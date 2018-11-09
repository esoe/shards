package esoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WidgetReestr extends JPanel{
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
    Core core;
    ReestrListener listenR;

    public WidgetReestr(){
        setLayout(layWidget);
        initCore();
        initPaneControls();
        initPaneShards();

    }

    public void initCore(){
        core = new Core(Core.getDefaultCard());
        listenR = new ReestrListener(tableShards);
        Core.getReestr().addTableModelListener(listenR);
    }
    public void initPaneControls(){
        paneControls.setBackground(Color.blue);
        paneControls.setLayout(layPaneControls);

        JButton goShard = new JButton("GO");
        goShard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Нажата кнопка goShard");
                /**
                 * должен открыться WidgetCore с соответствующим Core и списком Shards
                 * надо в виджет сообщать какой core открывать или формировать.
                 * new WidetCore(core);
                 */
            }
        });
        paneControls.add(goShard);

        this.add(paneControls, layWidget.NORTH);


    }
    public void initPaneShards(){
        paneShards.setBackground(Color.blue);
        paneShards.setLayout(layPaneShards);
        tableShards.setModel(Core.getReestr());//нужно передавать можель осколка по названию или id
        //отображаем заголовки новой модели в таблице
        int i = 0;
        while (i < Core.getReestr().getColumnCount()) {
            tableShards.getColumnModel().getColumn(i).setHeaderValue(Core.getReestr().getHeader()[i]);
            i++;
        }
        panelScrollShards = new JScrollPane(tableShards);
        paneShards.add(panelScrollShards, 0);
        this.add(paneShards, layWidget.CENTER);

    }

    public void initFrame() {
        tmpFrame = new JFrame("WidgetReestr");
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
        System.out.println( "... запущен метод main класса WidgetReestr проекта shards  ... " );
        new WidgetReestr().initFrame();
    }

}
