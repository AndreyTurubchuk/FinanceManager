import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Администратор on 05.02.2016.
 */
public class User {
   private ArrayList<User> users;
    private String name;
    private String login;
    private String password;
    private ArrayList<Account> account2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Account> getAccount2() {
        return account2;
    }

    public void setAccount2(ArrayList<Account> account2) {
        this.account2 = account2;
    }

    public User(String name, String login, String password) throws IOException {
        this.name = name;
        this.login = login;
        this.password = password;
     }


    public void PrintUser(){
        System.out.println(login);
        System.out.println(password);
    }

}
