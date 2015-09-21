package com.github.ayassinov.swing.ui;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.List;

/**
 * @author ayassinov on 19/09/15.
 */
public class TableModelBinding<T> {

    private final JTable table;
    private final BaseTableModel<T> baseTableModel;

    public TableModelBinding(List<Column> columns, UpdatableTableCell<T> updatableTableCell) {
        this(new BaseTableModel<T>(columns, updatableTableCell));
    }

    public TableModelBinding(BaseTableModel<T> baseTableModel) {
        this.baseTableModel = baseTableModel;
        this.table = new JTable(baseTableModel);
        final TableColumnModel columnModel = this.table.getColumnModel();
        final List<Column> columns = this.baseTableModel.getColumns();
        for (int columnIndex = 0; columnIndex < this.baseTableModel.getColumnCount(); columnIndex++) {
            columnModel.getColumn(columnIndex).setCellEditor(columns.get(columnIndex).getTableCellEditor());
            columnModel.getColumn(columnIndex).setCellRenderer(columns.get(columnIndex).getTableCellRenderer());
        }
    }

    public T getValueOfTheSelectedRow() {
        if (table.getSelectedRow() < 0) {
            return null;
        }

        final int selectedIndex = table.getRowSorter().convertRowIndexToModel(table.getSelectedRow());
        return baseTableModel.getValue(selectedIndex);
    }

    public JTable getJTable() {
        return table;
    }

    public BaseTableModel<T> getTableModel() {
        return baseTableModel;
    }

    public JScrollPane getScrollPane(boolean horizontalScrollBar, boolean verticalScrollBar) {
        final JScrollPane scrollPane = new JScrollPane(this.table);
        table.setFillsViewportHeight(true);
        //scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        return scrollPane;
    }
}
