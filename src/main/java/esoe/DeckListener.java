package esoe;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class DeckListener implements TableModelListener {
    Deck model;
    JTable tab;

    DeckListener(JTable table) {
        this.tab = table;
        this.model = Core.deck();
    }

    public void tableChanged(TableModelEvent e) {
        if (e.getType() == 0) {
            tab.setModel(Core.deck());
        }

    }
}