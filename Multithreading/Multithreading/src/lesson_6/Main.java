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

    public static void main(String[] args) {

    }

    //представляет из себя - производителя
    private static void produce() throws InterruptedException {
        Random random = new Random(100);

        while (true){
            queue.put(random.nextInt());
        }
    }

    //представляет из себя потребителя
    private static void consumer() throws InterruptedException {
        while (true){
            Thread.sleep(100);
            System.out.println(queue.take());
            System.out.println("Size our queue is " + queue.size());
        }
    }
}
