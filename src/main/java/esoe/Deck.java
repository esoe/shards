package esoe;
/**
 * модель данных в которой хранятся карточки осколков
 * в которой ведется поиск осколков, для нужд системы
 * "колода или картотека"
 */

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class Deck extends AbstractTableModel {
    private String[] header = {"id", "name", "parent", "shape"};
    private Object[][] data;
    private int index = 0;

    public Deck(){}
    public int index(){
        index++;
        return index;
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
    //
    public String getColumnName(int col){
        return header[col];
    }
    //возвращает массив данных
    public Object[][] getData(){
        return data;
    }
    //добавляет карту в колоду
    public void add(Card card){
        if (data == null){
            data = new Object[1][getColumnCount()];
            data[0][0] = card.getId();
            data[0][1] = card.getName();
            data[0][2] = card.getParent();
            data[0][3] = card.getShape();
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
            d[getRowCount()][3] = card.getShape();
            //устанавливаем ссылку объекта data на новый объект d
            data = d;//должно поидее работать так
        }
        //сообщаем слушателю модели об изменении данных, для вызова обработчика события
        fireTableDataChanged();
    }
    //возвращает карту из колоды по индексу
    public Card getCard(int id){
        Card card = new Card();
        //проверяем, наличие записей в колоде
        if (getRowCount() != 0){
            //перебираем все записи в колоде
            int i = 0;
            boolean find = true;
            while (i < getRowCount()){
                //сравниваем значение id с записями в колоде
                if (id == (int)data[i][0]){
                    //заполняем поля новой карточки
                    card.setID((int)data[i][0]);
                    card.setName((String)data[i][1]);
                    card.setParent((int)data[i][2]);
                    card.setShape((int)data[i][3]);
                    find = false;
                }
                i++;
            }
            //проверяем, была ли  найдена необходимая карточка
            if (find){
                System.out.println("карточка с номером " + id + " не найдена ...");
                System.out.println("метод deck.getCard(id) вернул пустую карточку ...");
            }
        }else {
            System.out.println("колода пуста ...");
            System.out.println("метод deck.getCard(id) вернул пустую карточку ...");
        }

        return card;
    }
    //возвращает упрощенную модель данных, только второй столбец "name"
    public DefaultTableModel simple(){
        DefaultTableModel dm = new DefaultTableModel();
        dm.setColumnCount(1);
        dm.setRowCount(this.getRowCount());
        //перебираем строки модели
        int i = 0;
        while (i < this.getRowCount()){
            //копируем данные в новую модель
            dm.setValueAt(this.getData()[i][1], i, 0);
            i++;
        }
        //устанавливаем заголовок столбца новой модели
        Object[] head = {"name"};
        dm.setColumnIdentifiers(head);
        return dm;
    }
    //возвращает список наименований
    public Object[] list(){
        Object[] o = new Object[getRowCount()+1];
        if (getRowCount() != 0){
            o[0] = "Default";
            int i = 0;
            while (i < getRowCount()){
                o[i+1] = data[i][1];//столбец 1 - это наименования
                i++;
            }
        }else {
            o[0] = "Default";
        }
        return o;
    }
    //возвращает id по имени
    public int getID(String name){
        int id = 0;
        int i = 0;
        while (i < getRowCount()){
            if (name == getData()[i][1]){
                id = (int)getData()[i][0];
            }
            i++;
        }
        return id;
    }
}
