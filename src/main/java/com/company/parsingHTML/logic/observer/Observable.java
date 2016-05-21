package com.company.parsingHTML.logic.observer;

import java.util.List;

/**
 * Интерфейс, определяющий методы для добавления, удаления и оповещения наблюдателей.
 *
 * @param <T> Обьект который будет передоватся ObserverParing.
 * @param <S> объект в который будем записывать
 *           полученную информацию во время парсинга.
 */
interface Observable<T, S> {
    /**
     * Добавить слушателя.
     */
    void addObserver(ObserverParing<T, S> observerParing);

    /**
     * Удолить слушателя.
     */
    void removeObserver(ObserverParing<T, S> observerParing);

    /**
     * Оповистить всех слушателей.
     *
     */
    void notifyObserver(T t,S s);

    List<ObserverParing<T, S>> getListObserverParing();
}
