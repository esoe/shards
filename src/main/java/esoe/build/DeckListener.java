package esoe.build;

import esoe.Deck;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class DeckListener implements TableModelListener {
    Deck model;
    JTable tab;

    DeckListener(JTable table, Deck deck) {
        this.tab = table;
        this.model = deck;
    }

    public void tableChanged(TableModelEvent e) {
        if (e.getType() == 0) {
            tab.setModel(model);
        }

    }
}