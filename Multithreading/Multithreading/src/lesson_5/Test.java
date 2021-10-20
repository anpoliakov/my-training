package lesson_5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
* Данные работники представленные пулом потоков - не будут хвататься за одну задачу
* работа данных потоков из коробки уже отлична
* */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        //непосредственно сам ПУЛ ПОТОКОВ
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //даём задания для наших существующих 2-х потоков (2-м рабочим)
        for(int i = 0; i < 5; i++){
            //метод принимает класс реализующий Runnable с методом run()
            executorService.submit(new Work(i));
        }

        //говорим сервису, что заданий больше нет и нужно приступать к выполнению заданий
        executorService.shutdown(); //мгновенный выход из этого метода в поток main

        //этот метод очень похож на метод join() только одно НО
        //если после 1 часа потоки не выполнили свою работу - они завершаются принудительно
        executorService.awaitTermination(1, TimeUnit.HOURS);
    }
}

//класс описывающий работу, которая имеет ID и выполняется определённое кол-во времени
class Work implements Runnable{
    private int id;

    public Work(int id){
        this.id = id;
    }

    //описание какой либо работы - то что занимает большое кол-во времени
    //в моём случае это просто сон N кол-во времени
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Work " + id + " was completed");
    }
}
