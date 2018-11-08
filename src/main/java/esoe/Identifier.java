package esoe;

/**
 * идентификация ядрышек.
 * 1. каждое ядро имеет свой уникальный идентификационный номер. Сквозная нумерация.
 * 2.
 */
public class Identifier {
    private static int index = 0; //порядковый номер объекта, сквозной
    public Identifier(){
    }
    public static int getNext(){
        index++;
        return index;
    }
}
