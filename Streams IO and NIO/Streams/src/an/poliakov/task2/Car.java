package an.poliakov.task2;

import java.io.Serializable;

public class Car implements Serializable {
    //данное поле можно указывать и при десериализации данного обьекта,
    //поля будут сравниваться - и в случае расхождение исключение InvalidClassException
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;

    public Car(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name ='" + name + '\'' +
                ", age =" + age +
                '}';
    }
}
