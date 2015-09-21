package com.github.ayassinov.swing.ui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ayassinov on 21/09/15.
 */
public class BaseTableModel<T> extends AbstractTableModel {

    private final List<T> values;
    private final List<Column> columns;
    private final UpdatableTableCell<T> updatableTableCell;

    public BaseTableModel(List<Column> columns, UpdatableTableCell<T> updatableTableCell) {
        this.updatableTableCell = updatableTableCell;
        this.columns = columns;
        this.values = new ArrayList<T>();
    }

    public BaseTableModel(List<Column> columns, List<T> values, UpdatableTableCell<T> updatableTableCell) {
        this.updatableTableCell = updatableTableCell;
        this.columns = columns;
        this.values = values;
    }

    public int getRowCount() {
        return values.size();
    }

    public int getColumnCount() {
        return columns.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0)
            return null;

        if (this.values.isEmpty())
            return null;

        return updatableTableCell.getValueAtColumnIndex(this.values.get(rowIndex), columnIndex);
    }


    @Override
    public String getColumnName(int column) {
        return columns.get(column).getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex).getClazz();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columns.get(columnIndex).isEditable();
        //return super.isCellEditable(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //super.setValueAt(aValue, rowIndex, columnIndex);
        this.updatableTableCell.setValueAtColumnIndex(this.values.get(rowIndex), aValue, columnIndex);
    }

    public void setValues(List<T> values) {
        this.values.clear();
        this.values.addAll(values);
        fireTableDataChanged();
    }


    public T getValue(int index) {
        if (index < 0)
            return null;

        if (index > this.values.size())
            return null;

        return this.values.get(index);
    }

    public List<Column> getColumns() {
        return columns;
    }
}
