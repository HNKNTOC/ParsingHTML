package com.parsingHTML.logic.element;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тест для NumeratorName.
 */
public class NumeratorNameTest {
    @Test
    public void getName() throws Exception {
        assertEquals(NumeratorName.NUMERATOR.getName(), "Числ.");
        assertEquals(NumeratorName.DENOMINATOR.getName(), "Знам.");
        assertEquals(NumeratorName.EMPTY.getName(), "empty");
    }

    @Test
    public void fromString() throws Exception {
        assertEquals(NumeratorName.NUMERATOR, NumeratorName.fromString("Числ."));
        assertEquals(NumeratorName.DENOMINATOR, NumeratorName.fromString("Знам."));
        assertEquals(NumeratorName.EMPTY, NumeratorName.fromString("empty"));
    }

}