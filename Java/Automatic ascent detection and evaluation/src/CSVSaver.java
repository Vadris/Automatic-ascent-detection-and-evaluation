import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVSaver {
    public static void saveFile(String csvData, String folderPath, String fileName){
        File file = new File(folderPath + "/" + fileName);
        try{
            if(file.createNewFile()) System.out.println("File created");
            else System.out.println("File allready exists");
        }
        catch(IOException e){
            System.out.println("An error occured creating the file.");
            e.printStackTrace();
            return;
        }
        try{
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(csvData);
            fileWriter.close();
        }catch(IOException e){
            System.out.println("An error occured while writing to the file.");
            e.printStackTrace();
        }
    }
}
