package org.example.tty;

public class HelpMessage {
    public static void format() {
        System.out.println("Формат входных данных: S d I d I [d S]");
        System.out.println("Где:\n\tS - строка, тип String;\n\tI - число, тип int\n"
                + "\td - разделитель полей, допустимые символы: , ; |\n"
                + "\t[ ] - (символы [ ] не вводить) необязательное поле, допустимые значения: RU UN");
        System.out.println("Назначение полей: Название | численность населения | год основания [| правило страны]");
    }
}
