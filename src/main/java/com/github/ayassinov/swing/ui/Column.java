package com.github.ayassinov.swing.ui;

import lombok.Getter;

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
public class Column {

    private final String name;
    private final Class clazz;
    private final boolean editable;
    private final TableCellRenderer tableCellRenderer;
    private final TableCellEditor tableCellEditor;
    
    public Column(String name) {
        final JTable t = new JTable();
        this.name = name;
        this.clazz = String.class;
        this.editable = false;
        this.tableCellEditor = t.getDefaultEditor(clazz);
        this.tableCellRenderer = new DefaultTableCellRenderer();
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

        public ColumnsBuilder add(Column column) {
            columns.add(column);
            return this;
        }

        public List<Column> build() {
            return this.columns;
        }
    }
}
