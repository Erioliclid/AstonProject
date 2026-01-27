package org.example.appConfig;

import org.example.menu.Menu;

public class AppManager {
    public void run() {

        Menu menu = new Menu();
        boolean running = true;

        while (running) {
            menu.mainMenu();
            running = menu.mainMenuChoice();
        }
    }
}

