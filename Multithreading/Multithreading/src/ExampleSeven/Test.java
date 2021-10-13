package ExampleSeven;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify wn = new WaitAndNotify();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}

class WaitAndNotify {

    public void produce() throws InterruptedException {
        synchronized(this){
            System.out.println("Produce thread started...");
            //wait() - вызывается ТОЛЬКО в пределах синхронизованного блока
            //вызывается на обьекте на котором синхронизован блок synchronized
            wait(); //При вызове происходит следующее: 1 - отдаём intrinsic lock, 2- ждём пока будет вызван метод notify().
            System.out.println("Produce thread resumed...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000); // для того что бы поток с методом produce() был 100% первым
        Scanner scanner = new Scanner(System.in);

        synchronized (this){
            System.out.println("Waiting for return key pressed");
            scanner.nextLine();
            //важно что бы метод wait() and notify() были вызваны на одном обьекте(экран один)
            //за счёт этого обьекта и происходит связь этих двух потоков
            notify();
        }


    }

}
