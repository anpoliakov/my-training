package lesson_2;
/*
*   Применяем ключевое слово "volatile" на переменной - когда её используют несколько потоков (один пишет-другие читают).
*   Каждый поток может работать на отдельном ядре (у каждого ядра есть свой кэш может появиться эффект "некогерентности кешей",
*   содержимое кешей в ядрах будет отличаться). Данным ключевым словом запрещаем кэширование в ядрах, поток будет обращаться
*   каждый раз за значением в основную память.
*
* */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        //по нажатию - пропускает поток дальше и меняет значение переменной aBoolean
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        //основой поток main меняет переменную - которую читает второстепенный поток
        myThread.offWhile();
        System.out.println("I am off...");
    }
}

class MyThread extends Thread{
    volatile private boolean aBoolean = true;

    @Override
    public void run() {
        while(aBoolean){
            System.out.println("Hello");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void offWhile(){
        aBoolean = false;
    }
}