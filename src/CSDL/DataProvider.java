/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CSDL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author DLT
 */
public class DataProvider {
//khai báo các tham số kết nối
    static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/formgame";
    static final String USER = "root";
    static final String PASS = "";
    
    public static Connection ketNoi()
    {
        //Khai báo đối tượng kết nối
        Connection conn = null;           
        try {
            //Nạp driver của mysql vào để sử dụng
            
           Class.forName(DRIVER_CLASS);         
            //Thực hiện kết nối đến db
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
        } catch (ClassNotFoundException ex) {
            System.err.println("Not found driver. Detail: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Not connection to MySQL. Detail: " + ex.getMessage());
        }        
        //Trả về kết nối
        return conn;
    }
}
