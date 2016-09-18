package com.parsingHTML.logic.parser.validator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * Проверяет данные на валидность.
 */
public class ValidatorElement {
    private static final Logger LOGGER = LogManager.getLogger(ValidatorElement.class);

    /**
     * Вложенны ValidatorElement который тожо проверяет {@link Element};
     */
    private ValidatorElement AttachedInterpreter = null;

    public ValidatorElement() {
        LOGGER.debug("Create " + this);
    }

    public ValidatorElement(ValidatorElement AttachedInterpreter) {
        LOGGER.debug("Create " + this);
        this.AttachedInterpreter = AttachedInterpreter;
    }

    /**
     * Проверка проходит ли прроверку данный {@link Element}
     *
     * @param element {@link Element} который нужно проверить.
     * @return true если Element прошол проверку.
     */
    public boolean valid(Element element) {
        LOGGER.debug("valid() element = " + element);
        boolean returnResult = check(element) && AttachedInterpreter != null && AttachedInterpreter.valid(element);
        LOGGER.debug("valid() return " + returnResult);
        return returnResult;
    }

    protected boolean check(Element element) {
        //Стандартная реализация.
        return false;
    }

    @Override
    public String toString() {
        return "ValidatorElement{" +
                "AttachedInterpreter=" + AttachedInterpreter +
                '}';
    }
}
