package esoe.build;

import esoe.Card;
import esoe.Core;
import esoe.Deck;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    GridLayout layWidget = new GridLayout(3, 1);
    GridLayout layPaneEdit = new GridLayout(3, 1);
    GridLayout layPaneShards = new GridLayout(1, 1);
    GridLayout layPaneControls = new GridLayout(3, 2);
    public JPanel paneEdit = new JPanel();
    public JTextField fieldEditCard  = new JTextField();
    public JComboBox comboShape;
    public JPanel paneControls = new JPanel();
    public JPanel paneShards = new JPanel();
    public JTable tableShards = new JTable();
    public JScrollPane panelScrollShards;
    private Card card;
    private Deck deck;

    public CardWidget(Card card, Deck deck){
        setLayout(layWidget);
        initDeck(card, deck);
        initPaneEdit();
        initPaneControls();
        initPaneShards();
    }
    //косяк тут, не понятно по какой карте таблица строится
    public void initDeck(Card card, Deck deck){
        this.card = card;
        this.deck = deck;
    }
    //
    public void initPaneEdit(){
        paneEdit.setBackground(Color.blue);
        paneEdit.setLayout(layPaneEdit);

        //настройка текстового поля
        fieldEditCard.setText(card.getName());
        //слушаем именения в fieldEditCard
        fieldEditCard.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                saveChanges();
            }
            public void removeUpdate(DocumentEvent e) {
                saveChanges();
            }
            public void insertUpdate(DocumentEvent e) {
                saveChanges();
            }
            //сохраняем изменения в модели
            public void saveChanges(){
                String name = fieldEditCard.getText();
                String shape = comboShape.getSelectedItem().toString();
                Card newCard = new Card();
                newCard.setID(card.getId());
                newCard.setName(name);
                newCard.setParent(card.getParent());
                newCard.setShape(Core.shapes().getID(shape));
                //удаляем старую карту
                deck.del(card);
                //обавляем новую карту
                deck.add(newCard);
                card = newCard;
                setupFrame();
            }
        });
        paneEdit.add(fieldEditCard);

        //настройка комбо - shape
        comboShape = new JComboBox(Core.shapes().root().list());
        paneEdit.add(comboShape);

        JButton btnImportShapes = new JButton("Import Shapes");
        btnImportShapes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Нажата кнопка btnImportShapes");
                String shape = (String)comboShape.getSelectedItem();
                int id;
                Card c;
                //копируем осколки выбранной shape в колоду
                if (shape != "Default"){
                    id = Core.shapes().getID(shape);
                    c = Core.shapes().getCard(id);
                    deck.addCore(card, Core.shapes().core(c));//аргументы - карта, указанная в комбо
                }
            }
        });
        paneEdit.add(btnImportShapes);

        add(paneEdit);

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
                Object[] o = Core.shapes().root().list();
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
    //настройка отображения на форме отображаемой карты
    public void setupFrame(){
        tmpFrame.setTitle("Desk - "+ deck.getName() + " : id - "  + card.getId() + " : name - " + card.getName());
    }

    //подготовка отображения фрейма Core
    public void initFrame() {
        tmpFrame = new JFrame("Desk - "+ deck.getName() + " : id - "  + card.getId() + " : name - " + card.getName());
        lf = tmpFrame.getContentPane();
        tmpFrame.setSize(450, 300);
        tmpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        lf.setBackground(Color.white);
        tmpFrame.setLayout(layFrame);
        tmpFrame.setVisible(true);
        tmpFrame.add(this);
    }
}
