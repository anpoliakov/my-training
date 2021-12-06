package an.poliakov.example2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RunnerEx2 {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList();
        animals.add(new Animal("Cat", 3, TypeAnimal.PEACEFUL));
        animals.add(new Animal("Tiger", 6, TypeAnimal.DENGEROUS));
        animals.add(new Animal("Dog", 5, TypeAnimal.PEACEFUL));
        animals.add(new Animal("Dodo", 100, TypeAnimal.PEACEFUL));
        animals.add(new Animal("Frog", 1, TypeAnimal.PEACEFUL));
        //вывод на консоль
        animals.forEach(System.out::println);

        /** НОВЫЙ ПОДХОД (декларативный) */

        //Filter пример 1
        System.out.println("\nРабота с методом filter пример 1:");
        //класс Predicate принимает 1 параметр и возвращает true/false (на нём можно построить условие)
        //задаём условие
        Predicate <Animal> tester = name -> name.getName().startsWith("D");
        //сортируем по условию и выводим на консоль
        animals.stream().filter(tester).forEach(System.out::println);

        //Filter пример 2
        System.out.println("\nРабота с методом filter пример 2:");
        List<Animal> sortedAnimals2 = animals.stream()
                .filter(animal -> animal.getType().equals(TypeAnimal.PEACEFUL)) //возвращает только true/false
                .collect(Collectors.toList()); //собрать всё в коллекцию
        sortedAnimals2.forEach(System.out::println);

        //
    }
}
