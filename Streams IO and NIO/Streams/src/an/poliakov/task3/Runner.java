package an.poliakov.task3;

import java.io.*;
import java.net.URL;

/*
* Получение картинки из интернета
* */
public class Runner {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://ya.cc/t/DwkYACpYy9Mx5");
        BufferedInputStream buffer = new BufferedInputStream(url.openStream());

        File file = new File("src/an/poliakov/photo.png");
        FileOutputStream out = new FileOutputStream(file);

        byte [] data = new byte[1024];
        int code;
        while ((code = buffer.read(data, 0 , 1024)) != -1){
            out.write(data, 0, code);
        }
        out.close();
        buffer.close();
    }
}
