package esoe;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class ReestrListener implements TableModelListener {
    ModelCore model;
    JTable tab;

    ReestrListener(JTable table) {
        this.tab = table;
        this.model = Core.getReestr();
    }

    public void tableChanged(TableModelEvent e) {
        if (e.getType() == 0) {
            tab.setModel(Core.getReestr());
        }

    }
}