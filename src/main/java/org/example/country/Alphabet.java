package org.example.country;

/**
 * Класс-перечисление Alphabet содержит данные об компьютерном представлении русского м английского алфавитов, такие
 * как: регулярное выражение для букв алфавита; первая строчная буква; последняя строчная буква; первая прописная буква;
 * последняя прописная буква. Не учитывается тот факт, что расположение символов зависит от таблицы кодировки (кодовая
 * страница), в некоторых случаях символы расположены с разрывами, в разрывах находятся иные символы, не относящиеся к
 * алфавиту.
 */
public enum Alphabet {
    EN ("[a-zA-Z]", 'a', 'z', 'A', 'Z'),
    RU ("[а-яёА-ЯЁ]", 'а', 'я', 'А', 'Я');

    private final String letters;
    private final int id;
    private static int quantity;
    private final char firstLowercase;
    private final char lastLowercase;
    private final char firstCapital;
    private final char lastCapital;

    /**
     * Подсчет количества элементов в перечислении
     * @return идентификатор текущего объекта перечисления
     */
    private static int addQuantity() {
        return quantity++;
    }

    /**
     * Конструктор элементов перечисления алфавита.
     *
     * @param alphabet       регулярное выражения для одной буквы алфавита
     * @param startLowercase первая строчная буква алфавита
     * @param endLowercase   последняя строчная буква алфавита
     * @param startCapital   первая прописная буква алфавита
     * @param endCapital     последняя прописная буква алфавита
     */
    Alphabet(String alphabet, char startLowercase, char endLowercase, char startCapital, char endCapital) {
        this.letters = alphabet;
        this.firstLowercase = startLowercase;
        this.lastLowercase = endLowercase;
        this.firstCapital = startCapital;
        this.lastCapital = endCapital;
        this.id = addQuantity();
    }

    /**
     * Возвращает регулярное выражение для одной буквы алфавита.
     *
     * @return регулярное выражение
     */
    public String getRegExp() {
        return this.letters;
    }

    /**
     * Возвращает идентификатор элемента перечисления.
     *
     * @return идентификатор элемента перечисления
     */
    public int getId() {
        return this.id;
    }

    /**
     * Возвращает общее количество элементов в классе-перечислении.
     *
     * @return количество элементов в классе-перечислении
     */
    public static int getQuantity() {
        return quantity;
    }

    /**
     * Возвращает первую строчную букву алфавита.
     *
     * @return первая строчная буква алфавита
     */
    public int firstLowercase() {
        return this.firstLowercase;
    }

    /**
     * Возвращает последнюю строчную букву алфавита.
     *
     * @return последняя строчная буква алфавита
     */
    public int lastLowercase() {
        return this.lastLowercase;
    }

    /**
     * Возвращает первую прописную букву алфавита.
     *
     * @return первая прописная буква алфавита
     */
    public int firstCapital() {
        return this.firstCapital;
    }

    /**
     * Возвращает последнюю прописную букву алфавита.
     *
     * @return последнюю прописную букву алфавита
     */
    public int lastCapital() {
        return this.lastCapital;
    }
}