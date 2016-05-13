import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Администратор on 05.02.2016.
 */
public class Account {
    private Integer idUsers; // номер пользователя чей счет
    private Integer idAccountsDatabase; // номер счета в базе счетов
    private String description; //текстовое описание
    private Integer balance;     //баланс

    public int getIdUsers() {
        return idUsers;
    }

    public int getIdAccountsDatabase() {
        return idAccountsDatabase;
    }

    public Integer getBalance() {
        return balance;
    }

    public Account(){

    }

    public Account(Integer idUsers, Integer idAccountsDatabase, String description, Integer balance) throws IOException{
        this.idUsers = idUsers;
        this.idAccountsDatabase = idAccountsDatabase;
        this.description = description;
        this.balance = balance;

    }

    public void setIdUsers(Integer idUsers) {
        this.idUsers = idUsers;
    }

    public void setIdAccountsDatabase(Integer idAccountsDatabase) {
        this.idAccountsDatabase = idAccountsDatabase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

}
