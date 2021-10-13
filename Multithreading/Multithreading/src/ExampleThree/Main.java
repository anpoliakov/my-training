package ExampleThree;

/*
*  # synchronized - ключевое слово
*
*  Используем  synchronized - когда есть данные в которые вносят изменения 2+ потока
*  synchronized - говорит о том,что в данном методе может работать только 1 поток
*  когда он выходит из этого метода - в него может попасть другой поток
*
*  МОНИТОР - есть у всех обьектов, монитор в один момент времени может принадлежать только 1 потоку
*
* */
public class Main {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main ();
        main.doWork();

        System.out.println(main.counter);
    }

    // в данном случае используем монитор обьекта - кому пренадлежит этот метод
    // благодаря этому ключ слову убераем состояние гонки (Race condition)
    public synchronized void cou(){
        counter++;
    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++){
                    cou();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++){
                    cou();
                }
            }
        });

        thread1.start();
        thread2.start();

        // нужно подождать пока каждый из потоков поувеличивает переменную - иначе они одновременно будут присваивать ей
        // по +1 значение => ответ будет 10000

        // этим я говорю что поток MAIN ждёт пока закончится поток thread1 а затем ждёт thread2
        // после чего завершается сам
        thread1.join();
        thread2.join();
    }
}
