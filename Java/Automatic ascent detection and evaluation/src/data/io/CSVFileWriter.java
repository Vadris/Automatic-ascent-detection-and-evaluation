package data.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * This class provides functionality to save data to a CSV file.
 */
public class CSVFileWriter {

    /**
     * Saves the provided CSV data to a file at the specified folder path with the given file name.
     *
     * @param csvData     The CSV data to be saved.
     * @param folderPath  The folder path where the file will be saved.
     * @param fileName    The name of the file.
     */
    public static void saveDataToCsvFile(String csvData, String folderPath, String fileName) {
        Path filePath = Path.of(folderPath, fileName);

        try {
            // Create parent directories if they don't exist
            Files.createDirectories(filePath.getParent());

            if (Files.exists(filePath)) {
                System.out.println("File already exists!");
            } else {
                // Write the CSV data to the file
                Files.writeString(filePath, csvData, StandardOpenOption.CREATE);
                System.out.println("File created");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating or writing to the file.");
            e.printStackTrace();
        }
    }
}
