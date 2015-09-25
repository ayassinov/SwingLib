package com.github.ayassinov.swing.ui.table;

import com.github.ayassinov.swing.ui.column.TableColumn;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ayassinov on 21/09/15.
 */
public class GenericTableModel<M> extends AbstractTableModel {

    private final List<M> values;
    private final List<TableColumn<M, ?>> tableColumns;

    public GenericTableModel(List<TableColumn<M, ?>> tableColumns) {
        this(tableColumns, new ArrayList<M>());
    }

    public GenericTableModel(List<TableColumn<M, ?>> tableColumns, List<M> values) {
        this.tableColumns = tableColumns;
        this.values = values;
    }

    public int getRowCount() {
        return values.size();
    }

    public int getColumnCount() {
        return tableColumns.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0)
            return null;

        if (this.values.isEmpty())
            return null;


        final TableColumn<M, ?> tableColumn = tableColumns.get(columnIndex);
        return tableColumn.getCellValueGetter().onGet(this.values.get(rowIndex));
    }

    @Override
    public String getColumnName(int column) {
        return tableColumns.get(column).getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tableColumns.get(columnIndex).getClazz();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return tableColumns.get(columnIndex).isEditable();
    }

    @Override
    public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
        //noinspection unchecked
        final TableColumn<M, Object> tableColumn = (TableColumn<M, Object>) tableColumns.get(columnIndex);
        if (tableColumns.get(columnIndex).isEditable()) {
            tableColumn.getCellValueSetter().onUpdate(this.values.get(rowIndex), newValue);
        }
    }

    public void setValues(List<M> values) {
        this.values.clear();
        this.values.addAll(values);
        fireTableDataChanged();
    }

    public M getValue(int index) {
        if (index < 0)
            return null;

        if (index > this.values.size())
            return null;

        return this.values.get(index);
    }
}
