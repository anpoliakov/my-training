package an.poliakov.example3;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * СИМВОЛЬНЫЕ ПОТОКИ:
 *
 * записать из консоли в файл данные по символьному потоку.
 *
 * */
public class RunnerExample3 {
    public static void main(String[] args) {
        String str = "Привет моя Java";
//        byte[] bytes = str.getBytes();
        char [] chars = new char[str.length()];

        //получаю символы строки начиная от 0, до конца, куда помещаем символы, и откуда начинаем помещать
        str.getChars(0, str.length(), chars, 0);

        try (FileWriter writer = new FileWriter("src/an/poliakov/outFile.txt")){
//            writer.write(chars); //как вариант
            for (char symbol : chars) {
                writer.write(symbol);
            }
        }catch (IOException e){
            System.out.println("Error in your patch");
        }
    }
}
