package com.github.ayassinov.swing.ui.table;

/**
 * @author ayassinov on 21/09/15.
 */
public interface UpdatableTableCell<T> {

    Object getValueAtColumnIndex(T value, int columnIndex);

    void setValueAtColumnIndex(T value, Object newValue, int columnIndex);
}
