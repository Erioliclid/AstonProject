package org.example;

import java.util.Objects;

public class City {
    private String name;
    private int population;
    private int year;

    private volatile int hashCode;

    protected City() {
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    void setPopulation(int population) {
        this.population = population;
    }

    public int getYear() {
        return year;
    }

    void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "City name: " + name + "\n" +
                "Population: " + population + "\n" +
                "Year of foundation: " + year + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof City secondCity)) return false;

        return population == secondCity.population
                && year == secondCity.year
                && name.equalsIgnoreCase(secondCity.name);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = Objects.hash(population, year);
            result = result*31 + name.hashCode();
            hashCode = result;
        }
        return result;
    }
}

