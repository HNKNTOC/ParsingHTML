package com.parsingHTML.logic.element;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Значения для нумератора.
 */
public enum NumeratorName {
    NUMERATOR("Числ."), DENOMINATOR("Знам."), EMPTY("empty");

    private static final Logger LOGGER = LogManager.getLogger(NumeratorName.class);
    /**
     * Нужен для получения NumeratorName по соответствующему name.
     */
    private static final Map<String, NumeratorName> MAP_VALUE = new HashMap<>();
    public String name;

    static {
        for (NumeratorName numeratorName : NumeratorName.values()) {
            MAP_VALUE.put(numeratorName.getName(), numeratorName);
        }
    }

    NumeratorName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static NumeratorName fromString(String name) {
        NumeratorName numeratorName = MAP_VALUE.get(name);
        if (numeratorName == null) {
            LOGGER.warn("fromString return null!");
        }
        return numeratorName;
    }

    @Override
    public String toString() {
        return "NumeratorName{" +
                "name='" + name + '\'' +
                '}';
    }
}
