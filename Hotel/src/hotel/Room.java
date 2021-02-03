/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import static hotel.Sql.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ENTER
 */
public class Room 
{
    private int room_id;
    private int catelog_id;
    PreparedStatement p = null;
    
    public Room(int room_id){
        this.room_id = room_id;
    }
    public Room(int room_id , int catelog_id)
    {   
        this.room_id = room_id;
        this.catelog_id = catelog_id;
    }
    public void addRoom(){
        try
            {
                String catStr = Integer.toString(catelog_id);
                String roomStr = Integer.toString(room_id);
                String st = "ว่าง";
                Connection con = connectDB();
                String ins = "insert into room values (?, ?, ?)";
                p = con.prepareStatement(ins);
                p.setString(1, roomStr);
                p.setString(2, catStr);
                p.setString(3, st);
                p.execute();
                JOptionPane.showMessageDialog(null, "Add Catelog Complete");
            }
        catch(Exception e){
            
        }
    }
    public void editRoom(){
        try
            {
                String catStr = Integer.toString(catelog_id);
                String roomStr = Integer.toString(room_id);
                Connection con = connectDB();
                String ins = "update room set catelog_id = ? where room_id = ?";
                p = con.prepareStatement(ins);
                p.setString(1, catStr);
                p.setString(2, roomStr);
                System.out.println(p);
                p.execute();
                JOptionPane.showMessageDialog(null, "Edit Room Complete");
            }
        catch(Exception e){
            
        }
    }
    public void deleteRoom(){
        try
            {
                String roomStr = Integer.toString(room_id);
                Connection con = connectDB();
                String ins = "delete from room where room_id = ?";
                p = con.prepareStatement(ins);
                p.setString(1, roomStr);
                p.execute();
                JOptionPane.showMessageDialog(null, "Delete Room Complete");
            }
        catch(Exception e){
            
        }
    }
    public int getroom_id()
    {
        return room_id;
    }
    public int getcatelog_id()
    {
        return catelog_id;
    }

}
