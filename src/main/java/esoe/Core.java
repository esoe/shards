package esoe;

import javax.swing.*;

public class Core {
    private Card card;
    private static Deck deck = new Deck();//реестр всех карт - колода

    public Core(){
        Card c = new Card();
        c.setID(Identifier.getNext());
        c.setName(JOptionPane.showInputDialog("Назовите проект!"));
        card = c;
        System.out.println("");
        deck().add(card);
    }
    public Core(Card card){
        this.card = card;
    }
    public Card getCard(){
        return card;
    }
    public static Deck deck(){
        return deck;
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
