package com.github.ayassinov;

import com.github.ayassinov.swing.TableExample;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final TableExample tableExample = new TableExample("JTable example");
                tableExample.setVisible(true);
            }
        });
    }
}
