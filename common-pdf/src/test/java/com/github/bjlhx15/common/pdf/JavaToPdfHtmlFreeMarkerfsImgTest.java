package com.github.bjlhx15.common.pdf;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaToPdfHtmlFreeMarkerfsImgTest {

    /**
     * 测试生成pdf文件
     * @throws Exception
     */
    @Test
    public void createPdfFile() throws Exception {
        System.out.println(JavaToPdfHtmlFreeMarkerfsImg.class.getClass().getResource("/"));
        Map<String,Object> data = new HashMap();
        data.put("name","李宏旭");
        JavaToPdfHtmlFreeMarkerfsImg.createPdfFile(data,"template_freemarker_fs.html",null,
                "/Users/lihongxu6/IdeaProjects/common/common-pdf/target/HelloWorld_CN_HTML_freemarker_fs_pdf.pdf");
    }


    /**
     * 测试生成流和图片
     * @throws Exception
     */
    @Test
    public void pdfToImg() throws Exception {
        System.out.println(JavaToPdfHtmlFreeMarkerfsImg.class.getClass().getResource("/"));
        Map<String,Object> data = new HashMap();
        data.put("name","李宏旭");

        ByteArrayOutputStream pdfStream = JavaToPdfHtmlFreeMarkerfsImg.pdfStream(data, "template_freemarker_fs.html");

        List<ByteArrayOutputStream> imgs = JavaToPdfHtmlFreeMarkerfsImg.pdfToImg(pdfStream.toByteArray(), 2, "png");

        String DEST = "/Users/lihongxu6/IdeaProjects/common/common-pdf/target/HelloWorld_CN_HTML_freemarker_fs_img2_";
        for (int i = 0; i < imgs.size(); i++) {
            FileOutputStream fileStream = new FileOutputStream(new File(DEST + i + ".png"));
            fileStream.write(imgs.get(i).toByteArray());
            fileStream.close();
        }
    }

}