package org.example.menu;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void mainMenu() {
        clear();
        System.out.println("""
                ==============================
                Приложение *Сортировка городов*
                ==============================
                1. Ввод данных
                2. Сортировка данных
                3. Сохранение в файл
                4. Многопоточный подсчет
                0. Выход
                ------------------------------
                Выберите нужный пункт..:                
                """);

    }

    public boolean mainMenuChoice() {
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                dataInputMenu();
                break;
            case "2":
                sortingMenu();
                break;
            case "3":
                saveMenu();
                break;
            case "4":
                threadCounting();
                //многопоточка
                break;
            case "0":
                System.out.println("Выход из приложения");
                return false;
            default:
                System.out.println("Такого значения нет. Нажмите Enter и повторите попытку");
                scanner.nextLine();
        }
        return true;
    }

    private void dataInputMenu() {
        clear();
        System.out.println("""
                ==============================
                          Ввод данных
                ==============================
                1. Ввод вручную
                2. Случайная генерация
                3. Загрузить из файла
                0. Назад
                ------------------------------
                Выберите нужный пункт..:                
                """);

        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                System.out.println("Выбран ввод вручную");
                //Вызов метода ввода
                break;
            case "2":
                System.out.println("Генерирую случайные данные");
                //Вызов метода рандома
                break;
            case "3":
                System.out.println("Загрузка из файла..");
                //Вызов загрузки
                break;
            case "0":
                return;
            default:
                System.out.println("Такого значения нет. Нажмите Enter и повторите попытку");
                scanner.nextLine();
        }
    }

    private void sortingMenu() {
        clear();
        System.out.println("""
                ==============================
                          Сортировка
                ==============================
                1. По названию
                2. По году основания
                3. По населению
                4. По чет/нечет
                0. Назад
                ------------------------------
                Выберите нужный пункт..:                
                """);
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                System.out.println("Сортирую по названию..");
                //Впихнуть сортировку
                break;
            case "2":
                System.out.println("Сортирую по году основания..");
                //Впихнуть сортировку
                break;
            case "3":
                System.out.println("Сортирую по населению..");
                //Впихнуть сортировку
                break;
            case "4":
                System.out.println("Сортирую по чет/нечет..");
                //Впихнуть сортировку
            case "0":
                return;
            default:
                System.out.println("Такого значения нет. Нажмите Enter и повторите попытку");
                scanner.nextLine();

        }
    }

    private void saveMenu() {
        clear();
        System.out.println("""
                ==============================
                       Сохранение в файл
                ==============================
                1. Сохранить файл
                2. Добавить в существующий файл
                0. Назад
                ------------------------------
                Выберите нужный пункт..:                
                """);
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                System.out.println("Сохранение файла..");
                //Впихнуть метод сохранения
                break;
            case "2":
                System.out.println("Добавление данных в файл..");
                //Впихнуть метод добавления
                break;
            case "0":
                return;
            default:
                System.out.println("Такого значения нет. Нажмите Enter и повторите попытку");
                scanner.nextLine();
        }
    }

    private void threadCounting(){
        System.out.println("Многопоточный подсчет");
        // ??
    }
    private void clear(){
        for (int i = 0; i < 30; i++) {
            System.out.println();
            }
    }


}
// все менюшки скинуть в один класс и использовать их реализацию?
// вывод для многопоточки?
// консольный вызов?
// добавить состояния в апстейт для корректной работы приложения
