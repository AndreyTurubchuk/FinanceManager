/**
 * Created by Администратор on 06.02.2016.
 */
public interface DataStore {
    User getUser(String name);
    void addUser(User user);
    void addUserSQL(User user);
    void addAccount(User user, Account account);
    void addRecord(Account account, RecordTransaction recordTransaction);
    User removeUser(String name);
    Account removeAccount(User owner, Account account);
    RecordTransaction removeRecord(Account from, RecordTransaction recordTransaction);
}
