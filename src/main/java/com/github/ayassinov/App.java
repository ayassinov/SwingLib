package com.github.ayassinov;

import com.github.ayassinov.swing.Example;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final Example example = new Example("JTable example");
                example.setVisible(true);
            }
        });
    }
}
