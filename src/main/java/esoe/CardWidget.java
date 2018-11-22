package esoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * панелька для отображения данных Shard
 *
 */
public class CardWidget extends JPanel {
    public JFrame tmpFrame;
    Container lf;
    GridLayout layFrame = new GridLayout();
    GridLayout layWidget = new GridLayout(2, 1);
    GridLayout layPaneShards = new GridLayout(1, 1);
    GridLayout layPaneControls = new GridLayout(5, 1);
    public JPanel paneControls = new JPanel();
    public JPanel paneShards = new JPanel();
    public JTable tableShards = new JTable();
    public JScrollPane panelScrollShards;
    private Card card;
    private Deck deck;

    public CardWidget(Card card, Deck deck){
        setLayout(layWidget);
        initDeck(card, deck);
        initPaneControls();
        initPaneShards();
    }
    //косяк тут, не понятно по какой карте таблица строится
    public void initDeck(Card card, Deck deck){
        this.card = card;
        this.deck = deck;
    }
    public void initPaneControls(){
        paneControls.setBackground(Color.blue);
        paneControls.setLayout(layPaneControls);


        JButton addShard = new JButton("Add");
        addShard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Нажата кнопка addShard");
                //добавление осколка к ядру
                String name = JOptionPane.showInputDialog("наименование осколка");
                System.out.println("осколок назван: " + name);
                Object[] o = Core.shapes().list();
                String shape = (String) JOptionPane.showInputDialog(
                        paneControls, "Shape", "выбор shape",
                        JOptionPane.QUESTION_MESSAGE, null,
                        o,
                        o[0]);
                System.out.println("shape осколка: " + shape);
                Card c = new Card(deck, name, card.getId(), Core.shapes().getID(shape));//содали новую карту
                deck.add(c);//добавили карту в колоду
                tableShards.setModel(deck.shards(card).simple(1));
            }
        });
        paneControls.add(addShard);

        JButton editCard = new JButton("editCard");
        editCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Нажата кнопка editCard");
                //новое наименование name
                //новая форма shape
                //родитель остается прежним parent
                //индекс остается прежним id
                //заменяем поля карты в колоде на новые значения
                /**
                 * смена формы може повлиять на состав осколков ядра, возможно стоит обнулять осколки
                 * или вести диалог с пользователем до окончания пересмотра всех осколков
                 *
                 *
                 * если форма (shape) не меняется, то сложностей с изменением названия осколка нет.
                 */
            }
        });
        paneControls.add(editCard);

        JButton delAttr = new JButton("Dell");
        delAttr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Нажата кнопка delAttr");
            }
        });
        paneControls.add(delAttr);

        JButton goParent = new JButton("GO to parent");
        goParent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Нажата кнопка goParent");
            }
        });
        paneControls.add(goParent);

        //добавляем комбо, для отображения shape
        //JComboBox

        add(paneControls);
    }
    public void initPaneShards(){
        paneShards.setBackground(Color.blue);
        paneShards.setLayout(layPaneShards);
        tableShards.setModel(deck.shards(card).simple(1));
        panelScrollShards = new JScrollPane(tableShards);
        paneShards.add(panelScrollShards, 0);
        add(paneShards);

    }
    //подготовка отображения фрейма Core
    public void initFrame() {
        tmpFrame = new JFrame("Core" +  ": id - "  + card.getId() + ", name: " + card.getName());
        lf = tmpFrame.getContentPane();
        tmpFrame.setSize(450, 300);
        tmpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        lf.setBackground(Color.white);
        tmpFrame.setLayout(layFrame);
        tmpFrame.setVisible(true);
        tmpFrame.add(this);
    }
}
