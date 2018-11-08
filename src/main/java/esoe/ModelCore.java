package esoe;

import javax.swing.table.AbstractTableModel;

public class ModelCore extends AbstractTableModel {
    private String[] header = {"id", "name", "parent"};
    private Object[][] data;
    //данные ядра
    public Shard shard;

    //если при создании модели указывать тип модели, то не нужны модели на
    public ModelCore(){}
    public ModelCore(String name, Shard parent){}//создает модель с указанным именем
    //добавляем строку в модель
    public void add(Shard shard){
        fireTableDataChanged();
    }
    public int getColumnCount() {
        return this.header.length;
    }
    public int getRowCount() {
        return this.data.length;
    }
    public Object getValueAt(int row, int col) {
        return this.data[row][col];
    }
    public ModelCore getModel(){
        return this;
    }


}

