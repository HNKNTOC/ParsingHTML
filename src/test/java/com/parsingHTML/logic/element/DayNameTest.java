package com.parsingHTML.logic.element;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тест для DayName.
 */
public class DayNameTest {

    private static final String DAY_NAME[] =
            {"Понедельник"
                    , "Вторник"
                    , "Среда"
                    , "Четверг"
                    , "Пятница"
                    , "Суббота"
                    , "Воскресенье"};

    private static final String DAY_NAME_SHORT[] = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};
    private static final String DAY_NAME_SHORT_LOWER_CASE[] = {"пн", "вт", "ср", "чт", "пт", "сб", "вс"};

    @Test
    public void getNameRu() throws Exception {
        assertEquals(DayName.MONDAY.getName(), DAY_NAME[0]);
        assertEquals(DayName.TUESDAY.getName(), DAY_NAME[1]);
        assertEquals(DayName.WEDNESDAY.getName(), DAY_NAME[2]);
        assertEquals(DayName.THURSDAY.getName(), DAY_NAME[3]);
        assertEquals(DayName.FRIDAY.getName(), DAY_NAME[4]);
        assertEquals(DayName.SATURDAY.getName(), DAY_NAME[5]);
        assertEquals(DayName.SUNDAY.getName(), DAY_NAME[6]);
    }

    @Test
    public void getNameShort() throws Exception {
        assertEquals(DayName.MONDAY.getNameShort(), DAY_NAME_SHORT[0]);
        assertEquals(DayName.TUESDAY.getNameShort(), DAY_NAME_SHORT[1]);
        assertEquals(DayName.WEDNESDAY.getNameShort(), DAY_NAME_SHORT[2]);
        assertEquals(DayName.THURSDAY.getNameShort(), DAY_NAME_SHORT[3]);
        assertEquals(DayName.FRIDAY.getNameShort(), DAY_NAME_SHORT[4]);
        assertEquals(DayName.SATURDAY.getNameShort(), DAY_NAME_SHORT[5]);
        assertEquals(DayName.SUNDAY.getNameShort(), DAY_NAME_SHORT[6]);
    }

    @Test
    public void valueOfNameShortTest() throws Exception {
        valueSubstitution(DAY_NAME_SHORT);
    }

    @Test
    public void valueOfNameShortLowerCase() throws Exception {
        valueSubstitution(DAY_NAME_SHORT_LOWER_CASE);
    }

    private void valueSubstitution(final String[] value) {
        assertEquals(DayName.MONDAY, DayName.valueOfNameShort(value[0]));
        assertEquals(DayName.TUESDAY, DayName.valueOfNameShort(value[1]));
        assertEquals(DayName.WEDNESDAY, DayName.valueOfNameShort(value[2]));
        assertEquals(DayName.THURSDAY, DayName.valueOfNameShort(value[3]));
        assertEquals(DayName.FRIDAY, DayName.valueOfNameShort(value[4]));
        assertEquals(DayName.SATURDAY, DayName.valueOfNameShort(value[5]));
        assertEquals(DayName.SUNDAY, DayName.valueOfNameShort(value[6]));
    }

}