package an.poliakov.example3;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Есть ряд базовых функциональных интерфейсов
 * Predicate - принимает 1 параметр, возвращает true/false
 * Consumer - прнимает обьект типа Т, совершает какие то действия и ничего не возвращает
 * Supplier - ничего не прнимает, но возвращает обьект типа Т
 *
 * */
public class RunnerEx3 {
    public static void main(String[] args) {
        Predicate<Integer> pred = i -> i > 0;
        System.out.println(pred.test(2));

        Consumer <String> cons = str -> System.out.println(str);
        cons.accept("Hello");
    }
}
