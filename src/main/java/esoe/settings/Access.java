package esoe.settings;

import esoe.build.LoginWidget;

/**
 * класс обеспечения доступа к базе данных
 */

public class Access {
    private static String login;
    private static String password;

    public Access(){
        LoginWidget lw = new LoginWidget();
        lw.initFrame();
    }
    public Access(String login, String password){
        setLogin(login);
        setPassword(password);
    }
    public static void setLogin(String user){
        login = user;
    }
    public static void setPassword(String pass){
        password = pass;
    }
    public static String getLogin(){
        return login;
    }
    public static String getPassword() {
        return password;
    }
}
