package an.poliakov.task1;

import java.io.*;

/*
* Собственный фильтр файлов по определённым расшерениям
* */
public class Runner {
    public static void main(String[] args) {
        File file = new File("src/an/poliakov/test_extensions");

        //вывод всего по указанному пути
        String[] allList = file.list();
        System.out.println("Весь список:");
        for(String s : allList){
            System.out.println(s);
        }

        //вывод только файлов с переданным расширением (собственно сам ФИЛЬТР)
        File[] fileOfFilter = file.listFiles(new MyFilter(new String[]{"txt"}));
        System.out.println("Отсортированный список:");
        for(File f : fileOfFilter){
            System.out.println(f.getName());
        }
    }
}
