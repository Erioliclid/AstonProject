package org.example.build;

import org.example.City;

/**
 * Интерфейс для обобщения концептов (шаблон Строитель).
 * Концепты должны содержать в себе все необходимые поля
 * создаваемого (предполагаемого) экземпляра объекта. В
 * процессе работы с концептом, заполняются поля, тем самым
 * подготавливают "претендента", верификация и "выпуск" из
 * претендента экземпляра объекта осуществляется "директором":
 * CityDirector метод cityDevelopment
 */
public interface ICityBuilder {

    /**
     * Задание название города-претендента объекта City.
     * @param name название города
     * @return объект реализующий интерфейс ICityBuilder
     */
    ICityBuilder setName(String name);

    /**
     * Задание количества населения в городе-претенденте объекта City.
     * @param population количество населения
     * @return объект реализующий интерфейс ICityBuilder
     */
    ICityBuilder setPopulation(int population);

    /**
     * Задание года основания города-претендента объекта City.
     * @param year год основания города
     * @return объект реализующий интерфейс ICityBuilder
     */
    ICityBuilder setYear(int year);

    /**
     * Из полей города-претендента получить объект City с заполненными
     * соответствующим образом полями.
     * @return экземпляр объекта City.
     */
    City getCityAfterBuild();
}
