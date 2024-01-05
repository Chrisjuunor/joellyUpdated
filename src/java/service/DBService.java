package service;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.User;

public class DBService {
    public DBService(){}
    
    public int createAccount(String name, String phone, String password, String role){
        try(Connection conn = getDBConnection()){
            String query = "INSERT INTO users(name,phone,password,role) "
                    + "VALUES(?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, password);
            stmt.setString(4, role);
            stmt.execute();
            
            query = "SELECT last_insert_id() AS id";
            Statement stmt1 = conn.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            if(rs.next())return rs.getInt("id");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public User performLogin(String phone, String password) {
        User user = new User();
        try(Connection conn = getDBConnection()){
            String sql = "SELECT * FROM users WHERE phone = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,phone);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                user = new User(rs.getInt("id"), rs.getString("name"),
                phone,rs.getString("role"));
                return user;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public void template(String phone, String pin) {
        try(Connection conn = getDBConnection()){
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    Connection getDBConnection() throws SQLException, ClassNotFoundException{
        String user = "root";
        String password = "password";
        String url = "jdbc:mysql://localhost:3306/okoli_tech?useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}
