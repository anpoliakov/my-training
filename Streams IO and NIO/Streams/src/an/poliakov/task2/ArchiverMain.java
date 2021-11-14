package an.poliakov.task2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
* Собственный архиватор запуск + запуск через консоль
* */
public class ArchiverMain {
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length != 3){
            printUsageAndExit();
        }

        String cmd  = args[0];
        Path archive = Paths.get(args[1]);
        Path directory = Paths.get(args[2]);

        switch (cmd){
            case "pack":
                pack(archive,directory);
                break;
            case "unpack":
                unpack(archive,directory)
                break;

            default:
                printUsageAndExit();
                break;
        }
    }

    private static void printUsageAndExit(){
        System.out.print("Usage: yar <pack|unpack> <archive> <dir>");
        System.exit(1);
    }

    private static void pack(Path archive, Path directory) throws IOException {
        try (AnotherArchiveWriter archiveWriter = new AnotherArchiveWriter(archive)){
            archiveWriter.addDirectoryRecursively(directory);
        }
    }

    private static void unpack(Path archive, Path directory){

    }
}
