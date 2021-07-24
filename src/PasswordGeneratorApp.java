import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.awt.*;
import java.awt.datatransfer.*;


public class PasswordGeneratorApp extends PApplet{
    private static PasswordGeneratorApp app;
    final String INIT_MSG="Type length here.";
    String msg=INIT_MSG; // input message from user
    private int length = 20;
    private String currentPassword = "";
    private int counter = 0;
    private int numbersCounter = 0;
    private int lowerCounter = 0;
    PFont mukta;
    PFont mono;
    PImage copy;
    PImage generate;
    PImage copied;
    PImage copyImg;
    private int rNum = 28;
    private int gNum = 28;
    private int bNum = 28;
    private int rSym = 28;
    private int gSym = 28;
    private int bSym = 28;
    private int rLower = 28;
    private int gLower = 28;
    private int bLower = 28;
    private int rUpper = 28;
    private int gUpper = 28;
    private int bUpper = 28;
    private boolean hasNumbers = false;
    private boolean hasSymbols = false;
    private boolean hasLowercase = false;
    private boolean hasUppercase = false;
    private int symbolsCounter = 0;
    private int upperCounter = 0;

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
        Password password = new Password(length, hasNumbers, hasSymbols, hasUppercase, hasLowercase);
        background(28,28,28);
        textFont(mukta);
        int x = 2;
        int y = x+1;

        // password
        textFont(mono);
        fill(249,235,48,255); //yellow
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

        int lineHeight = (height/2)+110;
        //lines
        strokeWeight(8);
        stroke(69,171,225);//blue
        line(0, lineHeight, 1067, lineHeight);

        strokeWeight(8);
        stroke(216,89,244); //pink
        line(0, lineHeight-5, 1067, lineHeight-5);

        strokeWeight(8);
        stroke(230,234,81); //yellow
        line(0, lineHeight-10, 1067, lineHeight-10);

        // must contain
        fill(249,235,48,255);
        textSize(27);
        text("must contain:", 135, lineHeight+45);

        //numbers setting
        strokeWeight(4);
        fill(rNum, gNum, bNum);
        stroke(216,89,244); //pink
        ellipse(60,lineHeight+80, 25, 25);
        fill(216,89,244);
        textSize(23);
        text("numbers", 130, lineHeight+85);

        //lowercase setting
        strokeWeight(4);
        fill(rLower, gLower, bLower);
        stroke(216,89,244); //pink
        ellipse(245,lineHeight+80, 25, 25);
        fill(216,89,244);
        textSize(23);
        text("lowercase", 330, lineHeight+85);

        //symbols setting
        strokeWeight(4);
        fill(rSym, gSym, bSym);
        stroke(216,89,244); //pink
        ellipse(60,lineHeight+125, 25, 25);
        fill(216,89,244);
        textSize(23);
        text("symbols", 130, lineHeight+130);

        //uppercase setting
        strokeWeight(4);
        fill(rUpper, gUpper, bUpper);
        stroke(216,89,244); //pink
        ellipse(245,lineHeight+125, 25, 25);
        fill(216,89,244);
        textSize(23);
        text("uppercase", 330, lineHeight+130);

        // user input
        fill(249,235,48,255);
        textSize(30);
        text(msg, 750, 500);

        // "press enter to save"
        fill(69,171,225);
        textSize(15);
        text("press enter to save.", 687, 525);

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

        // numbers button
        if (x > 50 && x < 70 &&
            y > height/2+170 && y < height/2+200){
            numbersCounter++;
            if(numbersCounter % 2 == 0){
                hasNumbers = true;
                rNum = 69;
                gNum = 171;
                bNum = 225;
            }
            else{
                hasNumbers = false;
                rNum = 28;
                gNum = 28;
                bNum = 28;
            }
        }

        // lowercase button
        if (x > 235 && x < 235+25 &&
                y > height/2+170 && y < height/2+200){
            lowerCounter++;
            if(lowerCounter % 2 == 0){
                hasLowercase = true;
                rLower = 69;
                gLower = 171;
                bLower = 225;
            }
            else{
                hasLowercase = false;
                rLower = 28;
                gLower = 28;
                bLower = 28;
            }
        }

        // symbols button
        if (x > 50 && x < 70 &&
                y > height/2+230 && y < height/2+258){
            symbolsCounter++;
            if(symbolsCounter % 2 == 0){
                hasSymbols = true;
                rSym = 69;
                gSym = 171;
                bSym = 225;
            }
            else{
                hasSymbols = false;
                rSym = 28;
                gSym = 28;
                bSym = 28;
            }
        }

        // uppercase button
        if (x > 235 && x < 235+25 &&
                y > height/2+230 && y < height/2+258) {
            upperCounter++;
            if (upperCounter % 2 == 0) {
                hasUppercase = true;
                rUpper = 69;
                gUpper = 171;
                bUpper = 225;
            } else {
                hasUppercase = false;
                rUpper = 28;
                gUpper = 28;
                bUpper = 28;
            }
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
