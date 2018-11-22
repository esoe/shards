package esoe;

public class Card {
    private int id;//уникальный идентификатор карточки
    private String name;//наименование карточки
    private int parent;//карта родителя. ее номер
    private int shape;

    public Card(){}
    public Card(Deck deck, String name, int parent, int shape){
        setName(name);
        setID(deck.index());
        setParent(parent);
        setShape(shape);
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
    public void setShape(int shape){
        this.shape = shape;
    }
    public int getShape(){
        return  shape;
    }
}
