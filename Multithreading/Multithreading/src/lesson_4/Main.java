package lesson_4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }
}

class Worker{
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void addToList1(){

    }

    public void addToList2 (){

    }

    public void mainMethod(){
        long before = System.currentTimeMillis();

        long after = System.currentTimeMillis();
        System.out.println("Время работы: " + (after - before));
    }
}
