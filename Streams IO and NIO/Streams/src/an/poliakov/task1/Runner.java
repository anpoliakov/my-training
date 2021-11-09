package an.poliakov.task1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
* Работаю с байтовым потоком ввода/вывода
* */
public class Runner {
    public static void main(String[] args) {
        FileInputStream fis = null;
        int b;

        try {
            fis = new FileInputStream("src/an/poliakov/test.txt");
            while ((b = fis.read()) != -1){
                System.out.print(" - ");
                System.out.print(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
