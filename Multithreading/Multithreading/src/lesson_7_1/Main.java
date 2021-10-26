package lesson_7_1;

import java.util.LinkedList;
import java.util.Queue;

/*
* Пример работы с несинхронизированной очередью, синхронизацию производим сами
* на низком уровне, с помощью методов wait() and notify()
* */

//просто класс для запуска 2-х методов в разных потоках
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
    private Queue<Integer> queue = new LinkedList<>();  //несинхрон очередь
    private final int LIMIT = 10;                       //максимальное кол-во элемен в очереди
    private final Object lock = new Object();           //обьект на котором производим синхронизацию

    public void produce() throws InterruptedException {
        int value = 0;

        //зацикливавем поток в пределах этого while()
        while (true){
            synchronized (lock){
                while (queue.size() == LIMIT){
                    lock.wait(); //если сработает этот метод, в этом месте остановитсяи наш текущий поток (ожидая вызова notify() в пределах обьекта lock)
                }

                queue.offer(value++);
                lock.notify();//вызов метода - активирует работу другого потока который был в состоянии ожидания (в нём был вызван wait())
                //и только после завершения работы текущего потока и освобождения lock
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
                lock.notify(); //чаще вчего данный метод прописывают в самом конце
            }

            Thread.sleep(500);
        }
    }
}
