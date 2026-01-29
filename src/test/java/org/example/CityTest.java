package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

public class CityTest {

    @Test
    public void testGetAndSet() {
        City testObj = new City();

        assertNull(testObj.getName());
        String testText = "Test";
        testObj.setName(testText);
        assertEquals(testText, testObj.getName());

        assertEquals(0, testObj.getPopulation());
        int testInt = 123456789;
        testObj.setPopulation(testInt);
        assertEquals(testInt, testObj.getPopulation());

        assertEquals(0, testObj.getYear());
        testObj.setYear(testInt);
        assertEquals(testInt, testObj.getYear());
    }

    @Test
    public void testToString() {
        City testObj = new City();
        String testMsg1 = "City name: null\nPopulation: 0\nYear of foundation: 0\n";

        assertEquals(testMsg1, testObj.toString());

        String testMsg2 = "TEST";
        int testInt = 123456789;
        testObj.setName("TEST");
        testObj.setPopulation(testInt);
        testObj.setYear(testInt);
        String testMsg3 = String.format("City name: %s\nPopulation: %d\nYear of foundation: %d\n",
                testMsg2, testInt, testInt);

        assertEquals(testMsg3, testObj.toString());
    }

    @Test
    public void testEquals() {
        City testObj1 = new City();
        City testObj3 = new City();
        City testObj4 = new City();
        Object testObj5 = new Object();

        assertFalse(testObj1.equals(null));
        assertTrue(testObj1.equals(testObj1));
        assertFalse(testObj1.equals(testObj5));

        testObj1.setName("Test");
        testObj1.setPopulation(123456789);
        testObj1.setYear(1234);

        testObj3.setName("Test");
        testObj3.setPopulation(123456789);
        testObj3.setYear(1234);

        assertTrue(testObj1.equals(testObj3));
        assertTrue(testObj3.equals(testObj1));

        testObj3.setName("Test-not");
        assertFalse(testObj1.equals(testObj3));
        assertFalse(testObj3.equals(testObj1));

        testObj4.setName(testObj1.getName());
        testObj4.setPopulation(testObj1.getPopulation());
        testObj4.setYear(testObj1.getYear());

        assertTrue(testObj1.equals(testObj4));

        testObj4.setPopulation(testObj4.getPopulation() - 1);

        assertFalse(testObj1.equals(testObj4));

        testObj4.setPopulation(testObj1.getPopulation());
        testObj4.setYear(testObj4.getYear() - 1);

        assertFalse(testObj1.equals(testObj4));
    }

    @Test
    public void testHashCodeEmptyObject() {
        City testObj = new City();
        assertThrows(NullPointerException.class, testObj::hashCode);
    }

    @Test
    public void testHashCodeEqualObject() {
        City testObj = new City();

        String name = "Test";
        int population = 123456;
        int year = 1234;

        int hashCode = Objects.hash(population, year) * 31 + name.hashCode();

        testObj.setYear(year);
        testObj.setPopulation(population);
        testObj.setName(name);

        assertEquals(hashCode, testObj.hashCode());
    }

    @Test
    public void testHashCodeDifferentObject() {
        City testObj = new City();

        String name = "Test";
        int population = 123456;
        int year = 1234;

        testObj.setYear(year);
        testObj.setPopulation(population);
        testObj.setName(name);

        name = "Test2";
        population = 654321;
        year = 4321;

        int hashCode = Objects.hash(population, year) * 31 + name.hashCode();

        assertNotEquals(hashCode, testObj.hashCode());
    }
}