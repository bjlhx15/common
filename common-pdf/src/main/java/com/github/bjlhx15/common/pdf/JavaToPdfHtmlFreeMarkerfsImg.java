package com.github.bjlhx15.common.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaToPdfHtmlFreeMarkerfsImg {
    //    private static final String DEST = "/Users/lihongxu6/IdeaProjects/common/common-pdf/target/HelloWorld_CN_HTML_freemarker_fs_img.png";
    private static final String HTML = "template_freemarker_fs.html";
    private static final String FONT = "simsun.ttf";
    //    private static final String LOGO_PATH = "file:/Users/lihongxu6/IdeaProjects/common/common-pdf/target/classes/";
//    private static final String IMG_EXT = "png";

    private static Configuration freemarkerCfg = null;

    static {
        freemarkerCfg = new Configuration();
        //freemarker的模板目录
        try {
            String classpath = Thread.currentThread().getContextClassLoader().getResource("/") != null
                    ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                    : JavaToPdfHtmlFreeMarkerfsImg.class.getClass().getResource("/").getPath();
            freemarkerCfg.setDirectoryForTemplateLoading(new File(classpath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * freemarker渲染html 返回pdf 输出流
     *
     * @param data    数据
     * @param htmlTmp 模板路径classpath 后路径
     * @param font    字体simhei 默认
     * @return
     */
    public static ByteArrayOutputStream pdfStream(Map<String, Object> data, String htmlTmp, String font) {
        if (StringUtils.isEmpty(htmlTmp)) {
            throw new RuntimeException("数据模板不能为空");
        }
        String content = JavaToPdfHtmlFreeMarkerfsImg.freeMarkerRender(data, htmlTmp);
        font = StringUtils.isEmpty(font) ? FONT : font;
        return JavaToPdfHtmlFreeMarkerfsImg.createPdf(content, font);
    }

    /**
     * freemarker渲染html 返回pdf 输出流
     *
     * @param data    数据
     * @param htmlTmp 模板路径classpath 后路径
     * @return
     */
    public static ByteArrayOutputStream pdfStream(Map<String, Object> data, String htmlTmp) {
        return JavaToPdfHtmlFreeMarkerfsImg.pdfStream(data, htmlTmp, null);
    }

    /**
     * 生成pdf文件
     *
     * @param data    数据
     * @param htmlTmp 模板路径classpath 后路径
     * @param font    默认字体 simsun null既是默认
     * @param dest    目标路径
     * @throws Exception
     */
    public static void createPdfFile(Map<String, Object> data, String htmlTmp, String font, String dest) throws Exception {

        if (StringUtils.isEmpty(htmlTmp)) {
            throw new RuntimeException("数据模板不能为空");
        }
        String content = JavaToPdfHtmlFreeMarkerfsImg.freeMarkerRender(data, htmlTmp);
        ITextRenderer render = new ITextRenderer();
        ITextFontResolver fontResolver = render.getFontResolver();
        font = StringUtils.isEmpty(font) ? FONT : font;
        fontResolver.addFont(font, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 解析html生成pdf
        render.setDocumentFromString(content);
        //解决图片相对路径的问题
        // 解决图片的相对路径问题
        String classpath = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : JavaToPdfHtmlFreeMarkerfsImg.class.getClass().getResource("/").getPath();
        render.getSharedContext().setBaseURL("file:" + classpath);
        render.layout();
        render.createPDF(new FileOutputStream(dest));
    }


    /**
     * freemarker渲染html
     */
    private static String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
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

    /**
     * 根据模板生成pdf文件流
     */
    private static ByteArrayOutputStream createPdf(String content, String font) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ITextRenderer render = new ITextRenderer();
        ITextFontResolver fontResolver = render.getFontResolver();
        try {
            fontResolver.addFont(font, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (com.lowagie.text.DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 解析html生成pdf
        render.setDocumentFromString(content);
        //解决图片相对路径的问题

        String classpath = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : JavaToPdfHtmlFreeMarkerfsImg.class.getClass().getResource("/").getPath();

        render.getSharedContext().setBaseURL("file:" + classpath);
        render.layout();
        try {
            render.createPDF(outStream);
            return outStream;
        } catch (com.lowagie.text.DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 根据pdf二进制文件 生成图片文件
     *
     * @param bytes      pdf二进制
     * @param scale      清晰度
     * @param formatName 格式png，jpg等
     */

    public static List<ByteArrayOutputStream> pdfToImg(byte[] bytes, float scale, String formatName) {
        try {
            PDDocument doc = PDDocument.load(bytes);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pages = doc.getNumberOfPages();
            List<ByteArrayOutputStream> blist = new ArrayList<ByteArrayOutputStream>();
            for (int i = 0; i < pages; i++) {
                ByteArrayOutputStream btmp = new ByteArrayOutputStream();
                BufferedImage image = renderer.renderImage(i, scale);   //第二个参数越大生成图片分辨率越高。
                ImageIO.write(image, formatName, btmp);
                blist.add(btmp);
            }
            return blist;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
