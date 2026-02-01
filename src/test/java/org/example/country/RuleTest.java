package org.example.country;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class RuleTest {

    @Test
    void getAlphabet() {
        assertAll(
                "Get alphabet from Enum Rule",
                () -> assertEquals(Alphabet.EN, Rule.UN.getAlphabet()),
                () -> assertEquals(Alphabet.RU, Rule.RU.getAlphabet()),
                () -> assertEquals(Alphabet.RU, Rule.UA.getAlphabet()),
                () -> assertEquals(Alphabet.RU, Rule.UZ.getAlphabet()),
                () -> assertEquals(Alphabet.RU, Rule.MD.getAlphabet()),
                () -> assertEquals(Alphabet.RU, Rule.KG.getAlphabet()),
                () -> assertEquals(Alphabet.RU, Rule.TJ.getAlphabet()),
                () -> assertEquals(Alphabet.EN, Rule.AU.getAlphabet()),
                () -> assertEquals(Alphabet.EN, Rule.IN.getAlphabet()),
                () -> assertEquals(Alphabet.EN, Rule.JP.getAlphabet()),
                () -> assertEquals(Alphabet.EN, Rule.DEFAULT.getAlphabet())
        );
    }

    @Test
    void getMinPopulationForCityStatus() {
        assertAll(
                "Get minimum population for city status from Enum Rule",
                () -> assertEquals(20_000, Rule.UN.getMinPopulationForCityStatus()),
                () -> assertEquals(12_000, Rule.RU.getMinPopulationForCityStatus()),
                () -> assertEquals(10_000, Rule.UA.getMinPopulationForCityStatus()),
                () -> assertEquals(7_000, Rule.UZ.getMinPopulationForCityStatus()),
                () -> assertEquals(10_000, Rule.MD.getMinPopulationForCityStatus()),
                () -> assertEquals(10_000, Rule.KG.getMinPopulationForCityStatus()),
                () -> assertEquals(10_000, Rule.TJ.getMinPopulationForCityStatus()),
                () -> assertEquals(250, Rule.AU.getMinPopulationForCityStatus()),
                () -> assertEquals(20_000, Rule.IN.getMinPopulationForCityStatus()),
                () -> assertEquals(30_000, Rule.JP.getMinPopulationForCityStatus()),
                () -> assertEquals(20_000, Rule.DEFAULT.getMinPopulationForCityStatus())
        );
    }

    @Test
    void getQuantity() {
        assertEquals(11, Rule.getQuantity());
    }
}