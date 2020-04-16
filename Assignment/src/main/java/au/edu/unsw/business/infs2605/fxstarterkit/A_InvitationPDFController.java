
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
    
    public static void createNewInvPDF() throws IOException{
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
        
        contentStream.beginText();
        //event name, large yellow font over two lines 
        contentStream.setFont(edoFont,50);
        contentStream.setNonStrokingColor(249,193,118);
        contentStream.newLineAtOffset(67, 673);
        contentStream.setLeading(55f);
        contentStream.showText(eventName1); 
        contentStream.newLine();
        contentStream.showText(eventName2);
        float width = JSFont.getStringWidth(description.substring(0,31)) / 1000 * 22;
        System.out.printf("%.2f",width);
        //event1 width 148
        //evenname2 width 210
        //description to reserve width 282 
        //description, black jsfont
        contentStream.setLeading(32);
        contentStream.newLine();
        contentStream.setFont(JSFont,22);
        contentStream.setNonStrokingColor(0,0,0);
        contentStream.showText(description);
        //char[] newArray = new char[description.length()];
        //how much space left in the line
        //how many spaces away the next space is
        //for(int i = 0, i<description.length(); i++){
          //  ch[i] = 
    //}
        
        
        contentStream.endText();
        contentStream.close();
        doc.save(dest);
        doc.close();

        

        
    }

}
