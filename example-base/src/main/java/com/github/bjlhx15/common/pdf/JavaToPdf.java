package com.github.bjlhx15.common.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class JavaToPdf {
    private static final String DEST = "/Users/lihongxu6/IdeaProjects/common/common-pdf/target/HelloWorld.pdf";


    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        document.add(new Paragraph("hello world"));
        document.close();
        writer.close();
    }
}
