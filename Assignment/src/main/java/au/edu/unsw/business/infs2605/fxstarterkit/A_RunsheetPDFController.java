/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;

/**
 *
 * @author cathy
 */
public class A_RunsheetPDFController {

    public static void createNewRunsheetPDF(ArrayList event_time, ArrayList event_activity, String eventName, int eventId) throws IOException, Exception {
        //makes a copy of the runsheet template pdf and edits it
        File source = new File("" + System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "au" + File.separator + "edu" + File.separator + "unsw" + File.separator + "business" + File.separator + "infs2605" + File.separator + "fxstarterkit" + File.separator + "images" + File.separator + "event_runsheet.pdf");
        File dest = new File("" + System.getProperty("user.dir") + File.separator + "runsheet" + eventId + ".pdf");
        Files.copy(source.toPath(), dest.toPath());

        PDDocument doc = PDDocument.load(dest);
        PDPage page = doc.getPage(0);

        PDFont edoFont = PDTrueTypeFont.loadTTF(doc, new FileInputStream(new File("" + System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "edo.ttf")));
        PDFont JSFont = PDTrueTypeFont.loadTTF(doc, new FileInputStream(new File("" + System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "JosefinSans-Light.ttf")));
        PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true, true);

        //event name font
        contentStream.beginText();
        contentStream.setFont(edoFont, 45);
        contentStream.setNonStrokingColor(249, 193, 118);
        contentStream.newLineAtOffset(67, 650);
        contentStream.setLeading(18f);

        //wrap texr
        A_InvitationPDFController.insertTextColumn(contentStream, eventName, edoFont, 45, 400, 35);
        contentStream.newLine();

        //description black font      
        contentStream.setLeading(20);
        contentStream.newLine();
        contentStream.setFont(JSFont, 14);
        contentStream.setNonStrokingColor(0, 0, 0);

        //inserts user inputted data into runsheet
        for (int i = 0; i < event_time.size(); i++) {
            A_InvitationPDFController.insertTextColumn(contentStream, event_time.get(i) + ": " + event_activity.get(i), JSFont, 14, 450, 14);

            contentStream.newLine();
        }

        //Ending the content stream
        contentStream.endText();

        //Closing the content stream
        contentStream.close();

        //Saving the document
        doc.save(dest);

        //Closing the document
        doc.close();
    }

    public static void editRunsheetPDF(ArrayList event_time, ArrayList event_activity, String eventName, int eventId) throws IOException, Exception {

        File source = new File("" + System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "au" + File.separator + "edu" + File.separator + "unsw" + File.separator + "business" + File.separator + "infs2605" + File.separator + "fxstarterkit" + File.separator + "images" + File.separator + "event_runsheet.pdf");
        File dest = new File("" + System.getProperty("user.dir") + File.separator + "runsheet" + eventId + ".pdf");
        Files.copy(source.toPath(), dest.toPath());

        PDDocument doc = PDDocument.load(dest);
        PDPage page = doc.getPage(0);

        PDFont edoFont = PDTrueTypeFont.loadTTF(doc, new FileInputStream(new File("" + System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "edo.ttf")));
        PDFont JSFont = PDTrueTypeFont.loadTTF(doc, new FileInputStream(new File("" + System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "JosefinSans-Light.ttf")));
        PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true, true);

        //event name font
        contentStream.beginText();
        contentStream.setFont(edoFont, 45);
        contentStream.setNonStrokingColor(249, 193, 118);
        contentStream.newLineAtOffset(67, 650);
        contentStream.setLeading(18f);

        //wrap text
        A_InvitationPDFController.insertTextColumn(contentStream, eventName, edoFont, 45, 400, 35);
        contentStream.newLine();

        //description black font
        contentStream.setLeading(20);
        contentStream.newLine();
        contentStream.setFont(JSFont, 14);
        contentStream.setNonStrokingColor(0, 0, 0);

        for (int i = 0; i < event_time.size(); i++) {
            A_InvitationPDFController.insertTextColumn(contentStream, event_time.get(i) + ": " + event_activity.get(i), JSFont, 14, 450, 14);

            contentStream.newLine();
        }

        //Ending the content stream
        contentStream.endText();

        //Closing the content stream
        contentStream.close();

        //Saving the document
        doc.save(dest);

        //Closing the document
        doc.close();

    }
}
