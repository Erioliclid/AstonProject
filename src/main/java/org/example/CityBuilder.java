package org.example;

public class CityBuilder {
    private City cityInConstruction;

    public CityBuilder(String name) {
        if (name == null)
            throw new NullPointerException("class CityBuilder: CityBuilder( null )");

        cityInConstruction = new City();
        cityInConstruction.name = name;
    }

    public CityBuilder setPopulation(int population) {
        cityInConstruction.population = population;
        return this;
    }

    public CityBuilder setYear(int year) {
        cityInConstruction.year = year;
        return this;
    }

    public City getCityAfterBuild() {
        return cityInConstruction;
    }
}
