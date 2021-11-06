package an.poliakov.example5;

import java.io.*;

/**
 *
 * СЕРИАЛИЗАИЯ / ДЕСЕРИАЛИЗАЦИЯ обьекта
 *
 * запись обьекта в файл и получение из файла назад в обьект.
 *
 * */
public class RunnerExample5 {
    public static void main(String[] args) {
        serialize();
        try {
            deserialize();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void serialize(){
        SerializableClass serializableClass = new SerializableClass("Gogo", 22);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/an/poliakov/outFile.txt"))){
            oos.writeObject(serializableClass);
        }catch(IOException e) {
            System.out.println("File not found");
        }
    }

    private static void deserialize() throws ClassNotFoundException{
        try (ObjectInputStream ois =  new ObjectInputStream(new FileInputStream("src/an/poliakov/outFile.txt"))){
            SerializableClass serializableClass = (SerializableClass) ois.readObject();
            System.out.printf("Title: %s%nSize: %d", serializableClass.title, serializableClass.size);
        }catch (IOException e){
            System.out.println("File not found");
        }
    }

    //по умолчанию все поля сериализуются - но если добавить параметр transient то то поле не сериализируется
    private static class SerializableClass implements Serializable{
        private String title; //поле не сериализируется
        private int size;

        public SerializableClass(String title, int size) {
            this.title = title;
            this.size = size;
        }
    }
}
