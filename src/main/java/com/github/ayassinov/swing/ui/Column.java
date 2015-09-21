package com.github.ayassinov.swing.ui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ayassinov on 19/09/15.
 */
@Getter
@RequiredArgsConstructor
public class Column {

    private final String name;
    private final Class clazz;
    private final boolean editable;
    private final TableCellEditor tableCellEditor;
    private final TableCellRenderer tableCellRenderer;

    public Column(String name) {
        this.name = name;
        this.clazz = String.class;
        this.editable = false;
        this.tableCellEditor = getDefaultCellEditor(clazz);
        this.tableCellRenderer = new DefaultTableCellRenderer();
    }

    public Column(String name, Class clazz) {
        this(name, clazz, false, getDefaultCellEditor(clazz), new DefaultTableCellRenderer());
    }

    public Column(String name, TableCellRenderer cellRenderer) {
        this(name, String.class, false, getDefaultCellEditor(String.class), cellRenderer);
    }

    public Column(String name, Class clazz, TableCellRenderer cellRenderer) {
        this(name, clazz, false, getDefaultCellEditor(clazz), cellRenderer);
    }

    public Column(String name, Class clazz, boolean editable) {
        this(name, clazz, editable, getDefaultCellEditor(clazz), new DefaultTableCellRenderer());
    }

    public Column(String name, Class clazz, boolean editable, TableCellEditor tableCellEditor) {
        this(name, clazz, editable, tableCellEditor, new DefaultTableCellRenderer());
    }

    private static TableCellEditor getDefaultCellEditor(Class clazz) {
        final JTable t = new JTable();
        return t.getDefaultEditor(clazz);
    }

    public static ColumnsBuilder builder() {
        return new ColumnsBuilder();
    }

    public static class ColumnsBuilder {
        private final List<Column> columns = new ArrayList<Column>();

        private ColumnsBuilder() {
        }

        public ColumnsBuilder add(String... columnNames) {
            for (String columnName : columnNames) {
                columns.add(new Column(columnName));
            }
            return this;
        }

        public ColumnsBuilder add(String s) {
            columns.add(new Column(s));
            return this;
        }

        public ColumnsBuilder add(String name, Class clazz) {
            final Column c = new Column(name, clazz, false, getDefaultCellEditor(clazz), new DefaultTableCellRenderer());
            columns.add(c);
            return this;
        }

        public ColumnsBuilder add(String name, TableCellRenderer cellRenderer) {
            final Column c = new Column(name, String.class, false, getDefaultCellEditor(String.class), cellRenderer);
            columns.add(c);
            return this;
        }

        public ColumnsBuilder add(String name, Class clazz, TableCellRenderer cellRenderer) {
            final Column c = new Column(name, clazz, false, getDefaultCellEditor(clazz), cellRenderer);
            columns.add(c);
            return this;
        }

        public ColumnsBuilder add(String name, Class clazz, boolean editable) {
            final Column c = new Column(name, clazz, editable, getDefaultCellEditor(clazz), new DefaultTableCellRenderer());
            columns.add(c);
            return this;
        }

        public ColumnsBuilder add(String name, Class clazz, boolean editable, TableCellEditor tableCellEditor) {
            final Column c = new Column(name, clazz, editable, tableCellEditor, new DefaultTableCellRenderer());
            columns.add(c);
            return this;
        }

        public ColumnsBuilder add(Column column) {
            columns.add(column);
            return this;
        }

        public List<Column> build() {
            return this.columns;
        }

        public ColumnsBuilder add(String name, boolean editable, TableCellRenderer render) {
            final Column c = new Column(name, String.class, editable, getDefaultCellEditor(String.class), render);
            columns.add(c);
            return this;
        }
    }
}
