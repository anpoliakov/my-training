package lesson_7_1;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();

        Thread td1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread td2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
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

class ProducerConsumer{
    private Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;

        while (true){
            synchronized (lock){
                while (queue.size() == LIMIT){
                    lock.wait();
                }

                queue.offer(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true){
            synchronized (lock){
                while (queue.size() == 0){
                    lock.wait();
                }

                System.out.println(queue.poll());
                System.out.println("Size = " + queue.size());
                lock.notify();
            }

            Thread.sleep(500);
        }
    }
}
