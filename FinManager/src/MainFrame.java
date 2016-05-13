import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Администратор on 22.02.2016.
 */
public class MainFrame extends JFrame {
    private String nameUser;
    private Integer IdUser;
    private JLabel helloUser;
    private Integer balanceAccount;
    private JLabel balanceAccountLabel;
    private JTextField balanceAccountText;
    private JLabel descriptionAccountLabel;
    private JTextField descriptionAccountText;
    private JLabel numberAccountLabel;
    private JButton btnAddAccount;
    private JButton btnAddTransaction;
    private JButton btnRemoveAccount;
    private JButton btnExit;
    private JButton btnCancel;
    private JComboBox cmboxAccountTotal;
    private JPanel panel;
    private JTable jTabTransaction;
    private DataStoreSQL dataStoreSQL = new DataStoreSQL();
    private JScrollPane jscrlp;
    private String numberAccount;

    protected ActionListener addAccountListener = new ActionListener() { // обработка кнопки добавления счета
       // @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            AddAccountFrame addAccountFrame = new AddAccountFrame(IdUser); // передать id пользователя
            addAccountFrame.setVisible(true);
        }
    };

    protected ActionListener addTransactionListener = new ActionListener() { // обработка кнопки добавления транзакции
       // @Override
        public void actionPerformed(ActionEvent e) {
            if (numberAccount != "") {
                dispose();
                numberAccount = cmboxAccountTotal.getSelectedItem().toString(); // номер счета из выпадающего списка счетов
                AddTransactionFrame addTransactionFrame = new AddTransactionFrame(IdUser, numberAccount, balanceAccount);
                addTransactionFrame.setVisible(true);
            } else {
                String message = "У Вас нет открытых счетов. Вы не можете провести транзакцию.";
                JOptionPane.showMessageDialog(null, message, "My Message", JOptionPane.PLAIN_MESSAGE);
            }
        }
    };

    protected ActionListener  removeAccountListener = new ActionListener() { // обработка кнопки удаления счета
       // @Override
        public void actionPerformed(ActionEvent e) {
            if (numberAccount != "") {
                dispose();
                String numberAccount = cmboxAccountTotal.getSelectedItem().toString(); // номер счета из выпадающего списка счетов
                dataStoreSQL.removeAccount(numberAccount);
                MainFrame mainFrame = new MainFrame(IdUser,"");  // открывание нового окна с обновленными данными
                mainFrame.setVisible(true);
            }
            else {
                String message = "Вы не выбрали счет или у Вас нет открытых счетов";
                JOptionPane.showMessageDialog(null, message, "My Message", JOptionPane.PLAIN_MESSAGE);
            }
        }
    };


    protected PopupMenuListener popupCmboxAccountTotalListener  = new PopupMenuListener() { // иннициализация выпадающего списка
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            cmboxAccountTotal.removeAllItems();
            ArrayList accountsUser = dataStoreSQL.getAccountsUser(nameUser);
            for (Object accountUser: accountsUser){
                cmboxAccountTotal.addItem(accountUser);
            }
        }
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }
        public void popupMenuCanceled(PopupMenuEvent e) {
        }
    } ;

    protected ActionListener cmboxAccountTotalListener = new ActionListener() { // заполнеие информации о счете
        public void actionPerformed(ActionEvent e) {
            if (cmboxAccountTotal.getSelectedItem() != null) {
                String s = cmboxAccountTotal.getSelectedItem().toString();
                Account account = dataStoreSQL.getAccount(s);
                balanceAccount = account.getBalance();
                balanceAccountText.setText(account.getBalance().toString());
                descriptionAccountText.setText(account.getDescription());
                numberAccount = s;
                InitTabTrn(s);
            }

        }
    };

        protected ActionListener btnCancelListener = new ActionListener() { // отмена, возврат к начальному фрейму
        //@Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            JFrame FinManager = new BeginFrame();
        }
    };

    public MainFrame(Integer IdUser, String numberAccount) {
        super("Финансовый менеджер");
        InitPanel();
        this.IdUser = IdUser;
        this.numberAccount = numberAccount;
        nameUser = dataStoreSQL.getNameUser(IdUser);
        InitHelloUser();
        InitBtnCancel();
        InitBalanceAccount();
        InitDescriptionAccount();
        InitAddAccountButton();
        InitRemoveAccountButton();
        InitTransactionButton();
        InitNumberAccountLabel();
        InitCmboxAccountTotal();
        InitTabTrn(numberAccount);
        AddElementPanel();
        btnAddAccount.addActionListener(addAccountListener);
        btnRemoveAccount.addActionListener(removeAccountListener);
        btnAddTransaction.addActionListener(addTransactionListener);
        cmboxAccountTotal.addActionListener(cmboxAccountTotalListener);
        cmboxAccountTotal.addPopupMenuListener(popupCmboxAccountTotalListener);
        btnCancel.addActionListener(btnCancelListener);
    }

    private void InitBtnCancel() {
        btnCancel = new JButton("Отмена");
        btnCancel.setSize(110, 30);
        btnCancel.setLocation(555,515);
    }

    protected void InitTabTrn(String numberAccount) {
        if (numberAccount !="") {
            if (jscrlp != null) {
                panel.remove(jscrlp);
            }
            ArrayList<RecordTransaction> transactionTotal = new ArrayList<RecordTransaction>();
            transactionTotal = dataStoreSQL.getTrnTotal(numberAccount);
            Object[] headers2 = {"Сумма", "Дата совершения", "Примечание"};
            //Массив содержащий информацию для таблицы
            Object[][] data2 = null;
            data2 = new Object[transactionTotal.size()][3];
            Integer i = 0;
            for (Object trn : transactionTotal) {
                String signTrn = (transactionTotal.get(i).getTypeTrn() == 1) ? "+" : "-";
                data2[i][0] = signTrn + transactionTotal.get(i).getSumTrn();
                data2[i][1] = transactionTotal.get(i).getDataRecTrn();
                data2[i][2] = transactionTotal.get(i).getCommentTrn();
                i++;
            }
            jTabTransaction = null;
            jTabTransaction = new JTable(data2, headers2);
            jTabTransaction.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTabTransaction.getColumnModel().getColumn(2).setPreferredWidth(20);
            jscrlp = new JScrollPane(jTabTransaction);
            //Устаналиваем размеры прокручиваемой области
            jTabTransaction.setPreferredScrollableViewportSize(new Dimension(250, 100));
            jscrlp.setLocation(20, 260);
            jscrlp.setSize(345, 200);
            panel.add(jscrlp);
        }
        else
            InitTabTrn();
    }

    protected void InitTabTrn() {
        Object[] headers = {"Сумма","Дата совершения", "Примечание"};
        //Массив содержащий информацию для таблицы
        Object[][] data = null;
        data =  new Object[1][3];
        data[0][0] =  "";
        data[0][1] = "";
        data[0][2] = "";
        jTabTransaction = null;
        jTabTransaction = new JTable(data, headers);
        jTabTransaction.getColumnModel().getColumn(0).setPreferredWidth(5);
        jTabTransaction.getColumnModel().getColumn(2).setPreferredWidth(20);
        jscrlp = new JScrollPane(jTabTransaction);
        //Устаналиваем размеры прокручиваемой области
        jTabTransaction.setPreferredScrollableViewportSize(new Dimension(250, 100));
        jscrlp.setLocation(20,260);
        jscrlp.setSize(345,200);
        panel.add(jscrlp);
    }

    private void AddElementPanel() {
        panel.add(helloUser);
        panel.add(balanceAccountLabel);
        panel.add(balanceAccountText);
        panel.add(numberAccountLabel);
        panel.add(cmboxAccountTotal);
        panel.add(btnAddAccount);
        panel.add(btnRemoveAccount);
        panel.add(descriptionAccountLabel);
        panel.add(descriptionAccountText);
        panel.add(btnAddTransaction);
        panel.add(btnCancel);
    }

    private void InitNumberAccountLabel() {
        numberAccountLabel = new JLabel("Выберите номер счета из списка");
        numberAccountLabel.setSize(250, 30);
        numberAccountLabel.setLocation(20,60);
    }

    private void InitCmboxAccountTotal() {
        cmboxAccountTotal = new JComboBox();
        cmboxAccountTotal.setSize(340,25);
        cmboxAccountTotal.setLocation(20,100);
        if (numberAccount != "") {
            cmboxAccountTotal.removeAllItems();
            cmboxAccountTotal.addItem(numberAccount);
            InitTabTrn(numberAccount);
            Account account = dataStoreSQL.getAccount(numberAccount);
            balanceAccount = account.getBalance();
            balanceAccountText.setText(account.getBalance().toString());
            descriptionAccountText.setText(account.getDescription());
        }
    }

    private void InitTransactionButton() {
        btnAddTransaction = new JButton("Провести транзакцию");
        btnAddTransaction.setSize(165, 30);
        btnAddTransaction.setLocation(420,340);
    }

    private void InitRemoveAccountButton() {
        btnRemoveAccount = new JButton("Удалить счет");
        btnRemoveAccount.setSize(120, 30);
        btnRemoveAccount.setLocation(420,300);
    }

    private void InitAddAccountButton() {
        btnAddAccount = new JButton("Добавить счет");
        btnAddAccount.setSize(120, 30);
        btnAddAccount.setLocation(420,260);
    }

    private void InitDescriptionAccount() {
        descriptionAccountText = new JTextField("");
        descriptionAccountText.setSize(250, 30);
        descriptionAccountText.setLocation(420,195);
        descriptionAccountLabel = new JLabel("Описание счета");
        descriptionAccountLabel.setSize(150, 30);
        descriptionAccountLabel.setLocation(420,165);
    }

    private void InitBalanceAccount() {
        balanceAccountLabel = new JLabel("Баланс счета");
        balanceAccountLabel.setSize(100, 30);
        balanceAccountLabel.setLocation(420,95);
        balanceAccountText = new JTextField();
        balanceAccountText.setSize(120, 30);
        balanceAccountText.setLocation(420,125);
    }

    private void InitPanel() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,600);
        setVisible(true);
        panel = new JPanel();
        panel.setLayout(null);
        add(panel);
    }

    private void InitHelloUser(){
        helloUser = new JLabel("Здравствуйте, " + nameUser);
        helloUser.setSize(150, 30);
        helloUser.setLocation(20,10);
    }
}
