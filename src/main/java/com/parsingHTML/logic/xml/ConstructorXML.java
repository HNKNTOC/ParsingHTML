package com.parsingHTML.logic.xml;

/**
 * Принимает данные и преобразует их в T.
 */
public interface ConstructorXML<T> {
    /**
     * Обработать новый элемент.
     * @param elementxml элемент который нужно обработать.
     * @return false если обработать элемент не удалось.
     */
    boolean newElement(ElementXML elementxml);

    /**
     * Получить расписание в виде T.
     * @return T.
     */
    T getXML();

    /**
     * Очистить уже обработанные элементы.
     */
    void clear();
}
