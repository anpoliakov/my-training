package lesson_6;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
* Описание работы паттерна Производитель-Потребитель
* */
public class Main {
    //просто синхронизированная очередь - для параллельной работы нескольких потоков
    private static BlockingQueue <Integer> queue = new ArrayBlockingQueue(10);

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadOne.start();
        threadTwo.start();

        //пишем, всёравно что наши второстепенные потоки никогда не завершаться
        threadTwo.join();
        threadOne.join();
    }

    //представляет из себя - производителя (генерация данных в очередь)
    private static void produce() throws InterruptedException {
        Random random = new Random();

        while (true){
            /** СПЕЦ метод класса ArrayBlockingQueue, если очередь заполнена - ЖДЁТ пока появится место что бы добавить элемент*/
            queue.put(random.nextInt(100));
        }
    }

    //представляет из себя потребителя
    private static void consumer() throws InterruptedException {
        Random random = new Random();

        while (true){
            Thread.sleep(100);
            if(random.nextInt(10) == 5) {
                /** СПЕЦ метод класса ArrayBlockingQueue, если очередь пуста - ЖДЁТ пока появится данные для считывания */
                System.out.println(queue.take());
                System.out.println("Size our queue is " + queue.size());
            }
        }
    }
}
