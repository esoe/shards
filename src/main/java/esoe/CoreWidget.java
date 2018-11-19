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
    GridLayout layWidget = new GridLayout(1, 2);
    GridLayout layPaneShards = new GridLayout(1, 1);
    GridLayout layPaneControls = new GridLayout(4, 1);
    public JPanel paneControls = new JPanel();
    public JPanel paneShards = new JPanel();
    public JTable tableShards = new JTable();
    public JScrollPane panelScrollShards;
    public Core core;

    public CoreWidget(){
        setLayout(layWidget);
        initCore();
        initPaneControls();
        initPaneShards();

    }
    public CoreWidget(Core core){
        setLayout(layWidget);
        initCore(core);
        initPaneControls();
        initPaneShards();
    }
    public void initCore(){
        core = new Core();//запустили пустое ядро
        tableShards.setModel(Core.shards(core.getCard()));

    }
    public void initCore(Core core) {
        this.core = core;
        tableShards.setModel(Core.shards(core.getCard()));
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
                Card c = new Card(name, core.getCard().getId());
                Core.deck().add(c);//добавили карту в колоду
                tableShards.setModel(Core.shards(core.getCard()));

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

        this.add(paneControls);
    }
    public void initPaneShards(){
        paneShards.setBackground(Color.blue);
        paneShards.setLayout(layPaneShards);
        //передаю реестр, надо же получить список shards
        panelScrollShards = new JScrollPane(tableShards);
        paneShards.add(panelScrollShards, 0);
        this.add(paneShards);

    }

    public void initFrame() {
        tmpFrame = new JFrame("Core" +  ": " );
        lf = tmpFrame.getContentPane();
        tmpFrame.setSize(450, 300);
        tmpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        lf.setBackground(Color.white);
        tmpFrame.setLayout(layFrame);
        tmpFrame.setVisible(true);
        tmpFrame.add(this);
    }

    public static void main( String[] args )
    {
        System.out.println( "... запущен метод main класса CoreWidget проекта shards  ... " );
        new CoreWidget().initFrame();
        new DeckWidget().initFrame();
    }


}

