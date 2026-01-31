package org.example.build;

import org.example.City;

import java.util.Objects;

/**
 * Класс реализующий интерфейс ICityBuilder, концепт для создания
 * объектов City. Содержит поля аналогичные с классом City,
 * позволяет предварительно сформировать город-претендент, прежде чем
 * создать объект City. Для создания объектов City достаточно одного
 * концепта (объект CityConcept) на поток, созданного через new
 * CityConcept(name) или new CityConcept(). Реализация не поток-безопасная,
 * требуется на один поток по одному экземпляру, если требуется создавать
 * объекты City в несколько потоков.
 */
public class CityConcept implements ICityBuilder {
    private String name;
    private int population;
    private int year;

    /**
     * Конструктор с одним параметром, позволяет задать название
     * города-претендента.
     * @param name название города
     */
    public CityConcept(String name) {
        Objects.requireNonNull(name);
        setName(name);
    }

    /**
     * Конструктор без параметров.
     */
    public CityConcept() {
    }

    /**
     * Задания названия города.
     * @param name название города
     * @return объект реализующий интерфейс ICityBuilder
     */
    @Override
    public ICityBuilder setName(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }

    /**
     * Задание количества населения города.
     * @param population количество населения
     * @return объект реализующий интерфейс ICityBuilder
     */
    @Override
    public ICityBuilder setPopulation(int population) {
        this.population = population;
        return this;
    }

    /**
     * Задание года основания города.
     * @param year год основания города
     * @return объект реализующий интерфейс ICityBuilder
     */
    @Override
    public ICityBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    /**
     * Из заполненных полей города-претендента получить объект City.
     * @return объект City.
     */
    @Override
    public City getCityAfterBuild() {
        City city = new City();
        city.setName(name);
        city.setPopulation(population);
        city.setYear(year);
        return city;
    }
}
