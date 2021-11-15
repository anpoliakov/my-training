package an.poliakov.task2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/*
* Класс служит для создания архива
* */
public class AnotherArchiveWriter implements AutoCloseable{
    //обёртка - которая добавляет функционал примитивным типам при записи
    private final DataOutputStream output;

    //конструктор занимается - подготовкой файла для будущего архива
    public AnotherArchiveWriter(Path archive) throws IOException {
        this.output = new DataOutputStream(Files.newOutputStream(
                archive,
                StandardOpenOption.CREATE, //создать файл - если не существует
                StandardOpenOption.TRUNCATE_EXISTING // если существует - опусташить
        ));
    }

    public void addDirectoryRecursively(Path directory) throws IOException {
        //методы SimpleFileVisitor вызываются в различных стадиях перебора файлов
        //мне нужно только 1 событие - посещение файла (visitFile)
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                addFile(file, directory, attrs);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void addFile(Path file, Path baseDirectory, BasicFileAttributes fileAttributes) throws IOException {
        output.writeUTF(baseDirectory.relativize(file).toString()); //имя файла (без полного имени файла)
        output.writeLong(fileAttributes.creationTime().toMillis()); //время создания
    }

    @Override
    public void close() throws Exception {

    }
}
