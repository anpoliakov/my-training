package ExampleFive;

/*
*
* Thread pool - ещё оидн способ создания потоков
*
* */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        //создаём пул потоков из 2-х потоков (можно рассматривать как 2 работника на фабрике)
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //пишем задание для этих потоков, как только один из потоков освободждается - он береёт себе новое задание
        for(int i = 0; i < 5; i++){
            executorService.submit(new Work(i));
        }

        //говорим что закончили задания для наших двух потоков
        executorService.shutdown();
        System.out.println("Задачи для  пула потоков описаны.");

        //что то вроде join - поток main доёдт до этого и будет ждать пока завершаться потоки пула
        //и ждать будет 1 день - если по истечению 1 дня не завершится, то потоки сворачиваются.
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

class Work implements Runnable{
    private int id;

    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Work " + id + " was completed!");
    }
}
