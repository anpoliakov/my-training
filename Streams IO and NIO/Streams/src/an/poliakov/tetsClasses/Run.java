package an.poliakov.tetsClasses;

import java.io.*;
import java.net.URL;

public class Run {
    private static final String OUT_FOLDER = "/Volumes/Data/test/icons";
    private static final String url = "https://s3.o7planning.com/txt/google-emoticons-data.txt";

    public static void main(String[] args) throws IOException {
        InputStream is = new URL(url).openStream();

        PushbackInputStream pis = new PushbackInputStream(is, 2);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int fileIdx = 0;

        int code;
        while ((code = pis.read()) != -1) {
            if (code == (int)'@') {
                int next1 = pis.read();
                int next2 = pis.read();

                if (next1 == '@' && next2 == '@') {
                    byte[] imageData = baos.toByteArray();
                    fileIdx++;
                    writeToFile(imageData,fileIdx); // Write Image file.

                    baos = new ByteArrayOutputStream();
                } else {
                    baos.write(code);
                    pis.unread(next2);
                    pis.unread(next1);
                }
            } else {
                baos.write(code);
            }
        }

        byte[] imageData = baos.toByteArray();
        fileIdx++;
        writeToFile(imageData,fileIdx);  // Write Image file.

        pis.close();
    }

    private static void writeToFile(byte[] data, int fileIdx) throws IOException {
        File file = new File(OUT_FOLDER +"/" + fileIdx + ".png");
        file.getParentFile().mkdirs();
        System.out.println("Write file: " + file.getAbsolutePath());

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.close();
    }
}
