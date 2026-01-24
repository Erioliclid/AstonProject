package org.example;

import java.util.Objects;

public class City implements Comparable<City> {
    protected String name;
    protected int population;
    protected int year;

    private volatile int hashCode;

    protected City() {
    }

/*
    public City(String name, int population, int year) {
        this.name = name;
        this.population = population;
        this.year = year;
    }
*/

    public String getName() {
        return name;
    }

/*
    public void setName(String name) {
        this.name = name;
    }
*/

    public int getPopulation() {
        return population;
    }

/*
    public void setPopulation(int population) {
        this.population = population;
    }
*/

    public int getYear() {
        return year;
    }

/*
    public void setYear(int year) {
        this.year = year;
    }
*/

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

    @Override
    public int compareTo(City city) {
        return (name == city.name) ? 0 : name.compareTo(city.name);
    }
}

