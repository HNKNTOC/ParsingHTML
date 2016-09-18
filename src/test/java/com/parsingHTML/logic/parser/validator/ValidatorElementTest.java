package com.parsingHTML.logic.parser.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;


public class ValidatorElementTest {

    @Test
    public void nextInterpreterElementEqualNull() throws Exception {
        ValidatorElement validatorElement = new ValidatorElement();
        assertFalse(validatorElement.valid(null));
    }

    @Test
    public void nextInterpreterElementNotNull() throws Exception {
        ValidatorElement validatorElement = new ValidatorElement(new ValidatorElement());
        assertFalse(validatorElement.valid(null));
    }

}