package com.company.parsingHTML.logic.parsing;

import com.company.parsingHTML.logic.observer.ObservableParing;
import com.company.parsingHTML.logic.observer.ObserverParing;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Абстрактный класс реализуюший основной функционал интерфейса Parser.
 */
public abstract class ParserAbstract<S, T> implements Parser<S, T>, ObserverParing<T,S> {
    private static final Logger LOGGER = LogManager.getLogger(ParserAbstract.class);
    /**
     * Объект в который записываем полученую во время парсинга информацию.
     */
    private ObservableParing<T,S> observableParing;

    /**
     * Создание Parser.
     */
    public ParserAbstract() {
        this.observableParing = new ObservableParing<>();
        LOGGER.info("Create " + toString());
    }


    public ObservableParing<T,S> getObservableParing() {
        return observableParing;
    }

    @Override
    public void update(T t,S s) {
        LOGGER.debug("update t = "+t.toString()+" s = "+s.toString());
        if (isParsing(t)) {
            LOGGER.debug("parsing");
            parsing(t,s);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
