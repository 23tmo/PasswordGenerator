import processing.core.PApplet;
import java.awt.*;
import java.awt.datatransfer.*;


public class PasswordGeneratorApp extends PApplet{
    private static PasswordGeneratorApp app;
    final String INIT_MSG="Type length of password";
    String msg=INIT_MSG; // input message from user
    private int length = 12;
    private String currentPassword = "";

    public static void main(String[] args){ // creates new PasswordGeneratorApp object and runs program
        app = new PasswordGeneratorApp();
        app.runSketch();
    }
    public PasswordGeneratorApp(){} // constructor

    public void settings(){
        size(1440, 872); // size of canvas
    }

    public void setup(){
        fill(0);
    }

    public void draw(){
        Password password = new Password(length, true, true, true, true, true);
        background(255);

        // generate button
        fill(0);
        strokeWeight(5);
        fill(255);
        rect(200, 300, 150, 60);
        fill(0);
        textSize(12);
        textAlign(CENTER, CENTER);
        text("generate password", 275, 325);

        // user input
        fill(0);
        text(msg, 100, 100);

        // password
        fill(255, 0, 0);
        textSize(20);
        text(currentPassword, width/2, height/2);

        // copy to clipboard
        fill(0, 0, 255);
        rect(600, 700, 150, 60);



    }
    public void mouseClicked() {
        handleMouseClicked(mouseX, mouseY);
        msg=INIT_MSG;  //Reset message, prepare for a new message
    }

    public void handleMouseClicked(int x, int y){
        if (x > 200 && x < (200 + 150) &&
                y > 300 && y < (300 + 60)){
            currentPassword = SettingMethods.generatePassword();
        }

        if (x > 600 && x < (600 + 150) &&
                y > 700 && y < (700 + 60)){
            copyToClipboard(currentPassword);
        }
    }

    public void keyPressed() {
        //Prepare when writing a new message. Next resets message container
        if (msg.equals(INIT_MSG)) {
            msg = "";
        }

        //Detects only alphanumeric chars
        if ((key >= 'a' && key <= 'z') ||
                (key >= 'a' && key <= 'z') ||
                (key >= '0' && key <= '9')) {
            msg += key;
        }

        if (key == ENTER){
            length = Integer.parseInt(msg); // converting input msg to length
            System.out.println(length);
        }
    }

    public String getMsg(){
        return msg;
    }

    public void copyToClipboard(String stringToCopy){
        StringSelection selection = new StringSelection(stringToCopy);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
