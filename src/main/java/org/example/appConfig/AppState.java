package org.example.appConfig;

import org.example.City;
import org.example.CityArrayList.CityArrayList;

public class AppState {
    private CityArrayList<City> currentCities;
    private boolean cityLoaded = false;

    public AppState() {
        this.currentCities = new CityArrayList<>();
    }

    public CityArrayList<City> getCurrentCities() {
        return currentCities;
    }

    public void setCurrentCities(CityArrayList<City> cities) {
        this.currentCities = cities;
        this.cityLoaded = cities != null && !cities.isEmpty();
    }

    public boolean isCityLoaded() {
        return cityLoaded;
    }

}

