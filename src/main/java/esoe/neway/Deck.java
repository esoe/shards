package esoe.neway;
/**
 * модель данных в которой хранятся карточки осколков
 * в которой ведется поиск осколков, для нужд системы
 * колода или картотека
 *
 */

import javax.swing.table.AbstractTableModel;

public class Deck extends AbstractTableModel {
    private String[] header = {"id", "name", "parent"};
    private Object[][] data;

    public Deck(){}
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
    //добавляет карту в колоду
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
}
