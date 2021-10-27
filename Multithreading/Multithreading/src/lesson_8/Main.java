package lesson_8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* Класс защёлка CountDownLatch - полностью синхронизированный,
* для работы одновременно с множеством потоков. Отпирает место (места) в
* потоке (в главнов или второстепенном), где был вызван метод countDownLatch.await().
* При условии - если был вызван метод countDownLatch.countDown(); колличеством = числу
* в параметрах при создании обьекта CountDownLatch.
* */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        //через пул потоков создами список заданий
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for(int i = 0; i < 5; i++){
            executorService.submit(new Processor(i,countDownLatch));
        }
        executorService.shutdown();

        //говорим что в главном потоке будем ждать вызова метода countDown() кол-вом = 5 (не важно из каких потоков)
        countDownLatch.await();
        System.out.println("Программа выполнена, дождалась вызова 5 раз метода countDown()");
    }
}

class Processor implements Runnable{
    private int id;
    private CountDownLatch cdl;

    public Processor(int id, CountDownLatch cdl){
        this.id = id;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //вызовем метод для уменьшения значения защёлки (1 из 5 для открытия защёлки await())
        cdl.countDown();
        System.out.println("Thread - " + id);
    }
}
