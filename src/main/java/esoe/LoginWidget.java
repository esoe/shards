package esoe;

import esoe.settings.Access;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWidget extends JPanel {
    private JFrame tmpFrame;
    private Container lf;
    private GridLayout layFrame = new GridLayout();
    private GridLayout layWidget = new GridLayout(3, 1);
    private JPanel paneControls = new JPanel();
    private GridLayout layControls = new GridLayout(1, 2);
    private JTextField fieldLogin  = new JTextField("user");
    private JPasswordField fieldPassword  = new JPasswordField("default");
    //
    public LoginWidget(){
        init();
    }
    public void init(){
        setLayout(layWidget);
        add(fieldLogin);
        add(fieldPassword);
        initControls();
    }
    public void initControls(){
        paneControls.setLayout(layControls);
        //кнопка отмены авторизации
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Нажата кнопка Cancel");
                tmpFrame.dispose();
            }
        });
        paneControls.add(btnCancel);

        //кнопка отмены авторизации
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Нажата кнопка Login");
                Access.setLogin(fieldLogin.getText());
                Access.setPassword(String.valueOf(fieldPassword.getPassword()));
                tmpFrame.dispose();
                System.out.println(Access.getLogin());
                System.out.println(Access.getPassword());
            }
        });
        paneControls.add(btnLogin);

        add(paneControls);

    }
    //подготовка отображения фрейма
    public void initFrame() {
        tmpFrame = new JFrame("LoginWidget");
        lf = tmpFrame.getContentPane();
        tmpFrame.setSize(300, 100);
        tmpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        lf.setBackground(Color.white);
        tmpFrame.setLayout(layFrame);
        tmpFrame.setVisible(true);
        tmpFrame.add(this);
    }
    public static void main( String[] args ){
        System.out.println( "... запущен метод main класса LoginWidget проекта shards ..." );
        new LoginWidget().initFrame();

    }
}
