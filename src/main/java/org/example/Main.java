package org.example;

import org.example.appConfig.AppManager;

public class Main {
    public static void main(String[] args) {
        new Main().startApp();
    }

    public void startApp(){
        AppManager appManager = new AppManager();
        appManager.run();
    }
}