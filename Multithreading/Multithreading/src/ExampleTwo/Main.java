package ExampleTwo;

/* # Методы interrupt() and isInterrupted() - флаг потока

    Mожно рассматривать их как GET/SET методы внутри класса Thread для работы с переменной
    Можно описать работу потока опираясь на значение возвращаемое методом isInterrupted()
    Если возвращает false - то никто не запрашивал "остановку" потока
    А если вернёт true - то этот поток кто то попросил остановиться методом - interrupt() который устанавливает параметр true

*/
public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("Main Thread started...");

        try {
            //если прервать sleep,join,wait во время работы - сгенерируется исключение InterruptedException
            Thread.sleep(3000);

            //данный метод возвращает управление сразу текущему потоку
            myThread.interrupt();
            //и что бы подождать точного остановления потока и потом уже идти дальше - вызываем метод join
            myThread.join();

            System.out.println("Main Thread finished...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("MyThread started...");

            // есть небольшая проблема с isInterrupted() и обработкой исключения:
            while (!isInterrupted()) {
                System.out.println("Hello !");
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    // как только попадаем сюда - поток прерван,сработало исключение, в этом исключении сбрасывается
                    // значение isInterrupted() которое только что было установлено как true и стало сново false
                    System.err.println("### Поток прерван!");
                    // что бы всётаки прервать цикл используем break; или :
                    interrupt();
                }
            }
    }
}



