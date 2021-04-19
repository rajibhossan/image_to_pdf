package com;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws DocumentException, BadElementException, IOException {

        String outputFileName = "OUT_PUT_A4.pdf";//output file name
        String path = "C://Users/";//image file location
        String extention = "jpg";// image file type that need to convert pdf

        File location = getFileLocationFolder(path);
        List<String> imageFileName = getListOfImageFile(location, extention);
        createPdf(location, imageFileName, outputFileName);
    }

    public static List<String> getListOfImageFile(File root, String extention) {
        File[] listOfFiles = root.listFiles();
        List<String> files = new ArrayList<>();
        for (File name : listOfFiles) {
            //System.out.println(name.getName());
            String f = name.getName();
            System.out.println(f);
            String[] ext = f.split(Pattern.quote("."));
            System.out.println(ext[0] + ": " + ext[1]);

            if (ext[1].equals(extention)) {
                files.add(name.getName());
            }
        }
        return files;
    }

    public static File getFileLocationFolder(String location) {
        File root = new File(location);
        return root;
    }

    public static void createPdf(File root, List<String> files, String outputFile) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(new File(root, outputFile)));
        document.open();
        for (String f : files) {
            document.newPage();
            Image image = Image.getInstance(new File(root, f).getAbsolutePath());
            image.setAbsolutePosition(0, 0);
            image.setBorderWidth(0);
            image.scaleAbsolute(PageSize.A4);
            document.add(image);
        }
        document.close();
    }

}
