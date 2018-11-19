package esoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeckWidget extends JPanel{
    public JFrame tmpFrame;
    Container lf;
    GridLayout layFrame = new GridLayout(1, 1, 0, 0);
    BorderLayout layWidget = new BorderLayout();
    GridLayout layPaneShards = new GridLayout(1, 1);
    GridLayout layPaneControls = new GridLayout(1, 1);
    public JPanel paneControls = new JPanel();
    public JPanel paneShards = new JPanel();
    public JTable tableShards = new JTable();
    public JScrollPane panelScrollShards;
    DeckListener listenR;

    public DeckWidget(){
        setLayout(layWidget);
        initCore();
        initPaneControls();
        initPaneShards();

    }

    public void initCore(){
        listenR = new DeckListener(tableShards);
        Core.deck().addTableModelListener(listenR);
    }
    public void initPaneControls(){
        paneControls.setBackground(Color.blue);
        paneControls.setLayout(layPaneControls);

        JButton goShard = new JButton("GO");
        goShard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("... нажата кнопка goShard");
                Card card = new Card();
                //в карту надо записать параметры выбранной пользователем карты
                int id = (int)tableShards.getModel().getValueAt(tableShards.getSelectedRow(), 0);
                String name = (String)tableShards.getModel().getValueAt(tableShards.getSelectedRow(), 1);
                int parent = (int)tableShards.getModel().getValueAt(tableShards.getSelectedRow(), 2);
                card.setID(id);
                card.setName(name);
                card.setParent(parent);
                System.out.println("... выбрана строка: " + tableShards.getSelectedRow());
                System.out.println("... id: " + id);
                System.out.println("... name: " + name);
                System.out.println("... parent: " + parent);
                //передаем карту в новое ядро? надо ли?
                Core core = new Core(card);
                new CoreWidget(core).initFrame();
            }
        });
        paneControls.add(goShard);

        //создает новое корневое ядро с нулевым родителем, новый проект.
        JButton newShard = new JButton("NEW");
        newShard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("... нажата кнопка newShard");
                new CoreWidget().initFrame();

            }
        });
        paneControls.add(newShard);

        this.add(paneControls, layWidget.NORTH);
    }
    public void initPaneShards(){
        paneShards.setBackground(Color.blue);
        paneShards.setLayout(layPaneShards);
        tableShards.setModel(Core.deck());//нужно передавать можель осколка по названию или id
        panelScrollShards = new JScrollPane(tableShards);
        paneShards.add(panelScrollShards, 0);
        this.add(paneShards, layWidget.CENTER);

    }

    public void initFrame() {
        tmpFrame = new JFrame("DeckWidget");
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
        new DeckWidget().initFrame();
    }

}
