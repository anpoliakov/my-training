package an.poliakov.example1;

import an.poliakov.example1.funcInterfaces.FuncSummator;
import an.poliakov.example1.funcInterfaces.FunctionalInterfaceOne;

/**
 * Основные сведения об функциональных интерфейсах:
 * -метод должен быть один
 * -метод может ничего не возвращать, в таком случае левая часть пуста: () -> действие
 * -кроме лямбда выражения можно писать лямбда блок
 * -есть НЕтерминальное(возвращает какое то значение) и терминальное (ничего не возвращает) лямбда выражение
 * -внутри лямбда функции нельзя изменять переменные метода! Только переменные класса!
 *
 * */

public class RunnerEx1 {
    int val1 = 5; //попробуем изменить (успешно)

    public static void main(String[] args) {
        RunnerEx1 e = new RunnerEx1();
        int val2 = 10; //попробуем изменить (ошибка)

        //пример терминальной лямбды
        FunctionalInterfaceOne fun = str -> System.out.println(str);
        fun.printStr("Вывод строки из метода функционального интерфейса");

        //пример нетерминальной лямбды + лямда блока
        FuncSummator fansum = (a, b) -> {
            //val2 = 7; - изменить переменную метода в лямбде не получится!

            int i = 0;
            int result = 0;
            while (i < 5){
                result += a + b + i;
                i++;
            }
            return result;
        };
        System.out.println(fansum.sum(1,1));
        System.out.println(val2);
    }
}
