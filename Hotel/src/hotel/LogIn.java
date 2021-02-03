/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ENTER
 */
public class LogIn {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public boolean checkID(String ID, String password){
        con = Sql.connectDB();
        String sql = "select * from staff where username = ? and password = ?";
        try
            {
                pst = con.prepareStatement(sql);
                pst.setString(1, ID);
                pst.setString(2, password);
                rs = pst.executeQuery();
                
                if(rs.next())
                {
                    System.out.println(rs.getString(1));
                    return true;
                }
                else
                {
                    System.out.println(rs.getString(1));
                    return false;
                }
            } 
            catch (SQLException e) 
            {
                return false;
            }
    }
    public String checkFname(String ID){
        con = Sql.connectDB();
        String sql = "select firstname from staff where username = ?";
        try
            {
                pst = con.prepareStatement(sql);
                pst.setString(1, ID);
                rs = pst.executeQuery();
                
                if(rs.next())
                {
                    return rs.getString(1);
                }
            } 
        catch (SQLException e) 
        {
        }
        return null;
    }
    public String checkLname(String ID){
        con = Sql.connectDB();
        String sql = "select lastname from staff where username = ?";
        try
            {
                pst = con.prepareStatement(sql);
                pst.setString(1, ID);
                rs = pst.executeQuery();
                
                if(rs.next())
                {
                    return rs.getString(1);
                }
            } 
        catch (SQLException e) 
        {
        }
        return null;
    }
    public String checkEmail(String ID){
        con = Sql.connectDB();
        String sql = "select email from staff where username = ?";
        try
            {
                pst = con.prepareStatement(sql);
                pst.setString(1, ID);
                rs = pst.executeQuery();
                
                if(rs.next())
                {
                    return rs.getString(1);
                }
            } 
        catch (SQLException e) 
        {
        }
        return null;
    }
    public String checkPhone(String ID){
        con = Sql.connectDB();
        String sql = "select phone from staff where username = ?";
        try
            {
                pst = con.prepareStatement(sql);
                pst.setString(1, ID);
                rs = pst.executeQuery();
                
                if(rs.next())
                {
                    return rs.getString(1);
                }
            } 
        catch (SQLException e) 
        {
        }
        return null;
    }
    public String checkRole(String ID){
        con = Sql.connectDB();
        String sql = "select position from staff where username = ?";
        try
            {
                pst = con.prepareStatement(sql);
                pst.setString(1, ID);
                rs = pst.executeQuery();
                
                if(rs.next())
                {
                    return rs.getString("position");
                }
            } 
        catch (SQLException e) 
        {
        }
        return null;
    }
}
