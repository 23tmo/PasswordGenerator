import java.util.ArrayList;

public class SettingMethods {
    public SettingMethods(){ }

    public static String getRandomChar(){
        ArrayList<String> possibleChars = new ArrayList<>();

        // if can have numbers:
        if (Password.hasNumbers()){
            int randInt = (int) Math.floor(Math.random() * (57-48+1)+48); // pick random int between 30-39
            char character = (char)(randInt);
            possibleChars.add(Character.toString(character)); // casts char int to string, inserts in arrayList
        }

        // if can have upper/lower chars
        if (Password.hasUpperCase()){
            int randInt = (int) Math.floor(Math.random()*(90-65+1)+65);
            char character = (char)(randInt);
            possibleChars.add(Character.toString(character));
        }

        if (Password.hasLowerCase()){
            int randInt = (int) Math.floor(Math.random()*(122-97+1)+97);
            char character = (char)(randInt);
            possibleChars.add(Character.toString(character));
        }


        // if can have symbols
        if (Password.hasSymbols()){
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 33; i <= 47; i++){ // adding numbers ranging from 32-47 inclusive
                list.add(i);
            }
            for (int i = 58; i <= 64; i++){
                list.add(i);
            }
            for (int i = 91; i <= 95; i++){
                list.add(i);
            }
            for (int i = 123; i <= 126; i++){
                list.add(i);
            }
            int index = (int) Math.floor(Math.random() * list.size());
            int number = list.get(index);
            char character = (char)(number);
            possibleChars.add(Character.toString(character)); // casts char symbol to string, inserts in arrayList
        }
        int index = (int) Math.floor(Math.random() * possibleChars.size());
        return possibleChars.get(index);
    }

    public static String generatePassword(){ // generates password of random chars, converts password to string
        String[] charArr = new String[Password.getLength()];
        String password = "";
        for(int i = 0; i < charArr.length; i++){
            charArr[i] = getRandomChar();
        }
        for (String s : charArr) {
            password += s;
        }
        return password;
    }
}