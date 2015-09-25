package com.github.ayassinov.swing.ui.column;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ayassinov on 25/09/2015.
 */
public class ColumnsBuilder<M> {
    private final List<TableColumn<M, ?>> tableColumns = new ArrayList<TableColumn<M, ?>>();

    private TableCellEditor tableCellEditor;
    private TableCellRenderer tableCellRenderer;

    private ColumnsBuilder() {
    }

    public static <M> ColumnsBuilder<M> builder() {
        return new ColumnsBuilder<M>();
    }

    public static <M> ColumnsBuilder<M> with(TableCellRenderer render) {
        final ColumnsBuilder<M> builder = builder();
        builder.tableCellRenderer = render;
        return builder;
    }

    public static <M> ColumnsBuilder<M> with(TableCellEditor editor) {
        final ColumnsBuilder<M> builder = builder();
        builder.tableCellEditor = editor;
        return builder;
    }

    public static <M> ColumnsBuilder<M> with(TableCellRenderer renderer, TableCellEditor editor) {
        final ColumnsBuilder<M> builder = builder();
        builder.tableCellRenderer = renderer;
        builder.tableCellEditor = editor;
        return builder;
    }

    public <T> ColumnsBuilder<M> add(TableColumn<M, T> tableColumn) {
        tableColumns.add(tableColumn);
        return this;
    }

    public <T> ColumnsBuilder<M> add(String name, Class<T> clazz, CellValueGetter<M, T> cellValueGetter) {
        return add(name, clazz, null, null, cellValueGetter, null);
    }

    public <T> ColumnsBuilder<M> add(String name, Class<T> clazz, CellValueGetter<M, T> cellValueGetter, CellValueSetter<M, T> cellValueSetter) {
        return add(name, clazz, null, null, cellValueGetter, cellValueSetter);
    }

    public <T> ColumnsBuilder<M> add(String name, Class<T> clazz, TableCellRenderer cellRenderer, CellValueGetter<M, T> cellValueGetter) {
        return add(name, clazz, null, cellRenderer, cellValueGetter, null);
    }

    public <T> ColumnsBuilder<M> add(String name, Class<T> clazz, TableCellRenderer cellRenderer,
                                     CellValueGetter<M, T> cellValueGetter, CellValueSetter<M, T> cellValueSetter) {
        return add(name, clazz, null, cellRenderer, cellValueGetter, cellValueSetter);
    }

    public <T> ColumnsBuilder<M> add(String name, Class<T> clazz, TableCellEditor cellEditor, TableCellRenderer cellRenderer,
                                     CellValueGetter<M, T> cellValueGetter, CellValueSetter<M, T> cellValueSetter) {
        if (this.tableCellEditor != null && cellEditor == null) {
            cellEditor = this.tableCellEditor;
        }

        if (this.tableCellRenderer != null && cellRenderer == null) {
            cellRenderer = this.tableCellRenderer;
        }

        final TableColumn<M, T> tableColumn = new TableColumn<M, T>(name, clazz, cellEditor, cellRenderer, cellValueGetter, cellValueSetter);
        tableColumns.add(tableColumn);
        return this;
    }

    public List<TableColumn<M, ?>> build() {
        return this.tableColumns;
    }
}
