package org.example.country;

public enum Alphabet {
    EN ("[a-zA-Z]", 'a', 'z', 'A', 'Z', 0),
    RU ("[а-яА-я]", 'а', 'я', 'А', 'Я', 1);

    private final String letters;
    private final int id;
    private static int quantity;
    private final char firstLowercase;
    private final char lastLowercase;
    private final char firstCapital;
    private final char lastCapital;

    private static int addQuantity() {
        return quantity++;
    }

    Alphabet(String alphabet, char startLowercase, char endLowercase, char startCapital, char endCapital, int id) {
        this.letters = alphabet;
        this.firstLowercase = startLowercase;
        this.lastLowercase = endLowercase;
        this.firstCapital = startCapital;
        this.lastCapital = endCapital;
        this.id = addQuantity();
    };

    public String getRegExp() {
        return this.letters;
    }

    public int getId() {
        return this.id;
    }

    public static int getQuantity() {
        return quantity;
    }

    public int firstLowercase() {
        return (int) this.firstLowercase;
    }

    public int lastLowercase() {
        return (int) this.lastLowercase;
    }

    public int firstCapital() {
        return (int) this.firstCapital;
    }

    public int lastCapital() {
        return (int) this.lastCapital;
    }
}