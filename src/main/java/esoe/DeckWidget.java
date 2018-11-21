package esoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeckWidget extends JPanel{
    private JFrame tmpFrame;
    private Container lf;
    private GridLayout layFrame = new GridLayout(1, 1, 0, 0);
    private BorderLayout layWidget = new BorderLayout();
    private GridLayout layPaneShards = new GridLayout(1, 1);
    private GridLayout layPaneControls = new GridLayout(1, 1);
    private JPanel paneControls = new JPanel();
    private JPanel paneShards = new JPanel();
    private JTable tableShards = new JTable();
    private JScrollPane panelScrollShards;

    //создаем панельку в зависимости от передаваемой колоды (deck или shapes)
    public DeckWidget(Deck deck){
        setLayout(layWidget);
        initCore(deck);
        initPaneControls(deck);
        initPaneShards(deck);
    }

    public void initCore(Deck deck){
        DeckListener listenR = new DeckListener(tableShards, deck);
        deck.addTableModelListener(listenR);
    }
    public void initPaneControls(Deck deck){
        paneControls.setBackground(Color.blue);
        paneControls.setLayout(layPaneControls);

        //открываем интерфейс работы с указанным пользователем ядром
        JButton goShard = new JButton("GO");
        goShard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("... нажата кнопка goShard");
                //проверяем, что колода не пуста
                if (tableShards.getModel().getRowCount() != 0){
                    //считываем параметры выбранной пользователем строки
                    int id = (int)tableShards.getModel().getValueAt(tableShards.getSelectedRow(), 0);
                    String name = (String)tableShards.getModel().getValueAt(tableShards.getSelectedRow(), 1);
                    int parent = (int)tableShards.getModel().getValueAt(tableShards.getSelectedRow(), 2);
                    int shape = (int)tableShards.getModel().getValueAt(tableShards.getSelectedRow(), 3);
                    //создаем новую карту по указанным пользователем параметрам
                    Card card = new Card();
                    card.setID(id);
                    card.setName(name);
                    card.setParent(parent);
                    card.setShape(shape);
                    //открываем виджет ядра по новой карте
                    new CoreWidget(new Core(card), deck).initFrame();
                }else {
                    System.out.println("невозможно перейти к отсутствующим осколкам, создайте ядро!!");
                }

            }
        });
        paneControls.add(goShard);

        //создает новое корневое ядро с нулевым родителем, новый проект.
        JButton newShard = new JButton("NEW");
        newShard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("... нажата кнопка newShard");
                new CoreWidget(deck).initFrame();

            }
        });
        paneControls.add(newShard);

        this.add(paneControls, layWidget.NORTH);
    }
    public void initPaneShards(Deck deck){
        paneShards.setBackground(Color.blue);
        paneShards.setLayout(layPaneShards);
        tableShards.setModel(deck);
        panelScrollShards = new JScrollPane(tableShards);
        paneShards.add(panelScrollShards, 0);
        this.add(paneShards, layWidget.CENTER);

    }

    public void initFrame(String name) {
        tmpFrame = new JFrame(name);
        lf = tmpFrame.getContentPane();
        tmpFrame.setSize(450, 300);
        tmpFrame.setDefaultCloseOperation(3);
        lf.setBackground(Color.white);
        tmpFrame.setLayout(layFrame);
        tmpFrame.setVisible(true);
        tmpFrame.add(this);
    }

    public static void main( String[] args )
    {
        System.out.println( "... запущен метод main класса DeckWidget проекта shards ..." );
        new DeckWidget(Core.deck()).initFrame("DeckWidget");
        new DeckWidget(Core.shapes()).initFrame("ShapeWidget");

    }

}
