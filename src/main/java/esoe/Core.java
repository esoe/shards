package esoe;

import javax.swing.*;
import java.util.ArrayList;

public class Core {
    private Card card;//содержит id, name ядра, а также ссылку на родительское ядро
    private ModelCore shards;//список атрибутов (таблиц по каждому осколку) ядра
    private static ModelCore reestr = new ModelCore();//реестр всех карточек


    public Core(){
        Card c = new Card();
        c.setID(Identifier.getNext());
        c.setName(JOptionPane.showInputDialog("Назовите проект!"));
        card = c;
        shards = new ModelCore();
        reestr.add(card);
    }
    //создание проекта
    public Core(Card card){
        this.card = card;
        this.shards = new ModelCore();
        reestr.add(card);
    }
    public static Card getDefaultCard(){
        Card c = new Card();
        c.setID(Identifier.getNext());
        //название проекта должно браться с сервера из перечня проектов
        c.setName("unnamed project");
        //с нулевым родителем походу придется постоянно проверять в будущем, не нулевой ли он.
        c.setParent(null);
        //добавляем карточку "unnamed project" в reestr
        //Core.reestr.add(card);
        //добавляем осколок "discription" в "unnamed project"
        //shards.add(new ModelCore("discription", card.getParent()));
        return c;
    }
    public Card getCard(){
        return card;
    }
    public ModelCore getShards(){
        return shards;
    }
    public void addShard(String name){
        Card c = new Card(name, card);
        shards.add(c);
        //new Core(c);
    }
    public static ModelCore getReestr(){
        return reestr;
    }
}
