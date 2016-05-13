import java.util.ArrayList;

/**
 * Created by Администратор on 23.02.2016.
 */
public interface IDataStoreSQL {
    public void addUserSQL(User user);
    public boolean authenticateNameUser(String nameUser);
    public Integer authenticateLoginPasswordUser(String textLoginUser, String textPasswordUser);
    public String getNameUser(Integer IdUser);

    void addAccount(Account account);
    void removeAccount(String  NumberAccount);
    void addRecordTransaction(Integer idUser, String numberAccount, Integer typeTrn, Integer balanceAccount, Integer sum, String description); // номер счета, бланс счета, сумма транзакции
    public Integer getIdUser(String nameUser);
    public Integer getIDNumberAccount();
    public String getNumberAccount(Integer IDAccountDataBase); // получение счета по его номеру из справочника счетов
    //void addAccount();
    public ArrayList getAccountsUser(Integer IdUser);
    public ArrayList getAccountsUser(String NameUser);
    public Account getAccount(String numberAccount);
    public ArrayList getTrnTotal(String numberAccount);
}
