package org.example.build;

import org.example.City;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CityConceptTest {
    String testString1 = "test1";
    String testString2 = "abc";
    int testInt1 = 123456;
    int testInt2 = 654321;

    @Test
    public void setNameWithException() {
        CityConcept testObj = new CityConcept();

        assertThrows(NullPointerException.class, () -> testObj.setName(null));
    }

    @Test
    public void setNameWithConstructor() {
        CityConcept testObj1 = new CityConcept(testString1);

        City testObj2 = testObj1.getCityAfterBuild();
        assertEquals(testString1, testObj2.getName());
    }

    @Test
    public void setName() {
        CityConcept testObj1 = new CityConcept();

        testObj1.setName(testString1);
        City testObj2 = testObj1.getCityAfterBuild();
        assertEquals(testString1, testObj2.getName());
    }

    @Test
    public void setPopulation() {
        CityConcept testObj1 = new CityConcept();
        City testObj2 = testObj1.getCityAfterBuild();

        assertEquals(0, testObj2.getPopulation());

        testObj1.setPopulation(testInt1);
        testObj2 = testObj1.getCityAfterBuild();

        assertEquals(testInt1, testObj2.getPopulation());
    }

    @Test
    public void setYear() {
        CityConcept testObj1 = new CityConcept();
        City testObj2 = testObj1.getCityAfterBuild();

        assertEquals(0, testObj2.getYear());

        testObj1.setYear(testInt1);
        testObj2 = testObj1.getCityAfterBuild();

        assertEquals(testInt1, testObj2.getYear());
    }

    @Test
    public void getCityAfterBuild() {
        CityConcept testObj1 = new CityConcept();

        City testObj2 = testObj1
                .setName(testString1)
                .setPopulation(testInt1)
                .setYear(testInt2)
                .getCityAfterBuild();

        City testObj3 = testObj1
                .setName(testString1)
                .setPopulation(testInt1)
                .setYear(testInt2)
                .getCityAfterBuild();


        assertNotNull(testObj2);
        assertNotNull(testObj3);

        assertNotSame(testObj2, testObj3);

        assertEquals(testObj2, testObj3);
        assertEquals(testObj2.hashCode(), testObj3.hashCode());

        testObj3 = testObj1
                .setName(testString2)
                .setPopulation(testInt2)
                .setYear(testInt1)
                .getCityAfterBuild();

        assertNotEquals(testObj2, testObj3);
        assertNotEquals(testObj2.hashCode(), testObj3.hashCode());
    }
}