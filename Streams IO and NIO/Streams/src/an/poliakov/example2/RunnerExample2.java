package an.poliakov.example2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * БАЙТОВЫЕ ПОТОКИ:
 *
 * Возможность сразу выхватить с входного потока N-е количество байт и записать их N-е колличество
 * в выходной поток.
 *
 *
 * */
public class RunnerExample2 {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("src/an/poliakov/test.txt")){
            try(FileOutputStream output = new FileOutputStream("src/an/poliakov/outFile.txt")){
                //создаю массив байтов, размером = количеству байт которые можно скопировать
                byte [] bytes = new byte[input.available()];
                int buffer;

                //считываю определённое кол-во байтов в буфер
                while ((buffer = input.read(bytes)) != -1){
                    //записать столько то байт, со смещением = 0, и вот сами байты (buffer)
                    output.write(bytes, 0, buffer);
                }
            }catch (IOException e){
                System.out.println("Error in FileOutputStream");
            }
        }catch (IOException e){
            System.out.println("Error in FileInputStream");
        }
    }
}