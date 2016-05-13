import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Администратор on 25.02.2016.
 */
public class AddAccountFrame extends JFrame {
    private JTextField txtDscptnAccountUser; // описание счета
    private JLabel lblDscptnAccountUser;
    private JPanel addAccountUserFrame;
    private JButton btnAddAccountUser;
    private DataStoreSQL dataStoreSQL = new DataStoreSQL();
    private Integer idUser;

    protected ActionListener btnAddAccountUserListener = new ActionListener() { // обработка кнопки добавления счета
       // @Override
        public void actionPerformed(ActionEvent e) {

            Integer getIDNumberAccount = dataStoreSQL.getIDNumberAccount();
            try {
                dispose();
                Account account = new Account(idUser, getIDNumberAccount, txtDscptnAccountUser.getText(),0);
                dataStoreSQL.addAccount(account);
                String numberAccount = dataStoreSQL.getNumberAccount(getIDNumberAccount);
                MainFrame mainframe = new MainFrame(idUser, numberAccount);
                mainframe.setVisible(true);
            } catch (IOException e1)
                {
                    e1.printStackTrace();
                }
        }
    };

     public AddAccountFrame(Integer idUser){
        super("Финансовый менеджер");
        this.idUser = idUser;
        InitPanel();
        InitDscptnAccountUser();
        InitBtnAddAccountUser();
        AddElementPanel();
        btnAddAccountUser.addActionListener(btnAddAccountUserListener);
    }

    private void InitBtnAddAccountUser() {
        btnAddAccountUser = new JButton("ОК");
        btnAddAccountUser.setSize(60, 30);
        btnAddAccountUser.setLocation(20,310);
    }

    private void InitPanel() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);
        addAccountUserFrame = new JPanel();
        addAccountUserFrame.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10), new TitledBorder("Добавление счета")));
        addAccountUserFrame.setLayout(null);
        add(addAccountUserFrame);
    }

    private void AddElementPanel() {
        addAccountUserFrame.add(txtDscptnAccountUser);
        addAccountUserFrame.add(lblDscptnAccountUser);
        addAccountUserFrame.add(btnAddAccountUser);
    }

    private void InitDscptnAccountUser() {
        txtDscptnAccountUser = new JTextField();
        txtDscptnAccountUser.setSize(340,30);
        txtDscptnAccountUser.setLocation(20, 80);
        lblDscptnAccountUser = new JLabel("Введите текстовое описание счета");
        lblDscptnAccountUser.setSize(250, 30);
        lblDscptnAccountUser.setLocation(20,50);
    }
}
