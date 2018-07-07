package com.serpear.itextpdftest;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class App {

    public static void main(String[] args) {
        try {
            File file = new File("itext-test.pdf");
            FileOutputStream fileout = new FileOutputStream(file);
            Document document = new Document();
            PdfWriter.getInstance(document, fileout);
            document.addAuthor("Me");
            document.addTitle("My iText Test");

            document.open();

            Chunk chunk = new Chunk("iText Test");

            chunk.setBackground(new BaseColor(0xFF, 0xFF, 0xDE));
            document.add(chunk);

            Paragraph paragraph = new Paragraph();
            paragraph.add("Hello World");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            List<List<String>> dataset = getData();
            for (List<String> record : dataset) {
                for (String field : record) {
                    table.addCell(field);
                }
            }

            Image image;
            try {
                image = Image.getInstance("orange cone's - 590x600.png");
                image.setAlignment(Image.MIDDLE);
                document.add(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList list = new ArrayList();
            list.add("ABC");
            list.add("DEF");
            document.add(table);

            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static List<List<String>> getData() {
        List<List<String>> data = new ArrayList<List<String>>();
        String[] tableTitleList = {" Title", " (Re)set", " Obs", " Mean", " Std.Dev", " Min", " Max", "Unit"};
        data.add(Arrays.asList(tableTitleList));
        for (int i = 0; i < 10; ) {
            List<String> dataLine = new ArrayList<String>();
            i++;
            for (int j = 0; j < tableTitleList.length; j++) {
                dataLine.add(tableTitleList[j] + " " + i);
            }
            data.add(dataLine);
        }
        return data;
    }
}
