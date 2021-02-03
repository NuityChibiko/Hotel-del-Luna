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
import javax.swing.JOptionPane;

/**
 *
 * @author ENTER
 */
public class Manager extends Admin{
    Connection con = null;
    PreparedStatement p = null;
    ResultSet rs = null;
    public void addRoom(int roomid, int catelogid){
        Room r = new Room(roomid, catelogid);
        r.addRoom();
    }
    public void editRoom(int roomid, int catelogid){
        Room r = new Room(roomid, catelogid);
        r.editRoom();
    }
    public void deleteRoom(int roomid){
        Room r = new Room(roomid);
        r.deleteRoom();
    }
    public void addCat(int catelogid, String name, int price, int maxguest, String detail){
        Catelog cat = new Catelog(catelogid, name, price, maxguest, detail);
        cat.addCatelog();  
    }
    public void editCat(int catelogid, String name, int price, int maxguest, String detail){
        Catelog cat = new Catelog(catelogid, name, price, maxguest, detail);
        cat.editCatelog();  
    }
    public void deleteCat(int catelogid){
        Catelog cat = new Catelog(catelogid);
        cat.deleteCatelog();  
    }
    public void editCat(){
        
    }
    public void deleteCat(){
        
    }
    public void manageID(String fname, String lname, String username, String password, String email, String phone, String role){
        con = Sql.connectDB();
        String sql = "insert into staff(firstname, lastname, username, password, email, phone, position) values(?, ?, ?, ?, ?, ?, ?)";
        try
        {
            p = con.prepareStatement(sql);
            p.setString(1, fname);
            p.setString(2, lname);
            p.setString(3, username);
            p.setString(4, password);
            p.setString(5, email);
            p.setString(6, phone);
            p.setString(7, role);
            p.execute();
            JOptionPane.showMessageDialog(null, "Add User Complete");
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
}
