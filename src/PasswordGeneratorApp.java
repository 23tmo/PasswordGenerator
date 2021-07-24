import processing.core.PApplet;

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
        size(600, 500); // size of canvas
    }

    public void setup(){
        fill(0);
    }

    public void draw(){
        Password password = new Password(length, false, true, false, false, false);
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
}
