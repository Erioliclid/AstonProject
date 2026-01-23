package org.example;

public class City {
    protected String name;
    protected int population;
    protected int year;

    protected City() {};

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

}

