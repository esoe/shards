package esoe;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelShards extends AbstractTableModel {
    private String[] header = {"name"};
    private Object[][] data;

    public ModelShards(){}
    public ModelShards(ArrayList<Card> shards){
        data = new Object[shards.size()][1];
        //переписываем список в массив
        int i = 0;
        while (i < shards.size()){
            data[i][0] = shards.get(i).getName();
            i++;
        }
    }
    public int getColumnCount() {
        return header.length;
    }
    //возвращает количество строк модели
    public int getRowCount() {
        int row = 0;
        if(data != null){
            row = data.length;
        }
        return row;
    }
    //возвращает данные, содержащиеся в определенной ячейке модели
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    //возвращает строку заголовков
    public String[] getHeader(){
        return header;
    }
}
