package esoe;

import javax.swing.*;

public class Core {
    private Card card;
    private Deck shards;//перечень осколков ядра. только формироваться должны из deck
    private static Deck deck = new Deck();//реестр всех карт - колода

    public Core(){
        Card c = new Card();
        c.setID(Identifier.getNext());
        c.setName(JOptionPane.showInputDialog("Назовите проект!"));
        card = c;
        System.out.println("");
        shards = new Deck();
        deck.add(card);
    }
    public Core(Card card){
        this.card = card;
        this.shards = new Deck();
        deck.add(card);
    }
    public Card getCard(){
        return card;
    }
    public Deck getShards(){
        return shards;
    }
    public void setShards(Card card){
        //shards = deck.getShards(card);
        shards.fireTableDataChanged();
    }
    public static Deck getDeck(){
        return deck;
    }
}
