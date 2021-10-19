package lesson_5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        //непосредственно сам ПУЛ ПОТОКОВ
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //TODO описать и изучить 20.10
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
    }
}
