package com.github.ayassinov.swing.ui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ayassinov on 19/09/15.
 */
public abstract class BaseTable<T> extends AbstractTableModel {

    private final JTable table;
    private final List<T> values = new ArrayList<T>();
    private final List<Column> columns = new ArrayList<Column>();

    public abstract Object getValueAtColumnIndex(T value, int columnIndex);

    public BaseTable(List<Column> columns) {
        //this.table.setModel(this);
        this.columns.addAll(columns);
        this.table = new JTable(this);
    }

    public int getRowCount() {
        return this.values.size();
    }

    public int getColumnCount() {
        return columns.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0)
            return null;
        return getValueAtColumnIndex(this.values.get(rowIndex), columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column).getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex).getClazz();
    }

    public T getValueOfTheSelectedRow() {
        if (table.getSelectedRow() < 0) {
            return null;
        }

        final int selectedIndex = table.getRowSorter().convertRowIndexToModel(table.getSelectedRow());
        return values.get(selectedIndex);
    }

    public void setValues(List<T> values) {
        this.values.clear();
        this.values.addAll(values);
        fireTableDataChanged();
    }

    public JTable getTable() {
        return table;
    }

    public JScrollPane getInScrollPane(boolean horizontalScrollBar, boolean verticalScrollBar) {
        final JScrollPane scrollPane = new JScrollPane(this.table);
        table.setFillsViewportHeight(true);
        //scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        return scrollPane;
    }
}
