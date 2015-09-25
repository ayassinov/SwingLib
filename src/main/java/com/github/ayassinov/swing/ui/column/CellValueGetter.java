package com.github.ayassinov.swing.ui.column;

/**
 * @author ayassinov on 25/09/2015.
 */
public interface CellValueGetter<M, T> {

    T onGet(M rowObject);
}
