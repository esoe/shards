package esoe.build;

import esoe.Card;
import esoe.Core;
import esoe.Deck;

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
    private Deck deck;

    //создаем панельку в зависимости от передаваемой колоды (deck или shapes)
    public DeckWidget(Deck deck){
        setLayout(layWidget);
        initDeck(deck);
        initPaneControls();
        initPaneShards();
    }

    public void initDeck(Deck deck){
        this.deck = deck;
        DeckListener listenR = new DeckListener(tableShards, this.deck);
        this.deck.addTableModelListener(listenR);
    }
    public void initPaneControls(){
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
                    new CardWidget(card, deck).initFrame();
                }else {
                    System.out.println("невозможно перейти к отсутствующим осколкам, создайте ядро!!");
                }

            }
        });
        paneControls.add(goShard);

        //открываем core
        JButton goCore = new JButton("Core");
        goCore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("... нажата кнопка goCore");
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
                    new DeckWidget(deck.core(card)).initFrame(card.getName());
                }else {
                    System.out.println("невозможно перейти к отсутствующим осколкам, создайте ядро!!");
                }

            }
        });
        paneControls.add(goCore);

        //удаление карты
        JButton btnDel = new JButton("del");
        btnDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("... нажата кнопка btnDel");
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
                //удаляем карту из колоды
                deck.del(card);
                }
        });
        paneControls.add(btnDel);

        //создает новое корневое ядро с нулевым родителем, новый проект.
        JButton newShard = new JButton("NEW");
        newShard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("... нажата кнопка newShard");
                String name = JOptionPane.showInputDialog("наименование осколка");
                int parent = 0;
                Object[] o = Core.shapes().root().list();
                String shape = (String) JOptionPane.showInputDialog(
                        null, "Shape", "выбор shape",
                        JOptionPane.QUESTION_MESSAGE, null,
                        o,
                        o[0]);
                System.out.println("shape осколка: " + shape);
                Card card = new Card(deck, name, parent, Core.shapes().getID(shape));
                deck.add(card);//добавили карту в колоду
                //добавляем компоненты назначенной формы
                if (shape != "Default"){
                    int id = Core.shapes().getID(shape);
                    Card c = Core.shapes().getCard(id);
                    deck.addCore(card, Core.shapes().core(c));
                }
            }
        });
        paneControls.add(newShard);

        this.add(paneControls, layWidget.NORTH);
    }
    public void initPaneShards(){
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
}
