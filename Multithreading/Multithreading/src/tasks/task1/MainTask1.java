package tasks.task1;

import java.util.ArrayList;
import java.util.Scanner;

/* Задача: Пользователь вводит с клавиатуры значение в массив. После чего запускаются два потока.
Первый поток находит максимум в массиве, второй — минимум.
Результаты вычислений выводятся на в консоль */
public class MainTask1 {
    private ArrayList <Integer> mas = new ArrayList<>(13);

    public static void main(String[] args) throws InterruptedException {
        MainTask1 main = new MainTask1();
        main.fillMas();

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                main.searchMax();
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                main.searchMin();
            }
        });

        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
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
                System.out.println("Ввод завершён, идёт поиск по массиву:");
                System.out.println(mas);
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

    private void searchMax(){
        int maxValue = mas.get(0);
        int value;

        for (int i = 1; i < mas.size(); i++){
            if((value = mas.get(i)) > maxValue){
                maxValue = value;
            }
        }

        System.out.println("Max value = " + maxValue);
    }

    private void searchMin(){
        int minValue = mas.get(0);
        int value;

        for (int i = 1; i < mas.size(); i++){
            if((value = mas.get(i)) < minValue){
                minValue = value;
            }
        }

        System.out.println("Min value = " + minValue);
    }
}
