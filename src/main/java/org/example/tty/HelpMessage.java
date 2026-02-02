package org.example.tty;

public class HelpMessage {
    public static void format() {
        //Предлагаю изменить формат данных понятные для пользователя сделать
        // + Назачение полей сделать первой строкой
        System.out.println("Назначение полей: Название | численность населения | год основания [| правило страны]");
        System.out.println("Формат входных данных: S d I d I [d S]");
        System.out.println("""
                Где:
                \tS - строка, тип String;
                \tI - число, тип int
                \td - разделитель полей, допустимые символы: . , ; |
                \t[ ] - (символы [ ] не вводить) необязательное поле, допустимые значения: RU UN""");
    }

    public static void invitation() {
        System.out.print("#: ");
    }

    public static void fileName() {
        System.out.println("Введите путь и название файла с списком городов.");
        invitation();
    }
}
