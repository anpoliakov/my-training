package lesson_11;

import java.util.concurrent.locks.Lock;

public class Runner {
    public static void main(String[] args) {

    }
}

class Test{
    /*
    * Основной метод с помощью которого можно сохранить индивидуальный порядок захвата
    * lock-ков в каждом потоке, но при этом не получить deadlock
    * */

    //в каждом потоке вызывается этот метод и передаются lock-и в нужном для тебя порядке
    private void takeLocks(Lock lock1, Lock lock2){
        boolean firstLockTaken = false;
        boolean secondLockTaken = false;

        //цикл до тех пор - пока одному потоку не принадлежат все локи (выход через return)
        while (true){
            try{
                //попытка захватить lock - если успешно return tru
                firstLockTaken = lock1.tryLock();
                secondLockTaken = lock2.tryLock();
            }finally {
                //если нужные lock-и захвачены одним потоком - выходим из всего метода и продолжаем
                //работу с использованием двух нужных тебе локов (ждём этого варианта событий в цикле)
                if(firstLockTaken && secondLockTaken){
                    return;
                }

                //если лок захвачен только одного обьекта - освобождаем
                if(firstLockTaken){
                    lock1.unlock();
                }

                if(secondLockTaken){
                    lock2.unlock();
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
