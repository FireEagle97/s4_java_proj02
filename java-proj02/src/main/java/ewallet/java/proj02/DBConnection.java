/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ewallet.java.proj02;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author 1811257
 */
public class DBConnection {
    String url = "jdbc:mysql://localhost:3306/";
    String username = "nasr";
    String password = "Python_420";
    
    public Connection getConnection(){
        Connection connectDB;
        try {
            connectDB = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return connectDB;
    }
    
    
}
