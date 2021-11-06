package an.poliakov.example4;

import java.io.FileReader;
import java.io.IOException;

/**
 *
 * СИМВОЛЬНЫЙ ПОТОК
 *
 * считываем файл посимвольно - и выводим в консоль
 *
 * */
public class RunnerExample4 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("src/an/poliakov/outFile.txt")){
            int yourChar;

            while ((yourChar = reader.read()) != -1){
                System.out.print((char) yourChar);
            }
        }catch(IOException e){
            System.out.println("File not found");
        }
    }
}
