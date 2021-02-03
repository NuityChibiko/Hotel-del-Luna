/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;
import java.sql.*;


public class Sql
{
    public static Connection connectDB()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL;
            dbURL = "jdbc:mysql://35.240.238.117:3306/hotel";
            String username = "root";
            String password = "1234";
            Connection connect = DriverManager.getConnection(dbURL, username, password);
            return connect;
        } 
        catch (Exception e)
        { 
            System.out.println("Error loading driver: " + e.getMessage());
        }
        return null;
    }
    
    public static void showdata(String sql)
    {
        try 
        {
            Connection c = connectDB(); //check is work
            ResultSet rs;
            rs = c.createStatement().executeQuery(sql);
            while (rs.next())
            {
                System.out.format("username: %-10s password: %-10s  \n",rs.getString(1),rs.getString(2));
            }
            rs.close();
            c.close();
        }
        
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static void insert()
    {
        String sql = "insert into account value(?,?,?,?,?,?)";
        try
        {
            Connection con = connectDB();
//            Statement stm = con.createStatement();
//            stm.executeUpdate(sql);
//            System.out.println("บันทึกข้อมูลเรียบร้อยแล้ว");
            
            PreparedStatement p = null;
            p = con.prepareStatement(sql);
            p.setString(1, sql);
            p.setString(2, sql);
            p.setString(3, sql);
            p.execute();
            p.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
