package com.github.ayassinov.swing.ui.table;

import com.github.ayassinov.swing.ui.column.TableColumn;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.io.Serializable;
import java.util.List;

/**
 * @author ayassinov on 19/09/15.
 */
public class JTableAndModel<M extends Serializable> {

    private final JTable table;
    private final GenericTableModel<M> genericTableModel;

    public JTableAndModel(List<TableColumn<M, ?>> tableColumns) {
        this.genericTableModel = new GenericTableModel<M>(tableColumns);
        this.table = new JTable(genericTableModel);
        final TableColumnModel columnModel = this.table.getColumnModel();
        for (int columnIndex = 0; columnIndex < this.genericTableModel.getColumnCount(); columnIndex++) {
            columnModel.getColumn(columnIndex).setCellEditor(tableColumns.get(columnIndex).getTableCellEditor());
            columnModel.getColumn(columnIndex).setCellRenderer(tableColumns.get(columnIndex).getTableCellRenderer());
        }
    }

    public M getValueOfTheSelectedRow() {
        if (table.getSelectedRow() < 0) {
            return null;
        }

        final int selectedIndex = table.getRowSorter().convertRowIndexToModel(table.getSelectedRow());
        return genericTableModel.getValue(selectedIndex);
    }

    public JTable getJTable() {
        return table;
    }

    public GenericTableModel<M> getTableModel() {
        return genericTableModel;
    }

    public JScrollPane getScrollPane(boolean horizontalScrollBar, boolean verticalScrollBar) {
        final JScrollPane scrollPane = new JScrollPane(this.table);
        table.setFillsViewportHeight(true);
        //scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        return scrollPane;
    }

}
