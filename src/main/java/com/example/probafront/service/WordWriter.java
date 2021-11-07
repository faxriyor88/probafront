package com.example.probafront.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordWriter {
 public static void main(String[] args) throws IOException {
     XWPFDocument xwpfDocument=new XWPFDocument();
     XWPFParagraph xwpfParagraph=xwpfDocument.createParagraph();
     XWPFRun run= xwpfParagraph.createRun();
     run.setText("Salom");
     FileOutputStream fileOutputStream=new FileOutputStream(new File("src/main/java/com/example/probafront/service/myDocument.docx"));

     xwpfDocument.write(fileOutputStream);
     xwpfDocument.close();

 }
}
