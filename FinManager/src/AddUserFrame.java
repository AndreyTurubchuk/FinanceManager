import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Администратор on 23.02.2016.
 */
public class AddUserFrame extends JFrame {
    private JTextField txtAddNameUser;
    private JTextField txtAddLoginUser;
    private JTextField txtAddPasswordUser;
    private JLabel lblNameUser;
    private JLabel lblLoginUser;
    private JLabel lblPasswordUser;
    private JButton btnAddUser;
    private JButton btnCancel;
    private JPanel AddUserPanel;
    private DataStoreSQL dataStoreSQL = new DataStoreSQL();

    protected ActionListener AddUserListener = new ActionListener() { // обработка кнопки регистрации пользователя
        public void actionPerformed(ActionEvent e) {
                 if (!dataStoreSQL.authenticateNameUser(txtAddNameUser.getText())) {// истина если имя пользователя совпадает с именем в БД
                    try {
                      dataStoreSQL.addUserSQL(new User(txtAddNameUser.getText(),
                              txtAddLoginUser.getText(),txtAddPasswordUser.getText()));
                      String message = "Регистрация произведена";
                      JOptionPane.showMessageDialog(null, message, "My Message", JOptionPane.PLAIN_MESSAGE);
                      dispose();
                      JFrame FinManager = new BeginFrame();
                    } catch (IOException  e2) {
                    e2.printStackTrace();
                }
            }
            else {
                String message = "Пользователь с таким именем уже есть в системе. Введите другое имя";
                JOptionPane.showMessageDialog(null, message, "My Message", JOptionPane.PLAIN_MESSAGE);
            }
        }
    };

    protected ActionListener CancelAddUserListener = new ActionListener() { // обработка кнопки регистрации пользователя
        public void actionPerformed(ActionEvent e) {
            dispose();
            JFrame FinManager = new BeginFrame();
        }
    };

    public AddUserFrame() {
        super("Финансовый менеджер");
        InitPanel();
        InitAddNameUser();
        InitAddLoginUser();
        InitAddPasswordUser();
        InitBtnAddUser();
        InitBtnCancel();
        AddElementPanel();
        btnAddUser.addActionListener(AddUserListener);
        btnCancel.addActionListener(CancelAddUserListener);
    }

    private void InitPanel() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);
        AddUserPanel = new JPanel();
        AddUserPanel.setLayout(null);
        AddUserPanel.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10), new TitledBorder("Регистрация нового пользователя")));
        add(AddUserPanel);
    }

    private void AddElementPanel() {
        AddUserPanel.add(lblNameUser);
        AddUserPanel.add(lblLoginUser);
        AddUserPanel.add(lblPasswordUser);
        AddUserPanel.add(txtAddNameUser);
        AddUserPanel.add(txtAddLoginUser);
        AddUserPanel.add(txtAddPasswordUser);
        AddUserPanel.add(btnAddUser);
        AddUserPanel.add(btnCancel);
    }

    private void InitBtnAddUser() {
        btnAddUser = new JButton("ОК");
        btnAddUser.setSize(60, 30);
        btnAddUser.setLocation(20,310);
    }

    private void InitAddPasswordUser() {
        lblPasswordUser = new JLabel("Введите пароль");
        lblPasswordUser.setSize(150, 30);
        lblPasswordUser.setLocation(20,190);
        txtAddPasswordUser = new JPasswordField();
        txtAddPasswordUser.setSize(340, 30);
        txtAddPasswordUser.setLocation(20, 220);
    }

    private void InitAddLoginUser() {
        lblLoginUser = new JLabel("Введите логин");
        lblLoginUser.setSize(150, 30);
        lblLoginUser.setLocation(20,120);
        txtAddLoginUser = new JTextField();
        txtAddLoginUser.setSize(340, 30);
        txtAddLoginUser.setLocation(20, 150);
    }

    private void InitAddNameUser() {
        lblNameUser = new JLabel("Введите имя");
        lblNameUser.setSize(150, 30);
        lblNameUser.setLocation(20,50);
        txtAddNameUser = new JTextField();
        txtAddNameUser.setSize(340, 30);
        txtAddNameUser.setLocation(20, 80);
    }

    private void InitBtnCancel() {
        btnCancel = new JButton("Отмена");
        btnCancel.setSize(80, 30);
        btnCancel.setLocation(275,310);
    }
}
