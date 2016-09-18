package com.parsingHTML.logic.parser.validator;

import com.parsingHTML.logic.parser.exception.ExceptionList;
import com.parsingHTML.logic.parser.exception.ExceptionManager;
import com.parsingHTML.logic.parser.exception.ExceptionValidationElement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * Проверяет данные на валидность.
 */
public class ValidatorElement implements ExceptionManager {
    private static final Logger LOGGER = LogManager.getLogger(ValidatorElement.class);

    private ExceptionList<ExceptionValidationElement> exceptions
            = new ExceptionList<>();
    /**
     * Вложенны ValidatorElement который тожо проверяет {@link Element};
     */
    private ValidatorElement nextInterpreter = null;

    public ValidatorElement() {
        LOGGER.debug("Create " + this);
    }

    public ValidatorElement(ValidatorElement nextInterpreter) {
        LOGGER.debug("Create " + this);
        this.nextInterpreter = nextInterpreter;
    }

    /**
     * Проверка проходит ли прроверку данный {@link Element}
     *
     * @param element {@link Element} который нужно проверить.
     * @return true если Element прошол проверку.
     */
    public boolean valid(Element element) {
        LOGGER.debug("valid() element = " + element);
        boolean returnResult = check(element) && nextInterpreter != null && nextInterpreter.valid(element);
        LOGGER.debug("valid() return " + returnResult);
        return returnResult;
    }

    protected boolean check(Element element) {
        //Стандартная реализация.
        return false;
    }

    private void fail(String message) {
        exceptions.add(new ExceptionValidationElement(message));
    }

    @Override
    public String toString() {
        return "ValidatorElement{" +
                "exception=" + exceptions +
                ", nextInterpreter=" + nextInterpreter +
                '}';
    }

    @Override
    public ExceptionList getExceptionList() {
        return exceptions;
    }
}
