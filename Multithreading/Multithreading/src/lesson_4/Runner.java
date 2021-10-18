package lesson_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        new Worker().main();
    }
}

class Worker{
    //генерация псевдослучайных чисел
    Random random = new Random();

    //два листа которые будем заполняем рандомными числамим
    private List<Integer> listOne = new ArrayList<>();
    private List<Integer> listTwo = new ArrayList<>();

    public void main() throws InterruptedException {
        long before = System.currentTimeMillis();

        //обьявление потоков
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });

        //запуск побочных потоков и ожидание их выполнения главным потоком
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();

        long after = System.currentTimeMillis();

        System.out.println("Program took " + (after - before) + " ms to run");
        System.out.println("Size list 1 = " + listOne.size());
        System.out.println("Size list 2 = " + listTwo.size());
    }

    public void work(){
        for(int i = 0; i < 1000; i++){
            addToListOne();
            addToListTwo();
        }
    }

    /* проблема этих методов в том - что один из потоков (кто успеет) забирает себе в пользование монитор
    Worker обьекта в котором расположены эти методы, и второй поток ничего не может поделать, только ждать своей очереди */
    public synchronized void addToListOne(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        listOne.add(random.nextInt(100));
    }

    public synchronized void addToListTwo(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        listTwo.add(random.nextInt(100));
    }
}
