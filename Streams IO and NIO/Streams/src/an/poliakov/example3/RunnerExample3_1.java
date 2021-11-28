package an.poliakov.example3;

public class RunnerExample3_1 {
    public static void main(String[] args) {
        String str = "Слово - Word";
        byte [] masBytes = str.getBytes();
        char [] masChar = new char[str.length()];

        System.out.println("Размер str = " + str.length());
        System.out.println("Размер masBytes = " + masBytes.length);
        System.out.println("Размер masChar = " + masChar.length);
        str.getChars(0, str.length(), masChar, 0);

        for (byte b : masBytes){
            System.out.println(b + " - " + (char)b);
        }
    }
}
