package an.poliakov.example0;

import java.io.*;

/*
* Простой пример работы по считыванию данных из файла спомощтю символьного потока
* */
public class Runner {
    public static void main(String[] args) {
        FileInputStream fis = null; //байтовый поток
        InputStreamReader isr = null; //символьный поток
        int b = 0;

        //обрабатываем исключение - так как компилятор не проверяет путь и файла может не существовать
        try {
            /* байтовый поток - получаем байт и преобразуем его к символу, для работы с
            символами и текстом - лучше выбрать символьный поток */
            fis = new FileInputStream("src/an/poliakov/filesForTests/filesBasic/test.txt");
            //в символьный поток передаём поток байтов - результат верная кодировка рус символов
            isr = new InputStreamReader(fis, "UTF-8");

            //считываем в переменную типа int - но значение будет всёравно byte
            while ((b = isr.read()) != -1){
                System.out.print((char) b);
            }

        /* обрабатываем исключение - если при получении данных
        была потеряна связь (достали флешку из пк) */
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            //закрываем потоки отдельно - иначе если один не закроется, второй тоже
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
