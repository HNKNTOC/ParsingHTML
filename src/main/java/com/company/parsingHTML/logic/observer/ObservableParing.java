package com.company.parsingHTML.logic.observer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Наблюдаемый парсер.
 */
public class ObservableParing<T,S> implements Observable<T,S> {
    private static final Logger LOGGER = LogManager.getLogger(ObservableParing.class);
    private final List<ObserverParing<T,S>> observerList;

    public ObservableParing() {
        observerList = new ArrayList<>();
        LOGGER.info("Create "+toString());
    }

    @Override
    public List<ObserverParing<T,S>> getListObserverParing() {
        LOGGER.debug("getListObserverParing "+ observerList.toString());
        return observerList;
    }

    @Override
    public void addObserver(ObserverParing<T,S> observerParing) {
        LOGGER.debug("addObserver "+observerParing.toString());
        observerList.add(observerParing);
    }

    @Override
    public void removeObserver(ObserverParing<T,S> observerParing) {
        LOGGER.debug("removeObserver "+observerParing.toString());
        observerList.remove(observerParing);
    }

    @Override
    public void notifyObserver(T t,S s) {
        LOGGER.debug("notifyObserver T = "+t.toString() +" s = "+s.toString());
        LOGGER.debug("observerList= "+observerList.toString());
        for (ObserverParing<T,S> tObserverParing : observerList) {
            tObserverParing.update(t,s);
        }
    }

    @Override
    public String toString() {
        return "ObservableParing{" +
                "observerList=" + observerList +
                '}';
    }
}
