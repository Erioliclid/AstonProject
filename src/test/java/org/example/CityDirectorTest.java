package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class CityDirectorTest {

    @Test
    public void testValidate1() {
        assertThrows(NullPointerException.class, () -> CityDirector.validate(null));

        City testObj1 = new CityConcept()
                .setName("Bad")
                .setPopulation(100)
                .setYear(100)
                .getCityAfterBuild();

        City testObj2 = new CityConcept()
                .setName("Good")
                .setPopulation(50_000)
                .setYear(0)
                .getCityAfterBuild();

        assertFalse(CityDirector.validate(testObj1));
        assertTrue(CityDirector.validate(testObj2));
    }

    @Test
    public void testValidate2() {
        City testObj1 = new CityConcept()
                .setName("Good")
                .setPopulation(250)
                .getCityAfterBuild();

        assertTrue(CityDirector.validate(testObj1, CityDirector.RULE.AU));

        testObj1 = new CityConcept()
                .setName("Bad")
                .setPopulation(199)
                .getCityAfterBuild();

        assertFalse(CityDirector.validate(testObj1, CityDirector.RULE.AU));

        testObj1 = new CityConcept()
                .setName("Good")
                .setPopulation(12_000)
                .getCityAfterBuild();

        assertTrue(CityDirector.validate(testObj1, CityDirector.RULE.RU));

        testObj1 = new CityConcept()
                .setName("Bad")
                .setPopulation(11_999)
                .getCityAfterBuild();

        assertFalse(CityDirector.validate(testObj1, CityDirector.RULE.RU));
    }

    @Test
    public void testValidate3() {
        assertFalse(CityDirector.validate("Bad", 12_000, -20_000));
        assertTrue(CityDirector.validate("Good", 12_000, -19_999));
    }

    @Test
    public void testValidate4() {
        assertFalse(CityDirector.validate("Bad", 19_999, 0, CityDirector.RULE.DEFAULT));
        assertTrue(CityDirector.validate("Good", 20_000, 0, CityDirector.RULE.DEFAULT));
    }

    @Test
    public void checkName() {
        CityDirector.RULE rule = CityDirector.RULE.DEFAULT;

        assertTrue(CityDirector.validate("Good", 12_000, 0));
        assertFalse(CityDirector.validate("", 12_000, 0));
        assertFalse(CityDirector.validate("_", 12_000, 0));
        assertFalse(CityDirector.validate("-", 12_000, 0));
        assertFalse(CityDirector.validate(" ", 12_000, 0));
        assertTrue(CityDirector.validate("Test - test", 12_000, 0));
        assertTrue(CityDirector.validate("Test - Test - Test", 12_000, 0));
        assertTrue(CityDirector.validate("Test-Test-Test-16", 12_000, 0));
        assertFalse(CityDirector.validate("Test-Test-Test16", 12_000, 0));
        assertFalse(CityDirector.validate("Test-Test-Test 16", 12_000, 0));
    }

    @Test
    public void converter() {
        City obj1 = new CityConcept()
                .setName("Test")
                .setPopulation(12_000)
                .getCityAfterBuild();

        City obj2 = CityDirector.converter("Test", 12_000, 0, new CityConcept())
                .getCityAfterBuild();

        assertEquals(obj1, obj2);

        obj2 = CityDirector.converter("Test", "12000", "0", new CityConcept())
                .getCityAfterBuild();

        assertEquals(obj1, obj2);

        String[] testString = {"Test", "12000", "0"};
        obj2 = CityDirector.converter(testString, new CityConcept())
                .getCityAfterBuild();

        assertEquals(obj1, obj2);
    }

    @Test
    public void cityDevelopment() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
                () -> CityDirector.cityDevelopment(new CityConcept()
                        .setName("test")
                        .setYear(0)
                        .setPopulation(0)));

        assertEquals("CityConcept: Invalid input data for building a class City",
                thrown.getMessage());

        City obj1 = new City();
        obj1.setName("Test");
        obj1.setPopulation(12_000);
        obj1.setYear(0);

        City obj2 = CityDirector.cityDevelopment(new CityConcept()
                .setName("Test")
                .setPopulation(12_000)
                .setYear(0));

        assertEquals(obj1, obj2);
    }
}