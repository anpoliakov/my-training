package lesson_7;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify wn = new WaitAndNotify();

        Thread td1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread td2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        td1.start();
        td2.start();

        td1.join();
        td2.join();
    }
}

class WaitAndNotify{
    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("Producer thread started ...");

            /* Данный метод в синхронизированном блоке на default классе this,
            * вызывается просто как - wait(), но если синхронизация на конкретном обьекте то lock.wait() */
            wait(); //1 - отдаём lock this обьекта, 2 - ждём пока будет вызван notify();

            System.out.println("Producer thread resumed ...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner sc = new Scanner(System.in);

        synchronized (this){
            System.out.println("Waiting for return key pressed");
            sc.nextLine();

            notify(); /* после вызова этого метода -
            монитор не переходит сразу в поток в котором был вызван метод wait(),
            монитор перейдёт в поток - только после завершения работы текущего потока */

            Thread.sleep(5000);
        }
    }
}
