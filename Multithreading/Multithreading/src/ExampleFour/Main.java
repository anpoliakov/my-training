package ExampleFour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/*
*
*  Используем блок синхронизации на обьекте
*
* */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Worker().main();
    }
}

class Worker{
    Random random = new Random();

    //создаём обьекты болванки - которые нужны для новых состояний мониторов(мониторы обьекта)
    //когда один поток захватит монитор одного обьекта - второй поток сможет не ждать освобождения этого обьекта
    //а пойдёт ко второму обьекту с его монитором и захватит его.
    Object local1 = new Object();
    Object local2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void addToList1(){
        //сихронизируемся на мониторе обьекта local1
        //для разрешения конфликта Race Condition

        //пока один поток будет занимать addToList1(); второй поток сможет во время ожидания выполнить
        // метод addToList2();
        synchronized (local1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //может так оказать что 2 потока добавляют элемент - и опять же один из потоков захватит процессорное время
            //в результате чего набросает больше туда значений а поток который потом подключится - перезапишет их
            //это если без синхронизации! Но мы синхронизировали.
            list1.add(random.nextInt(100));
        }
    }

    public void addToList2(){
        //сихронизируемся на мониторе обьекта local2
        synchronized (local2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(random.nextInt(100));
        }
    }

    public void work(){
        for (int i = 0; i < 1000; i++){
            addToList1();
            addToList2();
        }
    }

    public void main(){
        //запоминаем время до программы
        long before = System.currentTimeMillis();

        Thread thread1 = new Thread(new Runnable() { // #ПЕРВЫЙ поток
            @Override
            public void run() {
                work();
            }
        });

        Thread thread2 = new Thread(new Runnable() { // #ВТОРОЙ поток
            @Override
            public void run() {
                work();
            }
        });

        thread1.start();
        thread2.start();

        try {
            // говорим что текеущему потоку (main поток) подождать выполнение потоков thread1,thread2
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //запоминаем время после программы и находим время работы!
        long after =  System.currentTimeMillis();
        System.out.println("Program took " + (after-before) + " ms to run");

        System.out.println("List 1: " + list1.size());
        System.out.println("List 2: " + list2.size());
    }
}
