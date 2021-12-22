package an.poliakov.example2;

public class Animal {
    private String name;
    private int age;
    private TypeAnimal type;

    public Animal(String name, int age, TypeAnimal type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    //не совсем корректно (просто пример)
    public static boolean isStronger(Animal animal){
        return true; //ERROR
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

    public TypeAnimal getType() {
        return type;
    }

    public void setType(TypeAnimal type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                '}';
    }
}
