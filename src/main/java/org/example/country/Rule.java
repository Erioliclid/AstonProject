package org.example.country;

public enum Rule {
    UN (Alphabet.EN, 20_000), // ООН
    RU (Alphabet.RU, 12_000), // Россия
    UA (Alphabet.RU, 10_000), // Украина
    UZ (Alphabet.RU, 7_000), // Узбекистан
    MD (Alphabet.RU, 10_000), // Республика Молдова
    KG (Alphabet.RU, 10_000), // Киргизия
    TJ (Alphabet.RU, 10_000), // Таджикистан
    AU (Alphabet.EN, 250), // Австралия
    IN (Alphabet.EN, 20_000), // Индия
    JP (Alphabet.EN, 30_000), // Япония
    DEFAULT (Alphabet.EN, 20_000); // Без указания правил страны, равносильно выбору UN

    private final Alphabet alphabet;
    private final int minPopulationForCityStatus;
    private static int quantity;

    private static void addQuantity() {
        quantity++;
    }

    Rule (Alphabet alphabet, int minPopulationForCityStatus) {
        this.alphabet = alphabet;
        this.minPopulationForCityStatus = minPopulationForCityStatus;
        addQuantity();
    }

    public Alphabet getAlphabet() {
        return this.alphabet;
    }

    public int getMinPopulationForCityStatus() {
        return this.minPopulationForCityStatus;
    }

    public static int getQuantity() {
        return quantity;
    }
}
