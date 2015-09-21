package com.github.ayassinov.swing;

import com.github.ayassinov.swing.model.Classifier;
import com.github.ayassinov.swing.ui.Column;
import com.github.ayassinov.swing.ui.UpdatableTableCell;
import com.github.ayassinov.swing.ui.TableModelBinding;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * @author ayassinov on 19/09/15.
 */
public class Example extends JFrame {

    public Example(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 600));

        createUI();

    }


    private void createUI() {


        final JPanel mainPanel = new JPanel(new BorderLayout());

        final JTable table = createTable();

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        mainPanel.add(table, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        pack();
        setVisible(true);
    }

    private JTable createTable() {
        final TableCellRenderer render = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(Color.LIGHT_GRAY);
                } else {
                    c.setBackground(Color.GRAY);
                }
                return c;
            }
        };

        final java.util.List<Column> columns = Column.builder()
                .add("name", true, render)
                .add("date", render)
                .add("value", render)
                .build();

        final TableModelBinding<Classifier> tableModelBinding = new TableModelBinding<Classifier>(columns, new UpdatableTableCell<Classifier>() {
            public Object getValueAtColumnIndex(Classifier value, int columnIndex) {
                if (columnIndex == 0)
                    return value.getName();
                else if (columnIndex == 1)
                    return value.getCreatedAt();
                else if (columnIndex == 2) {
                    return value.getDefaultAmount();
                }
                return null;
            }

            public void setValueAtColumnIndex(Classifier value, Object newValue, int columnIndex) {
                if (columnIndex == 0)
                    value.setName((String) newValue);

            }
        });

        tableModelBinding.getTableModel().setValues(Classifier.listAll());
        return tableModelBinding.getJTable();
    }
}
