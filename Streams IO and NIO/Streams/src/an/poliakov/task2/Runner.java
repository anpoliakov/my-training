package an.poliakov.task2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class Runner {
    public static void main(String[] args) {
        Path pathClass = Paths.get("src/an/poliakov/filesForTests/filesTask2/dataOfClass.txt");
        ObjectOutputStream outObject = null;
        ObjectInputStream inObject = null;
        Car car1 = null;

        try {
            //ЗАПИСЬ ОБЬЕКТА
            outObject = new ObjectOutputStream(new FileOutputStream(pathClass.toFile()));
            car1 = new Car("BMW", 1995);
            outObject.writeObject(new Date());
            outObject.writeObject(car1);
            System.out.println("Object was recorded!");
            System.out.println("--------------------------------");

            //СЧИТЫВАНИЕ ОБЬЕКТА
            inObject = new ObjectInputStream(new FileInputStream(pathClass.toFile()));
            Date date = (Date)inObject.readObject();
            Car car2 = (Car)inObject.readObject();
            System.out.println("Object was read -> " + date);
            System.out.println("Object was read -> " + car2);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(outObject != null){
                try {
                    outObject.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
