import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.awt.*;
import java.awt.datatransfer.*;


public class PasswordGeneratorApp extends PApplet{
    private static PasswordGeneratorApp app;
    final String INIT_MSG="Type length of password";
    String msg=INIT_MSG; // input message from user
    private int length = 20;
    private String currentPassword = "";
    private int counter = 0;
    PFont mukta;
    PFont mono;
    PImage copy;
    PImage generate;
    PImage copied;
    PImage copyImg;



    public static void main(String[] args){ // creates new PasswordGeneratorApp object and runs program
        app = new PasswordGeneratorApp();
        app.runSketch();
    }
    public PasswordGeneratorApp(){} // constructor

    public void settings(){
        size(1067, 589); // size of canvas
    }

    public void setup(){
        fill(0);
        mukta = createFont("MuktaMahee-ExtraBold", 32);
        mono = createFont("RobotoMono-Bold", 20);
        copy = loadImage("copy.png");
        generate = loadImage("generate.png");
        copied = loadImage("copied.png");
        copyImg = copy;
    }

    public void draw(){
        Password password = new Password(length, true, true, true, true);
        background(28,28,28);
        textFont(mukta);
        int x = 2;
        int y = x+1;

        // user input
        fill(255);
        text(msg, width/2, height/2 - 100);

        // password
        textFont(mono);
        fill(230,234,81); //yellow
        textSize(20);
        textAlign(LEFT);
        text(currentPassword, 50, height/2+30);

        // title yellow
        fill(230,234,81); //yellow
        textSize(65);
        textAlign(CENTER);
        text("PASSWORD GENERATOR.", width/2, 80);

        // title pink
        fill(216,89,244); //pink
        text("PASSWORD GENERATOR.", width/2-x, 80-y);

        // title blue
        fill(69,171,225); //blue
        text("PASSWORD GENERATOR.", width/2-2*x, 80-2*y);

        // generate cake
        generate.resize(0, 170);
        image(generate, 650, height/2-101);
        textFont(mono);
        fill(69,171,225);
        text("generate", 730, height/2);
        // w:170  h: 170
        // x: 650 y: height/2-101

        //copy cake
        copyImg.resize(0, 200);
        image(copyImg, 825, height/2-132);
        textFont(mono);
        fill(69,171,225);
        text("copy", 940, height/2-90);
        //w: 220 h:200
        //x: 825 y: height/2 - 125

    }
    public void mouseClicked() {
        handleMouseClicked(mouseX, mouseY);
        msg=INIT_MSG;  //Reset message, prepare for a new message
    }

    public void copyOrCopied(){
        counter++;
        if(counter % 2 == 0){
            copyImg = copied;
        }
        else{
            copyImg = copy;
        }

    }
    public void handleMouseClicked(int x, int y){
        //copy cake
        if (x > 825 && x < (825 + 220) &&
                y > height/2-125 && y < (height/2-125 + 200)){
            copyToClipboard(currentPassword);
            copyOrCopied();
        }

        // generate cake
        if (x > 650 && x < 650 + 170 &&
                y > height/2-101 && y < (height/2-101 + 170)){
            currentPassword = SettingMethods.generatePassword();
        }

        //if (x > XPOS && x < (XPOS + WIDTH) &&
        //    y > YPOS && y < (YPOS + HEIGHT)){
        //    copyToClipboard(currentPassword);
        //}
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
