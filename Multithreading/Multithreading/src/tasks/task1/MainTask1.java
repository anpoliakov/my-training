package tasks.task1;

import java.util.ArrayList;
import java.util.Scanner;

public class MainTask1 {
    private ArrayList <Integer> mas = new ArrayList<>(13);

    public static void main(String[] args) {
        MainTask1 main = new MainTask1();
        main.fillMas();
        Thre
    }

    private void fillMas(){
        Scanner sc = new Scanner(System.in);
        final String STOP_SYMBOLL = "R";
        boolean isProcessing = true;

        System.out.println("Пожалуйста заполните массив (ввод по одному значению), как закончите введите " + STOP_SYMBOLL);
        while(isProcessing){
            String str = sc.nextLine().trim();
            if(str.equals(STOP_SYMBOLL)){
                isProcessing = false;
                System.out.println("Ввод завершён, идёт поиск");
                break;
            }

            try {
                Integer value = Integer.valueOf(str);
                mas.add(value);
                System.out.println("Успешно добавлено - " + value);
            }catch (NumberFormatException e){
                System.out.println("Вы ввели не цифру. Попробуйте ещё раз");
            }
        }
    }

    private int searchMax(){

    }

    private int searchMin(){

    }
}
