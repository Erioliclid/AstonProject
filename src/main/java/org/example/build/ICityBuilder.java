package org.example.build;

import org.example.City;

public interface ICityBuilder {

    ICityBuilder setName(String name);

    ICityBuilder setPopulation(int population);

    ICityBuilder setYear(int year);

    City getCityAfterBuild();
}
