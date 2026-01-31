package org.example.build;

import org.example.City;
import org.example.exception.NotValidCityDataException;
import org.example.country.Rule;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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

        assertTrue(CityDirector.validate(testObj1, Rule.AU));

        testObj1 = new CityConcept()
                .setName("Bad")
                .setPopulation(199)
                .getCityAfterBuild();

        assertFalse(CityDirector.validate(testObj1, Rule.AU));

        testObj1 = new CityConcept()
                .setName("Good")
                .setPopulation(12_000)
                .getCityAfterBuild();

        assertTrue(CityDirector.validate(testObj1, Rule.RU));

        testObj1 = new CityConcept()
                .setName("Bad")
                .setPopulation(11_999)
                .getCityAfterBuild();

        assertFalse(CityDirector.validate(testObj1, Rule.RU));
    }

    @Test
    public void testValidate3() {
        assertFalse(CityDirector.validate("Bad", 12_000, -20_000));
        assertTrue(CityDirector.validate("Good", 12_000, -19_999));
    }

    @Test
    public void testValidate4() {
        City obj = new City();
        obj.setName("Test");
        obj.setPopulation(50_000);
        obj.setYear(0);

        assertTrue(CityDirector.validate(obj, Rule.DEFAULT));
        assertTrue(CityDirector.validate(obj, Rule.UN));
        assertTrue(CityDirector.validate(obj, Rule.RU));
    }

    @Test
    public void testValidate5() {
        City obj = new City();
        obj.setName("Test");
        obj.setPopulation(50_000);
        obj.setYear(0);

        assertTrue(CityDirector.validate(obj, Rule.RU));

        obj.setName(" Test ");
        assertFalse(CityDirector.validate(obj, Rule.RU));
    }

    @Test
    public void testValidateEnum() {
        assertFalse(CityDirector.validate("Bad", 19_999, 0, Rule.DEFAULT));
        assertFalse(CityDirector.validate("Bad", 19_999, 0, Rule.UN));
        assertFalse(CityDirector.validate("Bad", 6_999, 0, Rule.UZ));
        assertFalse(CityDirector.validate("Bad", 9_999, 0, Rule.UA));
        assertFalse(CityDirector.validate("Bad", 9_999, 0, Rule.MD));
        assertFalse(CityDirector.validate("Bad", 9_999, 0, Rule.KG));
        assertFalse(CityDirector.validate("Bad", 9_999, 0, Rule.TJ));
        assertFalse(CityDirector.validate("Bad", 11_999, 0, Rule.RU));
        assertFalse(CityDirector.validate("Bad", 29_999, 0, Rule.JP));
        assertTrue(CityDirector.validate("Good", 20_000, 0, Rule.DEFAULT));
        assertTrue(CityDirector.validate("Good", 20_000, 0, Rule.UN));
        assertTrue(CityDirector.validate("Good", 7_000, 0, Rule.UZ));
        assertTrue(CityDirector.validate("Good", 10_000, 0, Rule.UA));
        assertTrue(CityDirector.validate("Good", 10_000, 0, Rule.MD));
        assertTrue(CityDirector.validate("Good", 10_000, 0, Rule.KG));
        assertTrue(CityDirector.validate("Good", 10_000, 0, Rule.TJ));
        assertTrue(CityDirector.validate("Good", 12_000, 0, Rule.RU));
        assertTrue(CityDirector.validate("Good", 30_000, 0, Rule.JP));
    }

    @Test
    public void testValidatePopulationMax() {
        assertFalse(CityDirector.validate("Bad", 100_000_000, 0));
        assertTrue(CityDirector.validate("Good", 99_999_999, 0));
    }

    @Test
    public void testValidateYear() {
        assertFalse(CityDirector.validate("Bad", 50_000, -20_000));
        assertTrue(CityDirector.validate("Good", 50_000, -19_999));
        int currentYear = LocalDate.now().getYear();
        int nextYear = currentYear + 1;
        assertFalse(CityDirector.validate("Bad", 50_000, nextYear));
        assertTrue(CityDirector.validate("Good", 50_000, currentYear));
    }

    @Test
    public void checkName() {
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
        assertTrue(CityDirector.validate("Орёл", 12_000, 0));
        assertTrue(CityDirector.validate("Нижний Новгород", 12_000, 0));
        assertFalse(CityDirector.validate("Ор3л", 12_000, 0));
        assertFalse(CityDirector.validate("Ор_л", 12_000, 0));
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

        try {
            obj2 = CityDirector.converter("Test", "12000", "0", new CityConcept())
                    .getCityAfterBuild();
            assertEquals(obj1, obj2);
        } catch (NotValidCityDataException e) {
            assertEquals(NotValidCityDataException.class, e.getClass());
        }


        String[] testString = {"Test", "12000", "0"};

        try {
            obj2 = CityDirector.converter(testString, new CityConcept())
                    .getCityAfterBuild();
            assertEquals(obj1, obj2);
        } catch (NotValidCityDataException e) {
            assertEquals(NotValidCityDataException.class, e.getClass());
        }

        NotValidCityDataException thrown = assertThrows(NotValidCityDataException.class,
                () -> CityDirector.converter("test", "19V65", "V20000", new CityConcept()));

        assertEquals("CityDirector: converter(String[], ICityBuilder)", thrown.getMessage());

        testString[0] = "Псков";
        testString[1] = "193082";
        testString[2] = "-903";

        ICityBuilder concept = new CityConcept();
        try {
            assertNotNull(CityDirector.converter(testString, concept));
        }
        catch (NotValidCityDataException e) {
            fail();
        }
    }

    @Test
    public void cityDevelopment() {
        NotValidCityDataException thrown = assertThrows(NotValidCityDataException.class,
                () -> CityDirector.cityDevelopment(new CityConcept()
                        .setName("test")
                        .setYear(0)
                        .setPopulation(0)));

        assertEquals("cityDevelopment: input data for create object type City not valid",
                thrown.getMessage());

        City obj1 = new City();
        obj1.setName("Test");
        obj1.setPopulation(12_000);
        obj1.setYear(0);

        try {
            City obj2 = CityDirector.cityDevelopment(new CityConcept()
                    .setName("Test")
                    .setPopulation(12_000)
                    .setYear(0));

            assertEquals(obj1, obj2);

        } catch (NotValidCityDataException e) {
            fail();
        }
    }
}