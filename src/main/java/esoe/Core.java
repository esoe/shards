package esoe;

public class Core {
    private static Deck deck = new Deck("deck");//реестр всех карт - колода
    private static Deck shapes = new Deck("shapes");//реестр всех доступных к использованию в проекте форм - shapes

    //создает новое ядро в определенной колоде
    public Core(){}
    public static Deck getDeck(){
        return deck;
    }
    public static Deck deck(){
        return deck;
    }
    public static Deck getShapes(){
        return shapes;
    }
    public static Deck shapes(){
        return shapes;
    }
    public static void main( String[] args ){
        System.out.println( "... запущен метод main класса DeckWidget проекта shards ..." );
        new DeckWidget(getDeck()).initFrame("DeckWidget");
        new DeckWidget(getShapes()).initFrame("ShapeWidget");

    }
}