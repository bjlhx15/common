package com.lhx.common.mybatis.plugin;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

public class MutiDatasourcePaginationPlugin extends PluginAdapter {
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
                                              IntrospectedTable introspectedTable) { // add field, getter, setter
        // for limit clause
        addPage(topLevelClass, introspectedTable, "page");
        return super.modelExampleClassGenerated(topLevelClass,
                introspectedTable);
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document,
                                           IntrospectedTable introspectedTable) {
        XmlElement parentElement = document.getRootElement();

        //----------------------------------------------------------------------------oracle 分页，生成相关 sql 部分语句  begin
        // 产生分页语句前半部分
        XmlElement oraclePaginationPrefixElement = new XmlElement("sql");
        oraclePaginationPrefixElement.addAttribute(new Attribute("id",
                "OracleDialectPrefix"));
        XmlElement pageStart = new XmlElement("if");
        pageStart.addAttribute(new Attribute("test", "page != null and _databaseId == 'oracle'"));
        pageStart.addElement(new TextElement(
                "select * from ( select row_.*, rownum rownum_ from ( "));
        oraclePaginationPrefixElement.addElement(pageStart);
        parentElement.addElement(oraclePaginationPrefixElement);

        // 产生分页语句后半部分
        XmlElement oraclePaginationSuffixElement = new XmlElement("sql");
        oraclePaginationSuffixElement.addAttribute(new Attribute("id",
                "OracleDialectSuffix"));
        XmlElement pageEnd = new XmlElement("if");
        pageEnd.addAttribute(new Attribute("test", "page != null and _databaseId == 'oracle'"));
        pageEnd.addElement(new TextElement(
                "<![CDATA[ ) row_ ) where rownum_ > #{page.begin} and rownum_ <= #{page.end} ]]>"));
        oraclePaginationSuffixElement.addElement(pageEnd);
        parentElement.addElement(oraclePaginationSuffixElement);
        //----------------------------------------------------------------------------oracle 分页，生成相关 sql 部分语句  end


        XmlElement mysqlPaginationSuffixElement = new XmlElement("sql");
        mysqlPaginationSuffixElement.addAttribute(new Attribute("id",
                "MysqlDialect"));
        XmlElement oraclePage = new XmlElement("if");
        oraclePage.addAttribute(new Attribute("test", "page != null and _databaseId == 'mysql'"));
        oraclePage.addElement(new TextElement("limit #{page.begin} , #{page.length}"));
        mysqlPaginationSuffixElement.addElement(oraclePage);
        parentElement.addElement(mysqlPaginationSuffixElement);

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {

        //----------------------------------------------------------------------------oracle 分页，生成相关 sql 部分语句  begin
        XmlElement oraclePageStart = new XmlElement("include"); //$NON-NLS-1$
        oraclePageStart.addAttribute(new Attribute("refid", "OracleDialectPrefix"));
        element.getElements().add(0, oraclePageStart);


        XmlElement oraclePageEnd = new XmlElement("include"); //$NON-NLS-1$
        oraclePageEnd.addAttribute(new Attribute("refid", "OracleDialectSuffix"));
        element.getElements().add(oraclePageEnd);
        //----------------------------------------------------------------------------oracle 分页，生成相关 sql 部分语句  end

        //----------------------------------------------------------------------------mysql 分页，生成相关 sql 部分语句  begin
        XmlElement mysqlPage = new XmlElement("include"); //$NON-NLS-1$
        mysqlPage.addAttribute(new Attribute("refid", "MysqlDialect"));
        element.getElements().add(mysqlPage);
        //----------------------------------------------------------------------------mysql 分页，生成相关 sql 部分语句  end

        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,
                introspectedTable);
    }

    private void addPage(TopLevelClass topLevelClass,
                         IntrospectedTable introspectedTable, String name) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType(
                "com.lhx.common.mybatis.vo.pagination.Page"));
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(new FullyQualifiedJavaType(
                "com.lhx.common.mybatis.vo.pagination.Page"));
        field.setName(name);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(
                "com.lhx.common.mybatis.vo.pagination.Page"), name));
        method.addBodyLine("this." + name + "=" + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType(
                "com.lhx.common.mybatis.vo.pagination.Page"));
        method.setName("get" + camel);
        method.addBodyLine("return " + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }

    /**
     * This plugin is always valid - no properties are required
     */
    public boolean validate(List<String> warnings) {
        return true;
    }
}
