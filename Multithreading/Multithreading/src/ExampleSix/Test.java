package ExampleSix;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/*
*
*  ПАТТЕРН производитель-потребитель
*
* */
public class Test {
    //очередь основанная на реализации через массив - ArrayBlockingQueue
    //работает по принципу как и обычная очередь FIFO
    //потокобезопасная(thread save) синхронизация уже настроенна внутри
    //говорим что максимальная размерность очереди 10 элементов
    public static BlockingQueue <Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
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

    //производитель - это метод\сущность которая создаёт какие то данные и закидывает их куда то для обработки
    public static void produce() throws InterruptedException {
        Random random = new Random();

        while(true){
            //как только очередь забьётся 10 элементами -> put будет ждать пока извлекут хотя бы 1 элемент из очереди
            queue.put(random.nextInt(100));
        }
    }

    //потребитель - берёт данные для обработки,обрабатывает и что либо с ними делает
    public static void consumer() throws InterruptedException {
        Random random = new Random();

        while (true){
            Thread.sleep(100);
            if(random.nextInt(10) == 5){
                System.out.println("From Queue: " + queue.take());
                System.out.println("Queue size is " + queue.size());
            }
        }
    }
}
