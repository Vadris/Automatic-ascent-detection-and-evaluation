package data.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class CSVFileWriter {
    public static void saveDataToCsvFile(String csvData, String folderPath, String fileName) {
        Path filePath = Path.of(folderPath, fileName);

        try {
            Files.createDirectories(filePath.getParent());
            Files.writeString(filePath, csvData, StandardOpenOption.CREATE);
            System.out.println("File created");
        } catch (IOException e) {
            System.out.println("An error occurred while creating or writing to the file.");
            e.printStackTrace();
        }
    }
}