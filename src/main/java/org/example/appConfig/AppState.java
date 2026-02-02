package org.example.appConfig;

import org.example.City;

public class AppState {
    private City[] currentCities; //список с городами
    private boolean cityLoaded = false; //проверка загружен ли список

    public City[] getCurrentCities() {
        return currentCities;
    }

    public void setCurrentCities(City[] cities) {
        this.currentCities = cities;
        this.cityLoaded = cities != null && cities.length > 0;
    }

    public boolean isCityLoaded() {
        return cityLoaded;
    }

    public void clearCity(){
        this.currentCities = null;
        this.cityLoaded = false;
    }

}
