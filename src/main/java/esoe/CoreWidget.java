package esoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * панелька для отображения данных Shard
 *
 */
public class CoreWidget extends JPanel {
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
    public Core core;

    public CoreWidget(Deck deck){
        setLayout(layWidget);
        initCore(deck);
        initPaneControls(deck);
        initPaneShards();

    }
    public CoreWidget(Core core, Deck deck){
        setLayout(layWidget);
        initCore(core, deck);
        initPaneControls(deck);
        initPaneShards();
    }
    public void initCore(Deck deck){
        core = new Core(deck);//запустили пустое ядро
        tableShards.setModel(Core.shards(core.getCard()).simple());

    }
    public void initCore(Core core, Deck deck) {
        this.core = core;
        tableShards.setModel(Core.shards(core.getCard()).simple());
    }

    public void initPaneControls(Deck deck){
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
                Card c = new Card(deck, name, core.getCard().getId(), Core.shapes().getID(shape));//содали новую карту
                deck.add(c);//добавили карту в колоду
                tableShards.setModel(Core.shards(core.getCard()).simple());

            }
        });
        paneControls.add(addShard);

        JButton delAttr = new JButton("Dell");
        delAttr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Нажата кнопка delAttr");
            }
        });
        paneControls.add(delAttr);

        JButton editCore = new JButton("editCore");
        editCore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Нажата кнопка editCore");
            }
        });
        paneControls.add(editCore);

        JButton goParent = new JButton("GO to parent");
        goParent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Нажата кнопка goParent");
            }
        });
        paneControls.add(goParent);

        //добавляем комбо, для отображения shape
        //JComboBox

        this.add(paneControls);
    }
    public void initPaneShards(){
        paneShards.setBackground(Color.blue);
        paneShards.setLayout(layPaneShards);
        panelScrollShards = new JScrollPane(tableShards);
        paneShards.add(panelScrollShards, 0);
        this.add(paneShards);

    }
    //подготовка отображения фрейма Core
    public void initFrame() {
        tmpFrame = new JFrame("Core" +  ": id - "  + core.getCard().getId() + ", name: " + core.getCard().getName());
        lf = tmpFrame.getContentPane();
        tmpFrame.setSize(450, 300);
        tmpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        lf.setBackground(Color.white);
        tmpFrame.setLayout(layFrame);
        tmpFrame.setVisible(true);
        tmpFrame.add(this);
    }
}
