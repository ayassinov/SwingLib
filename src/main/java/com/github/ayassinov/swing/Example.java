package com.github.ayassinov.swing;

import com.github.ayassinov.swing.model.Classifier;
import com.github.ayassinov.swing.ui.BaseTable;
import com.github.ayassinov.swing.ui.Column;

import javax.swing.*;
import java.awt.*;

/**
 * @author ayassinov on 19/09/15.
 */
public class Example extends JFrame {

    public Example(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        final MyTable myTable = new MyTable();
        myTable.setValues(Classifier.listAll());
        return myTable.getTable();
    }

    private class MyTable extends BaseTable<Classifier> {
        public MyTable() {
            super(
                    Column.builder()
                            .add("name", "date", "value")
                            .build()
            );
        }


        @Override
        public Object getValueAtColumnIndex(Classifier value, int columnIndex) {
            if (columnIndex == 0)
                return value.getName();
            else if (columnIndex == 1)
                return value.getCreatedAt();
            else if(columnIndex == 2){
                return value.getDefaultAmount();
            }
            return null;
        }
    }
}
