package org.example.appConfig;

import org.example.City;
//делаю по *готовому* сити, но его явно нужно переделывать(инкапсуляция отсутсвует.
// билдер не реализован адекватно. про енам молчу вообще)
//после и мне придется переделать свой класс. люблю двойную работу
// (если класс, конечно, поменяется) или добавлять защиту и копии в этот класс(выполняя чужую работу)

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
