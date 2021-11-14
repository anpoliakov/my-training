package an.poliakov.example1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class RunnerExampl1 {
    public static void main(String[] args) {
        FileWriter fileWriter = null;
        String str = " Java";

        try {
//            fileWriter = new FileWriter("src/an/poliakov/test.txt", true); //если true - файл не будет перезаписываться (допишет и всё)
//            fileWriter.write(str);


            FileOutputStream fileOutputStream = new FileOutputStream("src/an/poliakov/test.txt");
            fileOutputStream.write(str.getBytes());   //если так выполнить - то файл перезапишется

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //все потоки закрываем в отдельном try-catch
            //если не дозаписывает в файл - добавь закрытие :)
//            try {
//                fileWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
