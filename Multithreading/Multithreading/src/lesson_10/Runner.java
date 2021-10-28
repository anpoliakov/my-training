package lesson_10;

/*
* Работа с классом Semaphore -  который регулирует работу потоков с одним источником данных
* при создании экземпляра данного класса в параметрах указываем кол-во потоков которые могут
* одновременно использовать этот один источник данных
* */
public class Runner {
    public static void main(String[] args) {

    }
}

//singleton класс - образ одного возможного подключения к серверу (который будет использоваться разными потоками)
class Connection{
    private static Connection connection = new Connection();
    private int connectionsCount;

    private Connection(){}

    public static Connection getConnection(){
        return connection;
    }

    //TODO метод с полезной работой

}
