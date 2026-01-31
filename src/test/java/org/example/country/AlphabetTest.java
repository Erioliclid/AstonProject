package org.example.country;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class AlphabetTest {

    @Test
    void getRegExp() {
        assertEquals("[a-zA-Z]", Alphabet.EN.getRegExp());
        assertEquals("[а-яёА-ЯЁ]", Alphabet.RU.getRegExp());
    }

    @Test
    void getId() {
        assertEquals(0, Alphabet.EN.getId());
        assertEquals(1, Alphabet.RU.getId());
    }

    @Test
    void getQuantity() {
        assertEquals(2, Alphabet.getQuantity());
    }

    @Test
    void firstLowercase() {
        assertEquals('a', Alphabet.EN.firstLowercase());
        assertEquals('а', Alphabet.RU.firstLowercase());
    }

    @Test
    void lastLowercase() {
        assertEquals('z', Alphabet.EN.lastLowercase());
        assertEquals('я', Alphabet.RU.lastLowercase());
    }

    @Test
    void firstCapital() {
        assertEquals('A', Alphabet.EN.firstCapital());
        assertEquals('А', Alphabet.RU.firstCapital());
    }

    @Test
    void lastCapital() {
        assertEquals('Z', Alphabet.EN.lastCapital());
        assertEquals('Я', Alphabet.RU.lastCapital());
    }

    @Test
    void values() {
        HashSet<Alphabet> allValue = new HashSet<>();

        allValue.add(Alphabet.EN);
        allValue.add(Alphabet.RU);

        Arrays.stream(Alphabet.values()).toList().forEach(allValue::remove);
        assertTrue(allValue.isEmpty());
    }

    @Test
    void valueOf() {
        assertSame(Alphabet.EN, Alphabet.valueOf("EN"));
        assertSame(Alphabet.RU, Alphabet.valueOf("RU"));
        assertSame(Alphabet.EN, Alphabet.valueOf(Alphabet.class, "EN"));
        assertSame(Alphabet.RU, Alphabet.valueOf(Alphabet.class, "RU"));
    }

    @Test
    void ordinal() {
        assertEquals(0, Alphabet.EN.ordinal());
        assertEquals(1, Alphabet.RU.ordinal());
    }

    @Test
    void toStringTest() {
        assertEquals("EN", Alphabet.EN.toString());
        assertEquals("RU", Alphabet.RU.toString());
    }
}