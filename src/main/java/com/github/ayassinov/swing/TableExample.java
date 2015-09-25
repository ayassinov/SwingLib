package com.github.ayassinov.swing;

import com.github.ayassinov.swing.model.Classifier;
import com.github.ayassinov.swing.ui.table.JTableAndModel;
import com.github.ayassinov.swing.ui.column.CellValueGetter;
import com.github.ayassinov.swing.ui.column.CellValueSetter;
import com.github.ayassinov.swing.ui.column.ColumnsBuilder;
import com.github.ayassinov.swing.ui.column.TableColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Date;
import java.util.List;

/**
 * @author ayassinov on 19/09/15.
 */
public class TableExample extends JFrame {

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

    final TableCellRenderer blueRender = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setBackground(Color.BLUE);
            c.setForeground(Color.WHITE);
            return c;
        }
    };


    public TableExample(String title) {
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
        mainPanel.add(anotherTable(), BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        pack();
        setVisible(true);
    }

    private JTable createTable() {


        final List<TableColumn<Classifier, ?>> tableColumns = ColumnsBuilder.<Classifier>builder()
                .add("Active", Boolean.class, render, new CellValueGetter<Classifier, Boolean>() {
                            public Boolean onGet(Classifier rowObject) {
                                return rowObject.isActive();
                            }
                        }
                )
                .add("Date", Date.class, new CellValueGetter<Classifier, Date>() {
                    public Date onGet(Classifier rowObject) {
                        return rowObject.getCreatedAt();
                    }
                })
                .add("Name", String.class, new CellValueGetter<Classifier, String>() {
                            public String onGet(Classifier rowObject) {
                                return rowObject.getName();
                            }
                        }, new CellValueSetter<Classifier, String>() {
                            public void onUpdate(Classifier rowObject, String newValue) {
                                rowObject.setName(newValue);
                            }
                        }
                )
                .build();


        final JTableAndModel<Classifier> jTableAndModel = new JTableAndModel<Classifier>(tableColumns);
        jTableAndModel.getTableModel().setValues(Classifier.listAll());
        return jTableAndModel.getJTable();
    }


    private Component anotherTable() {

        final List<TableColumn<Classifier, ?>> columns = ColumnsBuilder.<Classifier>with(render)
                .add("Name", String.class, new CellValueGetter<Classifier, String>() {
                    public String onGet(Classifier rowObject) {
                        return rowObject.getName();
                    }
                }, new CellValueSetter<Classifier, String>() {
                    public void onUpdate(Classifier rowObject, String newValue) {
                        rowObject.setName(newValue);
                    }
                })
                .add("Date", Date.class, blueRender, new CellValueGetter<Classifier, Date>() {
                    public Date onGet(Classifier rowObject) {
                        return rowObject.getCreatedAt();
                    }
                })
                .add("Is Active", Boolean.class, new CellValueGetter<Classifier, Boolean>() {
                    @Override
                    public Boolean onGet(Classifier rowObject) {
                        return rowObject.isActive();
                    }
                })
                .add("Amount", Double.class, new CellValueGetter<Classifier, Double>() {
                    @Override
                    public Double onGet(Classifier rowObject) {
                        return rowObject.getDefaultAmount();
                    }
                })
                .build();


        final JTableAndModel<Classifier> tableAndModel = new JTableAndModel<Classifier>(columns);
        tableAndModel.getTableModel().setValues(Classifier.listAll());
        return tableAndModel.getScrollPane(true, true);


    }
}
