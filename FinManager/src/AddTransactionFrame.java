import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Comparator;

/**
 * Created by Администратор on 28.02.2016.
 */
public class AddTransactionFrame extends JFrame {
    private JLabel lblNumberAccount;
    private JLabel LblValueNumberAccount;
    private JLabel lblAddTrnComment;
    private JTextField txtAddTrnComment; // коментарии к операции по счету
    private JTextField txtSumTrn; // сумма операции
    private JButton btnAddTrn;
    private JButton btnCancel;
    private JRadioButton rbtnPlusSumTrn;
    private JRadioButton rbtnMinusSumTrn;
    private JLabel lblSumTrn;
    private JPanel jpAddTrnFrame;
    private Integer idUser;
    private String numberAccount;
    private Integer balanceAccount;
    private Integer sum;
    private DataStoreSQL dataStoreSQL = new DataStoreSQL();

    public AddTransactionFrame(Integer idUser, String numberAccount, Integer balanceAccount){
        super("Финансовый менеджер");
        this.idUser = idUser;
        this.numberAccount = numberAccount;
        this.balanceAccount = balanceAccount;
        InitAddTransactionFrame();
        InitLblNumberAccount();
        InitLblValueNumberAccount();
        InitSumTransaction();
        InitAddTrnComment();
        InitRbtnPlusMinus();
        InitBtnAddTrn();
        InitBtnCancel();
        InitAddElementPanel();
        add(jpAddTrnFrame);
        btnAddTrn.addActionListener(btnAddTrnListener);
        btnCancel.addActionListener(btnCancelListener);
        rbtnPlusSumTrn.addActionListener(rbtnPlusSumTrnListener);
        rbtnMinusSumTrn.addActionListener(rbtnMinusSumTrnListener);
    }

    private void InitAddTransactionFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);
    }

    private void InitBtnAddTrn() {
        btnAddTrn = new JButton("ОК");
        btnAddTrn.setSize(80, 30);
        btnAddTrn.setLocation(20,310);

    }

    private void InitBtnCancel() {
        btnCancel = new JButton("Отмена");
        btnCancel.setSize(80, 30);
        btnCancel.setLocation(275,310);
    }

    private void InitAddElementPanel() {
        jpAddTrnFrame = new JPanel();
        jpAddTrnFrame.setLayout(null);
        jpAddTrnFrame.setBorder(new CompoundBorder(new EmptyBorder(10, 10,10,10), new TitledBorder("Операции по счету")));
        jpAddTrnFrame.add(lblAddTrnComment);
        jpAddTrnFrame.add(txtAddTrnComment);
        jpAddTrnFrame.add(lblSumTrn);
        jpAddTrnFrame.add(txtSumTrn);
        jpAddTrnFrame.add(rbtnPlusSumTrn);
        jpAddTrnFrame.add(rbtnMinusSumTrn);
        jpAddTrnFrame.add(LblValueNumberAccount);
        jpAddTrnFrame.add(lblNumberAccount);
        jpAddTrnFrame.add(btnAddTrn);
        jpAddTrnFrame.add(btnCancel);
    }

    private void InitAddTrnComment() {
        lblAddTrnComment = new JLabel("Примечание");
        lblAddTrnComment.setSize(250, 30);
        lblAddTrnComment.setLocation(20,210);
        txtAddTrnComment = new JTextField();
        txtAddTrnComment.setSize(340,30);
        txtAddTrnComment.setLocation(20, 240);
    }

    private void InitSumTransaction() {
        lblSumTrn = new JLabel("Введите сумму операции");
        lblSumTrn.setSize(250, 30);
        lblSumTrn.setLocation(20,135);
        txtSumTrn = new JTextField();
        txtSumTrn.setSize(150,30);
        txtSumTrn.setLocation(20, 165);
    }

    private void InitLblValueNumberAccount() {
        LblValueNumberAccount = new JLabel(numberAccount);
        LblValueNumberAccount.setSize(200, 30);
        LblValueNumberAccount.setLocation(20,70);
        LblValueNumberAccount.setBackground(new Color(255, 104, 43));
        Font font = new Font("Courier", Font.BOLD,20);
        LblValueNumberAccount.setFont(font);
    }

    private void InitLblNumberAccount() {
        lblNumberAccount = new JLabel("Текущий счет");
        lblNumberAccount.setSize(100, 30);
        lblNumberAccount.setLocation(20,40);

    }

    private void InitRbtnPlusMinus() {
        rbtnPlusSumTrn = new JRadioButton("Положить", true);
        rbtnPlusSumTrn.setSize(100,20);
        rbtnPlusSumTrn.setLocation(220,160);
        rbtnMinusSumTrn = new JRadioButton("Снять", false);
        rbtnMinusSumTrn.setSize(100,20);
        rbtnMinusSumTrn.setLocation(220,180);
    }

    boolean charIsInteger(char value) { //является ли символ числом
        char[] arrayOfNumbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        boolean isFound = false;
        for (int i = 0; i < arrayOfNumbers.length ; i++) {
            if (value == arrayOfNumbers[i]) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    boolean stringIsInteger(String value) { //является ли строка числом
        boolean isFound =  false;
        for (int i = 0; i < value.length(); i++) {
            if (charIsInteger(value.charAt(i)) == false) {
                isFound = false;
                break;
            }
            else isFound = true;
            if (isFound == false) break;
        }
        return isFound;
    }

    protected ActionListener btnAddTrnListener = new ActionListener() { // обработка кнопки добавления транзакции по счету
        //@Override
        public void actionPerformed(ActionEvent e) {

            if (stringIsInteger(txtSumTrn.getText()) == true) {
                sum = Integer.parseInt(txtSumTrn.getText());
                Integer typeTrn = (rbtnPlusSumTrn.isSelected() == true) ? 1: 0;
                dataStoreSQL.addRecordTransaction(idUser, numberAccount, balanceAccount, typeTrn,sum, txtAddTrnComment.getText());
                dispose();
                MainFrame mainFrame = new MainFrame(idUser, numberAccount);
                mainFrame.setVisible(true);
            }
            else {
                String message = "Неверно  введена сумма транзакции";
                JOptionPane.showMessageDialog(null, message, "My Message", JOptionPane.PLAIN_MESSAGE);
            }
        }
    };

    protected ActionListener btnCancelListener = new ActionListener() { // обработка кнопки отмены транзакции
        //@Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            MainFrame mainFrame = new MainFrame(idUser, numberAccount);
            mainFrame.setVisible(true);
        }
    };



    protected  ActionListener rbtnPlusSumTrnListener = new ActionListener() { // обработка кнопки добавления транзакции по счету
        //@Override
        public void actionPerformed(ActionEvent e) {
            rbtnPlusSumTrn.setSelected(true);
            rbtnMinusSumTrn.setSelected(false);
        }
    };

    protected  ActionListener rbtnMinusSumTrnListener = new ActionListener() { // обработка кнопки добавления транзакции по счету
        //@Override
        public void actionPerformed(ActionEvent e) {
            rbtnMinusSumTrn.setSelected(true);
            rbtnPlusSumTrn.setSelected(false);
        }
    };

}
