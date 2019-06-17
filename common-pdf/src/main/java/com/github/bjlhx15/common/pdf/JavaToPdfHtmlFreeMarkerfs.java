package com.github.bjlhx15.common.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class JavaToPdfHtmlFreeMarkerfs {
    private static final String DEST = "/Users/lihongxu6/IdeaProjects/common/common-pdf/target/HelloWorld_CN_HTML_freemarker_fs.pdf";
    private static final String HTML = "template_freemarker_fs.html";
    private static final String FONT = "simhei.ttf";
//    private static final String LOGO_PATH = JavaToPdfHtmlFreeMarkerfs.class.getClass().getResource("/")+"logo.jpg";
    private static final String LOGO_PATH = "file:/Users/lihongxu6/IdeaProjects/common/common-pdf/target/classes/";

    private static Configuration freemarkerCfg = null;

    static {
        freemarkerCfg =new Configuration();
        //freemarker的模板目录
        try {
            freemarkerCfg.setDirectoryForTemplateLoading(new File("/Users/lihongxu6/IdeaProjects/common/common-pdf/target/classes/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, DocumentException, com.lowagie.text.DocumentException {
        System.out.println(JavaToPdfHtmlFreeMarkerfs.class.getClass().getResource("/"));
        Map<String,Object> data = new HashMap();
        data.put("name","李宏旭");
        String content = JavaToPdfHtmlFreeMarkerfs.freeMarkerRender(data,HTML);
        JavaToPdfHtmlFreeMarkerfs.createPdf(content,DEST);
    }

    /**
     * freemarker渲染html
     */
    public static String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
        Writer out = new StringWriter();
        try {
            // 获取模板,并设置编码方式
            Template template = freemarkerCfg.getTemplate(htmlTmp);
            template.setEncoding("UTF-8");
            // 合并数据模型与模板
            template.process(data, out); //将合并后的数据和模板写入到流中，这里使用的字符流
            out.flush();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static void createPdf(String content,String dest) throws IOException, DocumentException, com.lowagie.text.DocumentException {
        ITextRenderer render = new ITextRenderer();
        ITextFontResolver fontResolver = render.getFontResolver();
        fontResolver.addFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 解析html生成pdf
        render.setDocumentFromString(content);
        //解决图片相对路径的问题
        // 解决图片的相对路径问题
        //renderer.getSharedContext().setBaseURL("file:/E:/apache-tomcat-6.0.16/apache-tomcat-6.0.16/webapps/apps-client/");
        render.getSharedContext().setBaseURL(LOGO_PATH);
        render.layout();
        render.createPDF(new FileOutputStream(dest));
    }
}
