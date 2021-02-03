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

/**
 *
 * @author CHON_STRIX
 */
public class Checkout 
{
    private int customer_id;
    private String firstname;
    private String lastname;
    private int room_id;
    private String catelog_name;
    private int room_price;
    private String checkin_date;
    private String due_date;
    Connection con = null;
    PreparedStatement p = null;
    ResultSet rs = null;
    public Checkout(int customer_id){
        this.customer_id = customer_id;
    }
    public Checkout(int room_id, String catelog_name, int price)
    {
        this.room_id = room_id;
        this.catelog_name = catelog_name;
        this.room_price = price;
    }
    public Checkout(int customer_id, String firstname, String lastname, int room_id, String catelog_name, int room_price, String checkin_date, String due_date)
    {
        this.customer_id = customer_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.room_id = room_id;
        this.catelog_name = catelog_name;
        this.room_price = room_price;
        this.checkin_date = checkin_date;
        this.due_date = due_date;
    }
    
    public int getcustomer_id()
    {
        return customer_id;
    }
    public String getfirstname()
    {
        return firstname;
    }
    public String getlastname()
    {
        return lastname;
    }
    public int getroom_id()
    {
        return room_id;
    }
    public String getcatelog_name()
    {
        return catelog_name;
    }
    public int getroom_price()
    {
        return room_price;
    }
    public String getcheckin_date()
    {
        return checkin_date;
    }
    public String getdue_date()
    {
        return due_date;
    }
}
