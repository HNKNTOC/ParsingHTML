package com.parsingHTML.logic.element;

/**
 * Значения для нумератора.
 */
public enum NumeratorName {
    NUMERATOR("Числ."), DENOMINATOR("Знам."), EMPTY("empty");

    public String name;

    NumeratorName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static NumeratorName fromString(String text) {
        if (text != null) {
            for (NumeratorName name : NumeratorName.values()) {
                if (text.equalsIgnoreCase(name.getName())) {
                    return name;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "NumeratorName{" +
                "name='" + name + '\'' +
                '}';
    }
}
