package com.github.ayassinov.swing.ui.column;

/**
 * @author ayassinov on 25/09/2015.
 */
public interface CellValueSetter<M, T> {

    void onUpdate(M rowObject, T newValue);
}
