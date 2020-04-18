
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public static void createNewInvPDF(int event_id) throws IOException, Exception{
        //put invitation id in there next time
        //makes a copy of the invitation pdf and edit it
        File source = new File(""+System.getProperty("user.dir")+"\\src\\main\\resources\\au\\edu\\unsw\\business\\infs2605\\fxstarterkit\\images\\invitationTemplate.pdf");
        File dest = new File(""+System.getProperty("user.dir")+"\\src\\main\\resources\\au\\edu\\unsw\\business\\infs2605\\fxstarterkit\\images\\invitation" + event_id +".pdf");
        Files.copy(source.toPath(), dest.toPath());
        PDDocument doc = PDDocument.load(dest);
        PDPage page = doc.getPage(0);
        
        PDFont edoFont = PDTrueTypeFont.loadTTF(doc, new FileInputStream(new File (""+System.getProperty("user.dir")+"\\src\\main\\resources\\edo.ttf")));
        PDFont JSFont = PDTrueTypeFont.loadTTF(doc, new FileInputStream(new File (""+System.getProperty("user.dir")+"\\src\\main\\resources\\JosefinSans-Light.ttf")));
        PDFont JSFontBold = PDTrueTypeFont.loadTTF(doc, new FileInputStream(new File ("" + System.getProperty("user.dir")+"\\src\\main\\resources\\JosefinSans-Regular.ttf")));
        
        PDPageContentStream contentStream = new PDPageContentStream(doc, page,true,true,true);
        Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
        PreparedStatement ps = conn.prepareStatement("Select * FROM event WHERE event_id = ?");
        ps.setInt(1,event_id);
        ResultSet rs = ps.executeQuery();
        
        String eventName = null;
        String description = null;
        String location = null;
        String date = null;
        String start_time = null;
        String end_time = null;
        String instructions = null; 
        
        while(rs.next()){
            eventName = rs.getString("event_name");
            description = rs.getString("event_description");
            location = rs.getString("event_address");
            date = rs.getString("event_date");
            start_time = rs.getString("event_start_time");
            end_time = rs.getString("event_end_time");
            instructions = rs.getString("event_instructions");
        }

        contentStream.beginText();
        
        //event name, large yellow font 
        contentStream.setFont(edoFont,60);
        contentStream.setNonStrokingColor(249,193,118);
        contentStream.newLineAtOffset(67, 715);//655
        contentStream.setLeading(60f);
        insertTextColumn(contentStream, eventName, edoFont, 60, 270, 60);//275

        //description in black jsfont
        contentStream.setLeading(15);
        contentStream.newLine();
        contentStream.setFont(JSFont,20);
        contentStream.setNonStrokingColor(0,0,0);
        insertTextColumn(contentStream, description, JSFont, 20, 270, 25);
        contentStream.setLeading(55);
        contentStream.setNonStrokingColor(249,193,118);
        contentStream.setFont(JSFontBold,22);

        //location
        contentStream.newLine();
        contentStream.showText("Location:");
        contentStream.setLeading(25);
        contentStream.setNonStrokingColor(0,0,0);        
        contentStream.setFont(JSFont,20);
        insertTextColumn(contentStream, location, JSFont, 20, 270, 25);        
        contentStream.setLeading(40);

        //date
        contentStream.setNonStrokingColor(249,193,118);
        contentStream.setFont(JSFontBold,22);        
        contentStream.newLine();
        contentStream.showText("Date & Time:");
        contentStream.setLeading(25);
        contentStream.setNonStrokingColor(0,0,0);        
        contentStream.newLine();
        contentStream.setFont(JSFont,20);
        contentStream.showText(date + " " + start_time + " - " + end_time);
        contentStream.setLeading(40);        

        //special instructions
        contentStream.setNonStrokingColor(249,193,118);
        contentStream.setFont(JSFontBold,22);       
        contentStream.newLine();
        contentStream.showText("Special Instructions: ");
        contentStream.setLeading(25);       
        contentStream.setNonStrokingColor(0,0,0);        
        contentStream.setFont(JSFont,20);        
        insertTextColumn(contentStream, instructions, JSFont, 20, 270, 25);        
        
        contentStream.endText();
        contentStream.close();
        doc.save(dest);
        doc.close();      
    }
    
    //This wrap text method puts text into a column of a certain width. If a word overflows the
    //column width it puts these words on a new line. Works with different fonts and sizes.
    //Be sure to set the font size and colour before this method too
    public static void insertTextColumn(PDPageContentStream contentStream, String string, PDFont font, int fontSize, float width, int leading)throws Exception{
        int lastLetter = 0;
        int firstLetter = 0;
        String thisLine;
        String word[] = string.split(" ");

        try{
            for(int y=0; y<string.length(); y++){
                contentStream.setLeading(leading);
                if(y==string.length()-1){
                    if((font.getStringWidth(string.substring(firstLetter,y+1)) / 1000 * fontSize)>width){
                        contentStream.newLine();
                        thisLine = string.substring(firstLetter, lastLetter);
                        contentStream.showText(thisLine);
                        firstLetter = lastLetter + 1;
                    }

                    //special situation where there are 2 words
                    /**if((word.length==2) &&(font.getStringWidth(string.substring(firstLetter,y)) / 1000 * fontSize)>width){
                        contentStream.newLine();
                        contentStream.showText(word[0]);
                        contentStream.newLine();
                        contentStream.showText(word[1]);
                        break;
                    }**/
                    contentStream.newLine();
                    thisLine = string.substring(firstLetter,y+1);
                    contentStream.showText(thisLine);
                }
                if(string.charAt(y) == ' '){
                    if((font.getStringWidth(string.substring(firstLetter,y)) / 1000 * fontSize)>width){
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
