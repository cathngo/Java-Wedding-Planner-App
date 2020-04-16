
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javafx.application.HostServices;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import javafx.application.*;
import javafx.scene.paint.Color;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


/**
 *
 * @author mimi
 */
public class A_InvitationPDFController {
    public static void createNewInvPDF() throws IOException, Exception{
        //put invitation id in there next time
        //makes a copy of the invitation pdf and edit it
        File source = new File("C:\\Users\\Mimi\\Documents\\GitHub\\infs2605-20t1-EverlastingBelongGame\\Assignment\\src\\main\\resources\\au\\edu\\unsw\\business\\infs2605\\fxstarterkit\\images\\invTemplateFinal.pdf");
        File dest = new File("C:\\Users\\Mimi\\Documents\\GitHub\\infs2605-20t1-EverlastingBelongGame\\Assignment\\src\\main\\resources\\au\\edu\\unsw\\business\\infs2605\\fxstarterkit\\images\\newinvitation.pdf");
        Files.copy(source.toPath(), dest.toPath());
        PDDocument doc = PDDocument.load(dest);
        PDPage page = doc.getPage(0);
        
        PDFont edoFont = PDTrueTypeFont.loadTTF(doc, new FileInputStream(new File ("C:\\Users\\Mimi\\Documents\\GitHub\\infs2605-20t1-EverlastingBelongGame\\Assignment\\src\\main\\resources\\edo.ttf")));
        PDFont JSFont = PDTrueTypeFont.loadTTF(doc, new FileInputStream(new File ("C:\\Users\\Mimi\\Documents\\GitHub\\infs2605-20t1-EverlastingBelongGame\\Assignment\\src\\main\\resources\\JosefinSans-Light.ttf")));
        PDPageContentStream contentStream = new PDPageContentStream(doc, page,true,true,true);
        
        String eventName1 = "SANDY'S";
        String eventName2 = "BIRTHDAY!";
        String description = "It's my birthday party! Reserve the date. Don't arrive late!";
        String loc = "Location:";
        String loc2 = "18 Mona St, Lidcombe";
        String date = "Date & Time: ";
        String date2 = "3/2/2020 508PM";
        String specReq = "Special Requirements: ";
        String specReq2 = "No need to bring presents!";

        contentStream.beginText();
        //event name, large yellow font over two lines 
        contentStream.setFont(edoFont,60);
        contentStream.setNonStrokingColor(249,193,118);
        contentStream.newLineAtOffset(67, 673);
        contentStream.setLeading(60f);
        contentStream.showText(eventName1); 
        contentStream.newLine();
        contentStream.showText(eventName2);
            
        //description, black jsfont
        contentStream.setLeading(50);
        contentStream.setFont(JSFont,22);
        contentStream.setNonStrokingColor(0,0,0);
        //contentStream.showText(description);
        insertTextColumn(contentStream, description, JSFont, 22, 260, 33);
        contentStream.setLeading(60);
        contentStream.setNonStrokingColor(249,193,118);
        contentStream.setFont(JSFont,22);        
        contentStream.newLine();
        contentStream.showText(loc);
        contentStream.setLeading(33);
 
        contentStream.setNonStrokingColor(0,0,0);        
        contentStream.newLine();
        contentStream.showText(loc2);
        
        contentStream.setNonStrokingColor(249,193,118);
        contentStream.setFont(JSFont,22);        
        contentStream.newLine();
        contentStream.showText(date);

        contentStream.setNonStrokingColor(0,0,0);        
        contentStream.newLine();
        contentStream.showText(date2);        

        contentStream.setNonStrokingColor(249,193,118);
        contentStream.setFont(JSFont,22);       
        contentStream.newLine();
        contentStream.showText(specReq);
        
        contentStream.setNonStrokingColor(0,0,0);        
        contentStream.newLine();
        contentStream.showText(specReq2);        
        
        contentStream.endText();
        contentStream.close();
        doc.save(dest);
        doc.close();

        //width = 250 (try 260?)
        //leading 33

        
    }
    //this method puts text into a column of a certain width. It calculates  whether width of the words will overflow the
    //column (based on the font and fontsize) , and if so it puts these words on a new line. 
    public static void insertTextColumn(PDPageContentStream contentStream, String string, PDFont font, int fontSize, float width, int leading)throws Exception{
        int lastLetter = 0;
        int firstLetter = 0;
        String thisLine;
        try{
            for(int y=0; y<string.length(); y++){
                contentStream.setLeading(leading);
                if(y==string.length()-1){
                    contentStream.newLine();
                    thisLine = string.substring(firstLetter,y+1);
                    contentStream.showText(thisLine);
                }
                if(string.charAt(y) == ' '){
                    if((font.getStringWidth(string.substring(firstLetter,y)) / 1000 * 22)>width){
                        contentStream.newLine();
                        thisLine = string.substring(firstLetter,lastLetter);
                        contentStream.showText(thisLine);
                        firstLetter = lastLetter + 1;
                    }
                    lastLetter = y;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
