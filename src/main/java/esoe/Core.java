package esoe;

public class Core {
    private static Deck deck = new Deck("deck");//реестр всех карт - колода
    private static Deck shapes = new Deck("shapes");//реестр всех доступных к использованию в проекте форм - shapes
    private static Deck archive;//стоит ли сохранять удаленные осколки?

    public Core(){}
    public static Deck deck(){
        return deck;
    }
    public static Deck shapes(){
        return shapes;
    }
    public static void main( String[] args ){
        System.out.println( "... запущен метод main класса Core проекта shards ..." );
        new DeckWidget(deck()).initFrame("DeckWidget");
        new DeckWidget(shapes()).initFrame("ShapeWidget");
    }
}