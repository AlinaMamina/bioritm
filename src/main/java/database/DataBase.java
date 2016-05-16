package database;

import java.io.File;
import java.sql.*;


public class DataBase {
    private Connection conn;
    private Statement stmt = null;
    private ResultSet rs = null;
    private final String file_name;


    public DataBase(String bd_file) {

        file_name = bd_file;
    }

    public void Connect() throws SQLException, ClassNotFoundException {

        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection(
                "jdbc:h2:file://" + file_name,
                "sa", "");

        stmt = conn.createStatement();
    }

    public void Disconnect() throws SQLException {
        stmt.close();
        conn.close();
    }

    public boolean HasLogin(User u) throws SQLException {

        rs = stmt.executeQuery("select * from PersonData \n" +
                "WHERE pd_login = '" + u.login + "'" +
                " AND pd_password = '" + u.password + "'");
//        rs.next();
        return rs.next();
    }

    public boolean HasUser(User u) throws SQLException {

        rs = stmt.executeQuery("select * from PersonData \n" +
                "WHERE pd_login = '" + u.login + "'");
//        rs.next();
        return rs.next();
    }

    public void Change_data_last_call(User u, String date) throws SQLException {

        stmt.executeUpdate("UPDATE PersonData SET pd_d_last_call = '" + date + "'\n" +
                "WHERE pd_login = '" + u.login + "'");

    }

    public void Add_user(User u) throws SQLException {

        stmt.executeUpdate("BEGIN TRANSACTION");

        stmt.executeUpdate("INSERT INTO PersonData (pd_login, pd_password, pd_d_last_call, pd_d_birthday)"
                + " VALUES ('" + u.login + "','" + u.password + "','" + u.date + "','" + u.birthday + "')");

        stmt.executeUpdate("COMMIT");
    }

    public void Get_user_data(User u) throws SQLException {

        rs = stmt.executeQuery("select pd_d_last_call, pd_d_birthday from PersonData " +
                "WHERE pd_login = '" + u.login + "'" +
                " AND pd_password = '" + u.password + "'");
        rs.next();

        u.birthday = rs.getString("pd_d_birthday");
        u.date = rs.getString("pd_d_last_call");
    }

}
