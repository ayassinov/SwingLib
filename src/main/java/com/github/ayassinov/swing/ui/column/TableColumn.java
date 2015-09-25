package com.github.ayassinov.swing.ui.column;

import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.function.Function;

/**
 * @author ayassinov on 25/09/2015.
 */
@Getter
public class TableColumn<M, T> {

    private Class<T> clazz;

    private String name;

    private boolean editable;

    private TableCellEditor tableCellEditor;

    private TableCellRenderer tableCellRenderer;

    private CellValueGetter<M, T> cellValueGetter;

    private Function<M,T> myFunction;

    private CellValueSetter<M, T> cellValueSetter;

    private TableColumn() {
    }

    public TableColumn(String name, Class<T> clazz, CellValueGetter<M, T> cellValueGetter) {
        this(name, clazz, null, null, cellValueGetter, null);

    }

    public TableColumn(String name, Class<T> clazz, TableCellEditor tableCellEditor, TableCellRenderer tableCellRenderer,
                       CellValueGetter<M, T> cellValueGetter, CellValueSetter<M, T> cellValueSetter) {
        this();
        this.name = name;
        this.clazz = clazz;
        this.tableCellEditor = tableCellEditor;
        this.tableCellRenderer = tableCellRenderer == null ? new DefaultTableCellRenderer() : tableCellRenderer;

        setCellValueGetter(cellValueGetter);
        setCellValueSetter(cellValueSetter);
    }

    public TableColumn<M, T> cellEditor(TableCellEditor tableCellEditor) {
        this.tableCellEditor = tableCellEditor;
        return this;
    }

    public TableColumn<M, T> cellRenderer(TableCellRenderer tableCellRenderer) {
        this.tableCellRenderer = tableCellRenderer;
        return this;
    }

    public TableColumn<M, T> cellValueGetter(CellValueGetter<M, T> cellValueGetter) {
        setCellValueGetter(cellValueGetter);
        return this;
    }

    public TableColumn<M, T> cellValueSetter(CellValueSetter<M, T> cellValueSetter) {
        setCellValueSetter(cellValueSetter);
        return this;
    }

    private void setCellValueGetter(CellValueGetter<M, T> cellValueGetter) {
        this.cellValueGetter= cellValueGetter;
        if (this.cellValueGetter == null) {
            throw new IllegalArgumentException("Cell Value getter method is mandatory and needs to be implemented");
        }
    }

    private void setCellValueSetter(CellValueSetter<M, T> cellValueSetter) {
        this.cellValueSetter = cellValueSetter;
        if (this.cellValueSetter == null) {
            this.editable = false;
            this.cellValueSetter = new CellValueSetter<M, T>() {
                public void onUpdate(M rowObject, T newValue) {
                    throw new UnsupportedOperationException("Cannot set the value. The setter method is not implemented for this column");
                }
            };
        } else {
            //set cell editor or default one if it's null
            this.editable = true;
            if (this.tableCellEditor == null) {
                final JTable t = new JTable();
                this.tableCellEditor = t.getDefaultEditor(clazz);
            }
        }
    }
}
