package an.poliakov.example2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RunnerEx2 {
    public static void main(String[] args) {
        /** СТАРЫЙ ПОДХОД */

        ArrayList<Animal> animals = new ArrayList();
        animals.add(new Animal("Cat", 3, TypeAnimal.PEACEFUL));
        animals.add(new Animal("Tiger", 6, TypeAnimal.DENGEROUS));
        animals.add(new Animal("Dog", 5, TypeAnimal.PEACEFUL));
        animals.add(new Animal("Dodo", 100, TypeAnimal.PEACEFUL));
        animals.add(new Animal("Frog", 1, TypeAnimal.PEACEFUL));

        System.out.println("We are working with these data:");
        animals.forEach(System.out::println);

        /** НОВЫЙ ПОДХОД (декларативный) */

        //FILTER
        //пример 1
        System.out.println("\nРабота с методом filter пример 1:");
        //класс Predicate принимает 1 параметр и возвращает true/false (на нём можно построить условие)
        Predicate <Animal> testPredicate = name -> name.getName().startsWith("D");
        //сортируем по условию и выводим на консоль
        animals.stream().filter(testPredicate).forEach(System.out::println);

        //пример 2
        System.out.println("\nРабота с методом filter пример 2:");
        List<Animal> filteredCollection = animals.stream()
                .filter(animal -> animal.getType().equals(TypeAnimal.PEACEFUL)) //возвращает только true/false
                .collect(Collectors.toList()); //собрать всё коллекцию
        filteredCollection.forEach(System.out::println);

        //SORT
        System.out.println("\nРабота с методом sort:");
        List<Animal> sortedCollection = animals.stream()
                .sorted(Comparator.comparing(Animal::getAge)/*.thenComparing(доп условие сортировки)*/.reversed())
                .collect(Collectors.toList());
        sortedCollection.forEach(System.out::println);

        //ALL MATCH
        System.out.println("\nРабота с методом all match:");
        boolean allMatch = animals.stream().allMatch( animal -> animal.getAge() > 5);
        System.out.println("ВСЕ ли животные удовлетворяют словию? -" + allMatch);

        //ANY MATCH
        System.out.println("\nРабота с методом any match:");
        boolean anyMatch = animals.stream().anyMatch(animal -> animal.getAge() == 3);
        System.out.println("ХОТЯ БЫ 1 удовлетворяет словию? -" + anyMatch);

        //NONE MATCH
        System.out.println("\nРабота с методом none match:");
        boolean noneMatch = animals.stream().noneMatch(animal -> animal.getName().equals("Cat"));
        System.out.println("Есть животное с таким именем (вернёт false) иначе (true) -" + noneMatch);

        //MAX
        System.out.println("\nРабота с методом MAX (stream API):");
        animals.stream().max(Comparator.comparing(Animal::getAge)).ifPresent(System.out::println);

        //MIN
        System.out.println("\nРабота с методом MIN (stream API):");
        animals.stream().min(Comparator.comparing(Animal::getAge)).ifPresent(System.out::println);

    }
}
