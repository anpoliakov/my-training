package lesson_12;

import java.util.Random;

/*
* Прерывание потока и понятие об исключении InterruptedExeption
* InterruptedExeption - выбрасывается методами, которые могли выполняться в момент принудительной
* остановки работы потока
* */
public class Runner {
    public static void main(String[] args) throws InterruptedException {

        //имитация долгой работы потока (работу этого потока будет прерывать)
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for(int i = 0; i < 1_000_000_000; i++){
                    //проверка, если в другом потоке была команда на остановку текущего потока
                    //то вернётся значение true
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Thread was interrupted");
                        break;
                    }
                    Math.sin(random.nextDouble());
                }
            }
        });
        System.out.println("Starting thread");

        thread.start();
        /* метод сообщает потоку второстепенному - что ему необходимо остановиться, сообщение из
        * главного потока main */
        thread.interrupt();
        thread.join();

        System.out.println("Thread has finished");
    }
}
