/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import static hotel.Sql.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CHON_STRIX
 */
public class Catelog 
{
    private int catelogid;
    private String name;
    private int price;
    private int maxguest;
    private String detail;
    private PreparedStatement p = null;
    private PreparedStatement p2 = null;
    
    public Catelog(int catelogid){
        this.catelogid = catelogid;
    }
    public Catelog(int catelogid , String name , int price , int maxguest , String detail)
    {
        this.catelogid = catelogid;
        this.name = name;
        this.price = price;
        this.maxguest = maxguest;
        this.detail = detail;
    }
    public void addCatelog(){
        String sql_add = "insert into roomcatelog values(?,?,?,?,?)";
        try
            {
                Connection con = connectDB();

                p = con.prepareStatement(sql_add);
                p.setString(1, Integer.toString(catelogid));
                p.setString(2, name);
                p.setString(3, Integer.toString(price));
                p.setString(4, Integer.toString(maxguest));
                p.setString(5, detail);
                p.execute();
                JOptionPane.showMessageDialog(null, "Add Catelog Complete");
            }
            catch ( SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Add Catelog not complete");
            }
    }
    public void editCatelog(){
        String sql_add = "update roomcatelog set catelog_name = ?, room_price = ?, max_guest = ?, detail = ? where catelog_id = ?";
        try
            {
                Connection con = connectDB();
                p = con.prepareStatement(sql_add);
                p.setString(1, name);
                p.setString(2, Integer.toString(price));
                p.setString(3, Integer.toString(maxguest));
                p.setString(4, detail);
                p.setString(5, Integer.toString(catelogid));
                p.execute();
                JOptionPane.showMessageDialog(null, "Edit Catelog Complete");
            }
            catch ( SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Edit Catelog not complete");
            }
    }
    public void deleteCatelog(){
        String sql = "delete from roomcatelog where catelog_id = ?";
        String sql_check = "select room_id from room where catelog_id = ?";
        ResultSet rs = null;
        try
            {
                Connection con = connectDB();
                
                p2 = con.prepareStatement(sql_check);
                p2.setString(1, Integer.toString(catelogid));
                p2.executeQuery();
                rs = p2.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Catelog still have room left");
                    return;
                }
                
                p = con.prepareStatement(sql);
                p.setString(1, Integer.toString(catelogid));
                p.execute();
                JOptionPane.showMessageDialog(null, "Delete Catelog Complete");
            }
            catch ( SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Delete Catelog not complete");
            }
    }
    public int getcatelog_id()
    {
        return catelogid;
    }
    public String getname()
    {
        return name;
    }
    public int getprice()
    {
        return price;
    }
    public int getmaxguest()
    {
        return maxguest;
    }
    public String getdetail()
    {
        return detail;
    }
}
