package csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import myMath.DataPoint2D;
import myMath.ElevationProfile;

public class CsvParser {
    public static ElevationProfile parserCsv(String path) throws IOException{
        File csvDocument = new File(path);
        ArrayList<DataPoint2D> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvDocument))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(new DataPoint2D(Double.parseDouble(values[0]), Double.parseDouble(values[1])));
            }
        }
        return new ElevationProfile(data);
    }
}
