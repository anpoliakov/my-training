package lesson_13;

import java.util.Random;
import java.util.concurrent.*;

/*
* Работа с функциональным интерфейсом Callable (замена интерфейсу Runnable, который может
* возвращать какое то значение из потока, exceptions) и работа с параметризированным интерфейсом Future -
* который выступает Типом для получения значения из потока (тобишь работают в паре).
* */
public class Runner {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //Тип Future - содержит возвращаемое значение из отработавшего потока (можно заменить на лямбда)
        Future <String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                //имитация работы потока
                Random random = new Random();
                System.out.println("Starting new thread");
                Thread.sleep(3000);

                //пример возможности отловить исключение во второстепенном потоке - потоком извне
                if(random.nextInt(10) > 4){
                    throw new Exception("Something bad happened");
                }

                //возвращаем значение (в зависимости от параметризированного типа) из метода(потока)
                return "I finished - It was said by your thread!";
            }
        });

        executorService.shutdown();

        try {
            //текущий поток остановится в этом месте - и будет ожидать исполнения второстепенного потока
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //в этом типе ИМЕЕТСЯ возможность отлавливать исключения полученные
            //во второстепенном потоке
            Throwable ex = e.getCause();
            System.out.println(ex.getMessage());
        }
    }
}
