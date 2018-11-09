package esoe;

import javax.swing.table.AbstractTableModel;

public class ModelCore extends AbstractTableModel {
    private String[] header = {"id", "name", "parent"};
    private Object[][] data;

    //если при создании модели указывать тип модели, то не нужны модели на
    public ModelCore(){}
    //создает модель с указанным именем
    public ModelCore(String name, Card parent){
        //
    }
    //добавляем строку card в модель
    public void add(Card card){
        if (data == null){
            data = new Object[1][3];
            data[0][0] = card.getId();
            data[0][1] = card.getName();
            data[0][2] = card.getParent();
        }else {
            //создаем объект d с дополнительной строкой
            Object[][] d = new Object[getRowCount()+1][getColumnCount()];
            //переносим данные data в объект d
            int i = 0;
            while (i < getRowCount()){
                int j = 0;
                while (j < getColumnCount()){
                    d[i][j] = data[i][j];
                    j++;
                }
                i++;
            }
            //дописываем в объект d новую строку card
            d[getRowCount()][0] = card.getId();
            d[getRowCount()][1] = card.getName();
            d[getRowCount()][2] = card.getParent();
            //устанавливаем ссылку объекта data на новый объект d
            data = d;//должно поидее работать так
        }
        //сообщаем слушателю модели об изменении данных, для вызова обработчика события
        fireTableDataChanged();
    }
    //возвращает количество столбцов модели
    public int getColumnCount() {
        return this.header.length;
    }
    //возвращает количество строк модели
    public int getRowCount() {
        int row = 0;
        if(data != null){
            row = this.data.length;
        }
        return row;
    }
    //возвращает данные, содержащиеся в определенной ячейке модели
    public Object getValueAt(int row, int col) {
        return this.data[row][col];
    }
    //возвращает строку заголовков
    public String[] getHeader(){
        return header;
    }


}

