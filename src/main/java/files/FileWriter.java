package files;

import org.example.City;
import org.example.CityArrayList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

public class FileWriter {
    private final LineParser parser;

    public FileWriter() {
        this.parser = new LineParser();
    }

    private HashMap<String, String> cityToMap(City city) {
        HashMap<String, String> map = new HashMap<>();
        map.put(LineParser.KEY_NAME, city.getName());
        map.put(LineParser.KEY_POPULATION, String.valueOf(city.getPopulation()));
        map.put(LineParser.KEY_YEAR, String.valueOf(city.getYear()));
        return map;
    }

    public void write(Path filePath, CityArrayList<City> cityList) throws IOException {
        if (!FileUtils.isValidPath(filePath))
            throw new IOException("Некорректный путь к файлу: " + filePath);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND)) {
            for (int i = 0; i < cityList.size(); i++) {
                HashMap<String, String> cityMap = cityToMap(cityList.get(i));
                String line = parser.parseHashMap(cityMap);
                if (FileUtils.isValidLineFormat(line)) {
                    writer.write(line);
                } else throw new IllegalArgumentException();
            }
        }
    }
}
