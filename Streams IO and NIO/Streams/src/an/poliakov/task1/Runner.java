package an.poliakov.task1;

import java.io.*;

/*
* Собственный фильтр файлов c определёнными расширениями
* */
public class Runner {
    public static void main(String[] args) {
        File file = new File("src/an/poliakov/filesForTests/filesTask1");

        //вывод ВСЕГО по указанному пути
        String[] allList = file.list();
        System.out.println("Весь список:");
        for(String s : allList){
            System.out.println(s);
        }

        //вывод файлов с УКАЗАННЫМИ расширениями (собственно сам ФИЛЬТР)
        File[] fileOfFilter = file.listFiles(new MyFilter(new String[]{"txt","mms"}));
        System.out.println("Отсортированный список:");
        for(File f : fileOfFilter){
            System.out.println(f.getName());
        }
    }
}
