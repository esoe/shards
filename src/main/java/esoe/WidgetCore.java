package esoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * панелька для отображения данных Shard
 */
public class WidgetCore extends JPanel {
    public JFrame tmpFrame;
    Container lf;
    BorderLayout bl = new BorderLayout();
    GridLayout gl = new GridLayout();
    public JPanel panelText = new JPanel();
    public JTextArea ta = new JTextArea();
    public JScrollPane panelScroll;

    public WidgetCore(){
        this.panelScroll = new JScrollPane(this.ta);
        this.setLayout(this.bl);
        this.initPanelText();
        this.setVisible(true);
    }

    public void initCore(){
        //
    }

    public void initFrame() {
        this.tmpFrame = new JFrame("WidgetCore");
        this.lf = this.tmpFrame.getContentPane();
        this.tmpFrame.setSize(450, 300);
        this.tmpFrame.setDefaultCloseOperation(3);
        this.lf.setBackground(Color.white);
        this.tmpFrame.setLayout(this.gl);
        this.tmpFrame.setVisible(true);
        this.tmpFrame.add(this);
    }

    public void initPanelText() {
        this.panelText.setBackground(Color.blue);
        this.panelText.setLayout(this.gl);
        this.panelText.add(this.panelScroll, 0);
        BorderLayout var10002 = this.bl;
        this.add(this.panelText, "Center");
    }

    public static void main( String[] args )
    {
        System.out.println( "... запущен метод main класса WidgetCore проекта shards  ... " );
        new WidgetCore().initFrame();
    }


}

