/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turbotpms;

import java.sql.*;

/**
 *
 * @author delan
 */
public class DatabaseManager 
{
    String host = "jdbc:mysql://localhost/tpms";
    String user = "root";
    String pass = "password";
    Connection conn;
    
    public DatabaseManager()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host, user, pass);
        }
        catch(Exception e)
        {
            System.out.println("problem");
        }
    }
    public void addTransactionToRecord(String type,String item, String qty, String amt)
    {
        try
        {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into transactions\n (type, item, quantity, amount)\n values \n ('"+type+"','"+item+"','"+qty+"','"+amt+"');");
        }
        catch(SQLException e)
        {
            System.out.println("insert problem");
            System.out.println(e.getMessage());
        }
    }
    
}
