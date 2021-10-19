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

    //обьекты для синхронизации потоков
    Object lock1 = new Object();
    Object lock2 = new Object();

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

    /* проблема 2-х методов ниже в том - что один из потоков (кто успеет) забирает себе в пользование монитор
    Worker обьекта в котором расположены эти методы, и второй поток ничего не может поделать, только ждать своей очереди
    - переписали метод и создали блок синхронизации на специальном обьекте-мониторе lock1 - теперь поток может выполнять
    соседний метод пока первый метод занят другим потоком */
    public void addToListOne(){
        synchronized(lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            listOne.add(random.nextInt(100));
        }
    }

    public void addToListTwo(){
        synchronized(lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            listTwo.add(random.nextInt(100));
        }
    }
}
