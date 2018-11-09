package esoe;

import java.util.ArrayList;

public class Core {
    private Card card = new Card();//содержит id, name ядра, а также ссылку на родительское ядро
    private ArrayList<ModelCore> shards = new ArrayList();//список атрибутов (таблиц по каждому осколку) ядра
    private static ModelCore reestr = new ModelCore();//реестр всех карточек

    //создание проекта
    public Core(){  }
    private void setDefaultCore(){
        card.setID(Identifier.getNext());
        //название проекта должно браться с сервера из перечня проектов
        card.setName("unnamed project");
        //с нулевым родителем походу придется постоянно проверять в будущем, не нулевой ли он.
        card.setParent(null);
        //добавляем карточку "unnamed project" в reestr
        Core.reestr.add(card);
        //добавляем осколок "discription" в "unnamed project"
        this.shards.add(new ModelCore("discription", this.card.getParent()));
    }
    public Card getCard(){
        return card;
    }
    public ArrayList<ModelCore> getShards(){
        return shards;
    }
    public void addShard(String name){
        ModelCore mc = new ModelCore(name, getCard());
        getShards().add(mc);
    }
    public static ModelCore getReestr(){
        return reestr;
    }
}
