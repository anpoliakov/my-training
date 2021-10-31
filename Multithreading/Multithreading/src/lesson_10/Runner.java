package lesson_10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
* Работа с классом Semaphore -  который регулирует работу потоков с одним источником данных
* при создании экземпляра данного класса в параметрах указываем кол-во потоков которые могут
* одновременно использовать этот один источник данных (в моём случае класс Connection)
* */

//ВАРИАНТ БЕЗ ИСПОЛЬЗОВАНИЯ Semaphore класса:
public class Runner {
    public static void main(String[] args) throws InterruptedException {
        final int NUM_OF_THREAD = 200;

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREAD);
        Connection connection = Connection.getConnection();

        for(int i = 0; i < NUM_OF_THREAD; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        connection.doWork();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

/* singleton, образ моего подключения к серверу (показывает сколько сейчас подключено потоков
которые совершают некоторую полезную работу */
class Connection{
    private static Connection connection = new Connection();
    private int connectionsCount;

    private Connection(){}

    public static Connection getConnection(){
        return connection;
    }

    public void doWork() throws InterruptedException {
        synchronized (this){
            connectionsCount++;
            System.out.println(connectionsCount);
        }

        Thread.sleep(3000);

        synchronized (this){
            connectionsCount--;
        }
    }
}
