import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.awt.*;
import java.awt.datatransfer.*;


public class PasswordGeneratorApp extends PApplet{
    private static PasswordGeneratorApp app;
    final String INIT_MSG="Type length of password";
    String msg=INIT_MSG; // input message from user
    private int length = 12;
    private String currentPassword = "";
    PFont mono;
    PImage copy;
    PImage generate;



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
        mono = createFont("MuktaMahee-ExtraBold", 32);
        copy = loadImage("copy.png");
        generate = loadImage("generate.png");
    }

    public void draw(){
        Password password = new Password(length, true, true, true, true);
        background(28,28,28);
        textFont(mono);
        int x = 2;
        int y = x+1;

        // generate button
        fill(255);
        strokeWeight(5);
        fill(28,28,28);
        rect(200, 300, 150, 60);
        fill(255);
        textSize(12);
        textAlign(CENTER, CENTER);
        text("generate password", 275, 325);

        // user input
        fill(255);
        text(msg, width/2, height/2 - 100);

        // password
        fill(255, 0, 0);
        textSize(20);
        text(currentPassword, width/2, height/2);

        // title yellow
        fill(230,234,81); //yellow
        textSize(65);
        textAlign(CENTER);
        text("PASSWORD   GENERATOR.", width/2, 80);

        // title pink
        fill(216,89,244); //pink
        text("PASSWORD   GENERATOR.", width/2-x, 80-y);

        // title blue
        fill(69,171,225); //blue
        text("PASSWORD   GENERATOR.", width/2-2*x, 80-2*y);

        //copy/generate cake
        copy.resize(0, 200);
        image(copy, 825, height/2-125);

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

        //copy cake
        if (x > 825 && x < (825 + 220) &&
                y > height/2-125 && y < (height/2-125 + 200)){
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
