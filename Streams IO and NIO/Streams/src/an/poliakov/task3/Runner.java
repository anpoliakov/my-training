package an.poliakov.task3;

import java.io.*;
import java.net.URL;

/*
* Получение картинки из интернета (внимательно со скачиваемым расширением и записываемым)
* */
public class Runner {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://uprostim.com/wp-content/uploads/2021/03/image102-16.jpg");
        BufferedInputStream buffer = new BufferedInputStream(url.openStream());

        FileOutputStream out = new FileOutputStream("src/an/poliakov/photo.jpg");

        byte [] data = new byte[100];
        int code;
        while ((code = buffer.read(data)) != -1){
            out.write(data, 0, code);
        }
    }
}
