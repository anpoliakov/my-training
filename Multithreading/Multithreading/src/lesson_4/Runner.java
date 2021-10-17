package lesson_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Runner {
    public static void main(String[] args) {
        new Worker().main();
    }
}

class Worker{
    //генерация псевдослучайных чисел
    Random random = new Random();
    private List<Integer> listOne = new ArrayList<>();
    private List<Integer> listTwo = new ArrayList<>();

    public void addToListOne(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        listOne.add(random.nextInt(100));
    }

    public void addToListTwo(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        listTwo.add(random.nextInt(100));
    }

    public void work(){
        for(int i = 0; i < 1000; i++){
            addToListOne();
            addToListTwo();
        }
    }

    public void main(){
        long before = System.currentTimeMillis();
         work();
        long after = System.currentTimeMillis();

        System.out.println("Program took " + (after - before) + " ms to run");
    }
}
