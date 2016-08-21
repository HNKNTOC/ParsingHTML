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
    public void valueOfNameShort() throws Exception {
        assertEquals(DayName.MONDAY, DayName.valueOfNameShort(DAY_NAME_SHORT[0]));
        assertEquals(DayName.TUESDAY, DayName.valueOfNameShort(DAY_NAME_SHORT[1]));
        assertEquals(DayName.WEDNESDAY, DayName.valueOfNameShort(DAY_NAME_SHORT[2]));
        assertEquals(DayName.THURSDAY, DayName.valueOfNameShort(DAY_NAME_SHORT[3]));
        assertEquals(DayName.FRIDAY, DayName.valueOfNameShort(DAY_NAME_SHORT[4]));
        assertEquals(DayName.SATURDAY, DayName.valueOfNameShort(DAY_NAME_SHORT[5]));
        assertEquals(DayName.SUNDAY, DayName.valueOfNameShort(DAY_NAME_SHORT[6]));
    }

}