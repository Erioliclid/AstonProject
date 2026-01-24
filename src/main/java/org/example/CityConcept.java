package org.example;

import java.util.Objects;

public class CityConcept implements ICityBuilder {
    private String name;
    private int population;
    private int year;


    public CityConcept(String name) {
        Objects.requireNonNull(name);
        setName(name);
    }

    public CityConcept() {
    }

    @Override
    public ICityBuilder setName(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }

    @Override
    public ICityBuilder setPopulation(int population) {
        this.population = population;
        return this;
    }

    @Override
    public ICityBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    @Override
    public City getCityAfterBuild() {
        City city = new City();
        city.name = this.name;
        city.population = this.population;
        city.year = this.year;
        return city;
    }
}
