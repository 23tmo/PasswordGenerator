public class Password {
    private static int length;
    private static boolean numbers;
    private static boolean symbols;
    private static boolean letters;
    private static boolean uppercase;
    private static boolean lowercase;

    public Password(int length, boolean numbers, boolean symbols, boolean letters, boolean uppercase, boolean lowercase){
        this.length = length;
        this.numbers = numbers;
        this.symbols = symbols;
        this.letters = letters;
        this.uppercase = uppercase;
        this.lowercase = lowercase;
    }

    public static int getLength(){
        return length;
    }

    public void setLength(int input){
        length = input;
    }

    public static boolean hasNumbers(){
        return numbers;
    }

    public static boolean hasSymbols(){
        return symbols;
    }

    public static boolean hasLetters(){
        return letters;
    }

    public static boolean hasUpperCase(){
        return uppercase;
    }

    public static boolean hasLowerCase(){
        return lowercase;
    }

    public boolean hasUppercase(){
        return uppercase;
    }

    public boolean hasLowercase(){
        return lowercase;
    }

}
