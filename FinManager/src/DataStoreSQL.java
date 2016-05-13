
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


/**
 * Created by Администратор on 23.02.2016.
 */

public class DataStoreSQL implements IDataStoreSQL {

   // @Override
    public void addUserSQL(User user) { // регистрация, добавление нового пользователя,
        Statement stat = null;
        PreparedStatement pst = null;
        DBConnection dbConnection = new DBConnection();
        try {
            Connection con = dbConnection.getCon();
            String sql = "INSERT INTO `mydb`.`users` (`username`, `login`, `password`) VALUES (?,?,?);";
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getPassword());
            pst.executeUpdate();
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   // @Override
    public boolean authenticateNameUser(String nameUser) {
        Statement stat = null;
        ResultSet result;
        Boolean resultAuthenticateNameUser = false;
        DBConnection dbConnection = new DBConnection();
        try {
            Connection con = dbConnection.getCon();
            stat = con.createStatement();
            String sql = "SELECT * FROM users;";
            result = stat.executeQuery(sql);
            while (result.next()) {
                if (result.getString("username").equals(nameUser)) { // если логин  и пароль есть в БД, то вернет true
                    resultAuthenticateNameUser = true;
                    break;
                }
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return resultAuthenticateNameUser;
    }

    public Integer authenticateLoginPasswordUser(String textLoginUser, String textPasswordUser){ // проверка наличия пользователя в БД
        Statement stat = null;
        ResultSet result;
        Integer IdUser =0;
        DBConnection dbConnection = new DBConnection();
        try {
            Connection con = dbConnection.getCon();
            stat = con.createStatement();
            String sql = "SELECT * FROM users;";
            result = stat.executeQuery(sql);
            while (result.next()) {
                if ((result.getString("login").equals(textLoginUser) &&
                    (result.getString("password").equals(textPasswordUser)))) { // если логин  и пароль есть в БД, то вернет true
                    IdUser = result.getInt("idusers");
                    break;
                }
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return IdUser;
    }

    //@Override
    public String getNameUser(Integer IdUser) {
        Statement stat = null;
        ResultSet result;
        String resultGetNameUser = "";
        DBConnection dbConnection = new DBConnection();
        try {
            Connection con = dbConnection.getCon();
            stat = con.createStatement();
            String sql = "SELECT * FROM users;";
            result = stat.executeQuery(sql);
            while (result.next()) {
                if (result.getInt("idusers") == IdUser) {
                    resultGetNameUser  = result.getString("username");
                    break;
                }
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return resultGetNameUser;
    }

  //  @Override
    public void addAccount(Account account) {
        Statement stat = null;
        PreparedStatement pst = null;
        DBConnection dbConnection = new DBConnection();
        try {
            Connection con = dbConnection.getCon();
            String sql2 = "INSERT INTO `mydb`.`accounts` (`description`, `balance`, `idusers`, `idaccountsdatabase`) VALUES (?,?,?,?);";
            pst = con.prepareStatement(sql2);
            pst.setString(1, account.getDescription());
            pst.setInt(2, account.getBalance());
            pst.setInt(3, account.getIdUsers());
            pst.setInt(4, account.getIdAccountsDatabase());
            pst.executeUpdate();
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   // @Override
    public void removeAccount(String numberAccount ) {
        Statement stat = null;
        ResultSet result;
        PreparedStatement pst = null;
        Integer IdNumberAccount = 0;
        DBConnection dbConnection = new DBConnection();
        try {
            Connection con = dbConnection.getCon();
            //Поиск id счета по его номеру
            stat = con.createStatement();
            String sql = "SELECT * FROM mydb.accounts, mydb.accountsdatabase WHERE accounts.idaccountsdatabase = accountsdatabase.idaccountsDatabase;";
            result = stat.executeQuery(sql);
            while (result.next()) {
                if (result.getString("numberAccounts").equals(numberAccount)) {
                    IdNumberAccount = result.getInt("idaccounts");
                    break;
                }
            }
            String sql2 = " DELETE FROM `mydb`.`accounts` WHERE `idaccounts` = ?;";
            pst = con.prepareStatement(sql2);
            pst.setInt(1,IdNumberAccount);
            pst.executeUpdate();


        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //@Override
    public void addRecordTransaction(Integer idUser, String numberAccount, Integer balanceAccount, Integer typeTrn, Integer sum, String description) {
        Statement stat = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        DBConnection dbConnection = new DBConnection();
        Integer IdNumberAccount = 0;
        ResultSet result;
        try {
            Connection con = dbConnection.getCon();
           //Поиск id счета по его номеру
            stat = con.createStatement();
            String sql = "SELECT * FROM mydb.accounts, mydb.accountsdatabase WHERE accounts.idaccountsdatabase = accountsdatabase.idaccountsDatabase;";
            result = stat.executeQuery(sql);
            while (result.next()) {
                if (result.getString("numberAccounts").equals(numberAccount)) {
                    IdNumberAccount = result.getInt("idaccounts");
                    break;
                }
            }

            //транзакция
            String sql3 = "INSERT INTO `mydb`.`transaction` (`descriptions`, `summ`, `data`, `typeOfOperation`, `idAccount`) VALUES (?,?,?,?,?);";
            pst = con.prepareStatement(sql3);
            pst.setString(1, description);
            pst.setInt(2, sum);
            Date d = new Date();
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            format1.format(d);
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            pst.setDate(3, sqlDate);
            pst.setInt(4, typeTrn);
            pst.setInt(5, IdNumberAccount);
            pst.executeUpdate();
              // обновление баланса счета
            String sql2 = "UPDATE `mydb`.`accounts` SET `balance`= ? WHERE `idaccounts`= ?;";
            pst2 = con.prepareStatement(sql2);

            if (typeTrn == 1) {
                pst2.setInt(1, balanceAccount + sum);
            }
            else pst2.setInt(1, balanceAccount - sum);

            pst2.setInt(2, IdNumberAccount);
            pst2.executeUpdate();
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   // @Override
    public Integer getIdUser(String nameUser) {
        Statement stat = null;
        ResultSet result;
        Integer resultGetIdUser = 0;
        DBConnection dbConnection = new DBConnection();
        try {
            Connection con = dbConnection.getCon();
            stat = con.createStatement();
            String sql = "SELECT * FROM users;";
            result = stat.executeQuery(sql);
            while (result.next()) {
                if (result.getString("username").equals(nameUser)) {
                    resultGetIdUser  = result.getInt("idusers");
                    break;
                }
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return  resultGetIdUser;
    }

   // @Override
    public Integer getIDNumberAccount() {
        Statement stat = null;
        ResultSet result;
        Integer idNumberAccount = (Integer) 0;
        DBConnection dbConnection = new DBConnection();
        try {
            Connection con = dbConnection.getCon();
            stat = con.createStatement();
            String sql = "SELECT * FROM accounts;";
            result = stat.executeQuery(sql);
            result.next();
            idNumberAccount = 1;
            while (result.next()) {
            if (idNumberAccount < result.getInt("idaccountsdatabase")) idNumberAccount = result.getInt("idaccountsdatabase");
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return  ++idNumberAccount;
    }

    public String getNumberAccount(Integer IDAccountDataBase) {
        Statement stat = null;
        ResultSet result;
        Account account = null;
        String t = "";
        DBConnection dbConnection = new DBConnection();
        try {
            Connection con = dbConnection.getCon();
            stat = con.createStatement();
            String sql = "SELECT numberAccounts FROM mydb.accountsdatabase where idaccountsdatabase = " + IDAccountDataBase + ";";
            result =  stat.executeQuery(sql);
            while (result.next()) {
               t = result.getString("numberAccounts");
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return t;
    }

    //@Override
    public ArrayList getAccountsUser(Integer IdUser) {
        Statement stat = null;
        ResultSet result;
        ArrayList<Integer> str = new ArrayList<Integer>();
        DBConnection dbConnection = new DBConnection();
        try {
            Connection con = dbConnection.getCon();
            stat = con.createStatement();
            String sql = "SELECT * FROM accounts;";
            result = stat.executeQuery(sql);
            while (result.next()) {
                if (IdUser.equals(result.getInt("idusers"))) str.add(result.getInt("idaccountsdatabase")) ;
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return (ArrayList) str;
    }

  //  @Override
    public ArrayList getAccountsUser(String NameUser) {
        Statement stat = null;
        ResultSet result;
        List<String> str = new ArrayList<String>();
        DBConnection dbConnection = new DBConnection();
        try {
            Connection con = dbConnection.getCon();
            stat = con.createStatement();
            String sql = "SELECT us.username, acc.description, acc.balance, numberAccounts FROM mydb.accountsdatabase, mydb.users us join mydb.accounts acc ON us.idusers = acc.idusers WHERE acc.idaccountsdatabase = mydb.accountsdatabase.idaccountsDatabase AND username = '";
            sql = sql + NameUser +"';";
            result = stat.executeQuery(sql);
            while (result.next()) {
                str.add(result.getString("numberAccounts"));
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return (ArrayList) str;
    }

   // @Override
    public Account getAccount(String numberAccount) {
        Statement stat = null;
        ResultSet result;
        Account account = null;
        DBConnection dbConnection = new DBConnection();
        try {
            Connection con = dbConnection.getCon();
            stat = con.createStatement();
            String sql = "SELECT acc.description, acc.balance, mydb.accountsdatabase.numberAccounts, acc.idaccounts, acc.idusers FROM mydb.accountsdatabase, mydb.users us join mydb.accounts acc ON us.idusers = acc.idusers WHERE acc.idaccountsdatabase = mydb.accountsdatabase.idaccountsDatabase;";
            result = stat.executeQuery(sql);
            while (result.next()) {
                if (numberAccount.equals(result.getString("numberAccounts"))){
                    try {
                        account = new Account(result.getInt("acc.idusers"), result.getInt("acc.idaccounts"), result.getString("acc.description"), result.getInt("acc.balance"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return account;
    }

    public ArrayList<RecordTransaction> getTrnTotal(String numberAccount) {
        Statement stat = null;
        ResultSet result;
        List<RecordTransaction> transactions = new ArrayList<RecordTransaction>();
        RecordTransaction recordTransaction;
        DBConnection dbConnection = new DBConnection();
        try {
            Connection con = dbConnection.getCon();
            stat = con.createStatement();
            String sql = "SELECT mydb.transaction.data, mydb.transaction.descriptions, mydb.transaction.summ, mydb.transaction.typeOfOperation, mydb.accountsdatabase.numberAccounts " +
                    "FROM mydb.transaction "
                    + "inner join mydb.accounts on  mydb.transaction.idAccount = mydb.accounts.idaccounts "
                    + "inner join mydb.accountsdatabase on mydb.accounts.idaccountsdatabase = mydb.accountsdatabase.idaccountsDatabase "
                    + "WHERE mydb.accountsdatabase.numberAccounts = "
                    + numberAccount + " ;";
            result = stat.executeQuery(sql);
            while (result.next()) {
                    recordTransaction = new RecordTransaction(new Date(), result.getInt("typeOfOperation"), result.getInt("summ"), result.getString("descriptions"));
                    transactions.add(recordTransaction);
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            if(stat !=null) {
                try {
                    stat.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return (ArrayList) transactions;
          }


}

