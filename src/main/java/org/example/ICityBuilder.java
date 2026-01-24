package org.example;

public interface ICityBuilder {

    public abstract ICityBuilder setName(String name);

    public abstract ICityBuilder setPopulation(int population);

    public abstract ICityBuilder setYear(int year);

    public abstract City getCityAfterBuild();
}
