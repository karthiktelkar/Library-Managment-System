/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframe;
import java.sql.*;
/**
 *
 * @author HP
 */
public class DBconnection {
    static Connection con = null;
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        } catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
