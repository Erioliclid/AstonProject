package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class CityConceptTest {

    @Test
    public void setName() {
        CityConcept testObj = new CityConcept();
        assertThrows(NullPointerException.class, () -> testObj.setName(null));

    }

    @Test
    public void setPopulation() {
    }

    @Test
    public void setYear() {
    }

    @Test
    public void getCityAfterBuild() {
    }
}