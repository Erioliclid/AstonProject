package org.example.files;

import org.example.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

import files.CitySerializer;

/**
 * Тесты для класса CitySerializer
 */
class CitySerializerTest {

    @Test
    @DisplayName("Метод serialize бросает исключение при null")
    void serialize_nullCity_throwsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> CitySerializer.serialize(null)
        );

        assertEquals("City не может быть null", exception.getMessage());
    }

    @Test
    @DisplayName("Корректная сериализация города")
    void serialize_validCity_returnsCorrectFormat() {
        // Arrange
        City city = createTestCity("Москва", 12655050, 1147);

        // Act
        String result = CitySerializer.serialize(city);

        // Assert
        assertEquals("Москва | 12655050 | 1147\n", result);
    }

    @Test
    @DisplayName("Сериализация другого города")
    void serialize_anotherCity_returnsCorrectFormat() {
        // Arrange
        City city = createTestCity("Санкт-Петербург", 5384342, 1703);

        // Act
        String result = CitySerializer.serialize(city);

        // Assert
        assertEquals("Санкт-Петербург | 5384342 | 1703\n", result);
    }

    @Test
    @DisplayName("Город с UTF-8 символами")
    void serialize_cityWithUtf8Characters() {
        // Arrange
        City city = createTestCity("Йошкар-Ола", 281248, 1584);

        // Act
        String result = CitySerializer.serialize(city);

        // Assert
        assertEquals("Йошкар-Ола | 281248 | 1584\n", result);
    }

    // Вспомогательный метод для создания тестового города
    private City createTestCity(String name, int population, int year) {
        try {
            ICityBuilder concept = new CityConcept();
            CityDirector.converter(name, population, year, concept);
            return CityDirector.cityDevelopment(concept);
        } catch (NotValidCityDataException e) {
            throw new RuntimeException(e);
        }
    }
}
