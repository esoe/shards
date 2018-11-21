package esoe;

import javax.swing.*;

public class Core {
    private Card card;
    private static Deck deck = new Deck();//реестр всех карт - колода
    private static Deck shapes = new Deck();//реестр всех доступных к использованию в проекте форм - shapes

    //создает новое ядро в определенной колоде
    public Core(Deck deck){
        Card c = new Card();
        c.setID(deck.index());
        c.setName(JOptionPane.showInputDialog("Назовите проект!"));
        String s = JOptionPane.showInputDialog("Укажите № shape (0 по умолчанию)");
        c.setShape(Integer.parseInt(s));
        card = c;
        deck.add(card);
    }
    //открывает ядро по заданой карте
    public Core(Card card){
        this.card = card;
    }
    //возвращает карту ядра
    public Card getCard(){
        return card;
    }
    //возвращает модель данных колоды
    public static Deck deck(){
        return deck;
    }
    //возвращает модель используемых форм в колоде
    public static Deck shapes(){
        return shapes;
    }
    //возвращает модель, содержащую осколки текущего ядра
    public static Deck shards(Card card){
        Deck shards = new Deck();
        //перебираем колоду
        int i = 0;
        while (i < deck().getRowCount()){
            //сравниваем поле id карты с полями parent колоды
            if (card.getId() == (int)deck().getValueAt(i, 2)){
                //добавляем в модель совпавшие осколки
                shards.add(deck().getCard((int)deck().getValueAt(i, 0)));
            }
            i++;
        }
        return shards;
    }
}