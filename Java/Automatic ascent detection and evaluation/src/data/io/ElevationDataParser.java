package data.io;

import analysis.Math.DataPoint;
import analysis.Math.ElevationProfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ElevationDataParser {
    /**
     * Parses a CSV file containing elevation data and constructs an elevation profile.
     *
     * @param path The path to the CSV file.
     * @return The elevation profile constructed from the CSV data.
     * @throws IOException If an I/O error occurs while reading the CSV file.
     */
    public static ElevationProfile parseCsvToElevationProfile(String path) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path));
        ArrayList<DataPoint> data = new ArrayList<>();

        for (String line : lines) {
            String[] values = line.split(",");
            double x = Double.parseDouble(values[0]);
            double y = Double.parseDouble(values[1].replace(";", ""));
            data.add(new DataPoint(x, y));
        }

        return new ElevationProfile(data);
    }
}
