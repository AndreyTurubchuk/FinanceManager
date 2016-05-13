import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Администратор on 16.02.2016.
 */
public class DBConnection {
    private Connection con;
    private static DBConnection instance;


    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public DBConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //this.con = DriverManager.getConnection("jdbc:mysql://localhost/mydb?user=root&password=admin");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost/mydb?user=root&password=admin&useSSL=false");
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
        }
    }

    public Connection getCon() {
        return con;
    }
}
