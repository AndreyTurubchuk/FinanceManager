import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Администратор on 14.02.2016.
 */
public class BeginFrame extends JFrame {

    private JTextField loginUserText;
    private JLabel loginUserLabel;
    private JTextField passwordUserText;
    private JLabel passwordUserLabel;
    private JButton btnLogin;
    private JButton btnCreateNewUser;
    private JPanel panel;
    private DataStoreSQL dataStoreSQL = new DataStoreSQL();

    protected ActionListener createNewUserListener = new ActionListener() { // регистрация, создание пользователя
        public void actionPerformed(ActionEvent e) {
            dispose();
            Frame addUserFrame = new AddUserFrame();
                addUserFrame.setVisible(true);
        }
    };

    protected ActionListener loginListener = new ActionListener() { // вход в систему
        public void actionPerformed(ActionEvent e) {
           Integer IdUser = dataStoreSQL.authenticateLoginPasswordUser(loginUserText.getText(), passwordUserText.getText());
             if (!(IdUser ==0)) {// истина если логин и пароль пользователя совпадает с логином БД
                dispose();
                Frame mainFrame = new MainFrame(IdUser, "");
                mainFrame.setVisible(true);
             }
             else {
                String message = "Неверный логин или пароль или пользователя нет в системе";
                JOptionPane.showMessageDialog(null, message, "My Message", JOptionPane.PLAIN_MESSAGE);
             }
        }
    };

    BeginFrame(){
        super("Финансовый менеджер");
        InitPanel();
        InitLoginUser();
        InitPasswordUser();
        InitBtnLogin();
        IntiBtnCreateNewUser();
        AddElementPanel();
        btnCreateNewUser.addActionListener(createNewUserListener);
        btnLogin.addActionListener(loginListener);
    }

    private void IntiBtnCreateNewUser() {
        btnCreateNewUser = new JButton("Регистрация");
        btnCreateNewUser.setSize(120, 30);
        btnCreateNewUser.setLocation(240,210);
    }

    private void InitBtnLogin() {
        btnLogin = new JButton("Войти");
        btnLogin.setSize(80, 30);
        btnLogin.setLocation(20,210);
    }

    private void InitPasswordUser() {
        passwordUserText = new JPasswordField();
        passwordUserText.setSize(340, 30);
        passwordUserText.setLocation(20, 150);
        passwordUserLabel = new JLabel("Пароль");
        passwordUserLabel.setSize(80, 30);
        passwordUserLabel.setLocation(20,120);
    }

    private void AddElementPanel() {
        panel.add(loginUserText);
        panel.add(loginUserLabel);
        panel.add(passwordUserText);
        panel.add(passwordUserLabel);
        panel.add(btnLogin);
        panel.add(btnCreateNewUser);
    }

    private void InitLoginUser() {
        loginUserText = new JTextField();
        loginUserText.setSize(340, 30);
        loginUserText.setLocation(20, 80);
        loginUserLabel = new JLabel("Логин");
        loginUserLabel.setSize(80, 30);
        loginUserLabel.setLocation(20,50);
    }

    private void InitPanel() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,300);
        setVisible(true);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10), new TitledBorder("Вход в систему")));
        add(panel);
    }
}



