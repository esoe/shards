package esoe;

public class Card {
    private int id;//уникальный идентификатор карточки
    private String name;//наименование карточки
    private int parent;//карта родителя. ее номер

    public Card(){}
    public Card(String name, int parent){
        setName(name);
        setID(Identifier.getNext());
        setParent(parent);
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setID(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setParent(int parent){
        this.parent = parent;
    }
    public int getParent(){
        return parent;
    }
}
