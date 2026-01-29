package files;

import org.example.City;

import java.util.Map;

/**
 * Преобразует объекты City в строки формата "Название | Население | Год".
 */
public final class CitySerializer {
    private static final String FORMAT = "%s | %s | %s\n";
    private static final String KEY_NAME = "name";
    private static final String KEY_POPULATION = "population";
    private static final String KEY_YEAR = "year";

    private CitySerializer() {}

    /** Создает Map из полей города */
    private static Map<String, String> cityToMap(City city) {
        return Map.of(
                KEY_NAME, city.getName(),
                KEY_POPULATION, String.valueOf(city.getPopulation()),
                KEY_YEAR, String.valueOf(city.getYear()));
    }

    /** Форматирует Map в строку */
    private static String parseHashMap(Map<String, String> map) {
        return String.format(FORMAT,
                map.get(KEY_NAME),
                map.get(KEY_POPULATION),
                map.get(KEY_YEAR));
    }

    /**
     * Преобразует город в строку формата "Название | Население | Год".
     *
     * @param city объект City для преобразования
     * @return строка в формате "Название | Население | Год"
     * @throws IllegalArgumentException если city равен null
     */
    public static String serialize(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City не может быть null");
        }
        return parseHashMap(cityToMap(city));
    }
}
