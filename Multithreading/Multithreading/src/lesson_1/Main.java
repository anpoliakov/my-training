package lesson_1;
/*
*   2 способа создания потоков [extends Thread ИЛИ implements Runnable]
*   Потоки в Java не синхронизированны, не известно какой из потоков отработает быстрее
*   запускать новый поток только с помощью метода start()
*/

public class Main {
    public static void main(String[] args){

        //1)Пример запуска новых потоков [extends от класса Thread]
        MyThread myThread = new MyThread();
        myThread.start();

        //Можно создать ещё поток из того же класса
        //MyThread myThread2 = new MyThread();
        //myThread2.start();

        //2)Пример запуска нового потока [implements интерфейса Runnable]
        Thread thread = new Thread(new MyRunnable());
        thread.start();

        System.out.println("Hello from main thread!");
    }
}

//1)Способ создания потока
class MyThread extends Thread {
    @Override
    public void run() {
        for(int i = 1; i<=50; i++){
            System.out.println("Hello from MyThread - " + i);
        }
    }
}

//2)Способ создания потока (интерфейс содержит всего 1 метод, который необходимо описать)
class MyRunnable implements Runnable{
    @Override
    public void run() {
        for(int i = 1; i<=50; i++){
            System.out.println("Hello from MyRunnable - " + i);
        }
    }
}
