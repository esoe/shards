package esoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * панелька для отображения данных Shard
 *
 */
public class WidgetCore extends JPanel {
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

    public WidgetCore(){
        setLayout(layWidget);
        initCore();
        initPaneControls();
        initPaneShards();

    }
    public WidgetCore(Core core){
        setLayout(layWidget);
        initCore(core);
        initPaneControls();
        initPaneShards();
    }
    public void initCore(){
        core = new Core();//запустили пустое ядро
        tableShards.setModel(core.getShards());

    }
    public void initCore(Core core) {
        tableShards.setModel(core.getShards());
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
                Card c = new Card(name, core.getCard().getId());
                core.getShards().add(c);//добавили карту к осколкам
                Core.getDeck().add(c);//добавили карту в колоду
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
        tmpFrame.setDefaultCloseOperation(3);
        lf.setBackground(Color.white);
        tmpFrame.setLayout(layFrame);
        tmpFrame.setVisible(true);
        tmpFrame.add(this);
    }

    public static void main( String[] args )
    {
        System.out.println( "... запущен метод main класса WidgetCore проекта shards  ... " );
        new WidgetCore().initFrame();
        new WidgetReestr().initFrame();
    }


}

