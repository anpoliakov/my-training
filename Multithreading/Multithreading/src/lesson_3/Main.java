package lesson_3;

public class Main {
    public  int counter;

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.doWork();
    }

    //доступ к синхронизированному методу в один момент времени имеет - только ОДИН поток
    //синхронизация при использовании этого ключевого слова - на обьекте this (обьект в котором вызван этот метод)


    public synchronized void increment(){
        counter++;
    }

    public void doWork() throws InterruptedException {
        //просто описание - без старта потока
        Thread tdOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10000; i++){
                    increment();
                }
            }
        });

        //просто описание - без старта потока
        Thread tdTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10000; i++){
                    increment();
                }
            }
        });

        //запускаем 2 потока (мгновенно, при этом main поток идёт дальше)
        tdOne.start();
        tdTwo.start();

        //говорим main потоку (так как в нём вызываем метод join) свзяаться с
        //этими потоками и подождать их выполнения - после чего можно идти дальше
        tdOne.join();
        tdTwo.join();

        //посмотрим результат в counter
        System.out.println(counter);
    }
}
