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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author ENTER
 */
public class Admin {
    Connection con = null;
    PreparedStatement p = null;
    ResultSet rs = null;
    private String First_reserve;
    private String Last_reserve;
    private double day;
    public ResultSet searchName(String word)
    {
        String sql = "select * from customer where firstname like '%"+word+"%' order by firstname";
        try
        {
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            return rs;
        }
        catch (SQLException e)
        {
            System.out.println("in class admin "+e);
            return null;
        }
    }
    public ResultSet searchRoom(String word){
        String sql = "select c.firstname, c.lastname, r.room_id from customer as c\n" +
                        "inner join reservation re on re.customer_id = c.customer_id\n" +
                        "inner join room as r on re.room_id = r.room_id\n" +
                        "where c.firstname like '%"+word+"%' order by r.room_id";
        try
        {
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            return rs;
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return null;
        }
    }
    public void registCustomer(String ID, String fname, String lname, String address, String phone, String gender, String date, String country){
        String sql = "insert into customer (card_id, firstname, lastname, gender, birthday, country, address, phone) value(?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            p.setString(1, ID);
            p.setString(2, fname);
            p.setString(3, lname);
            p.setString(4, gender);
            p.setString(5, date);
            p.setString(6, country);
            p.setString(7, address);
            p.setString(8, phone);
           
            p.execute();
            JOptionPane.showMessageDialog(null, "Register customer Complete");
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    public ResultSet customerFind(String ID){
        String sql = "select firstname, lastname from customer where customer_id = ?";
        try
        {
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            p.setString(1, ID);
            rs = p.executeQuery();
            if(rs.next())
            {
                First_reserve = (rs.getString(1));
                Last_reserve = (rs.getString(2));
                return rs;
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
            return null;
        }
    }
    public String lastCustomerFind(){
        String sql = "select max(customer_id) from customer";
        try
        {
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            if(rs.next())
            {
                return rs.getString(1);
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
            return null;
        }
    }
    public String lastInvoiceFind(){
        String sql = "select max(invoice_id) from invoice";
        try
        {
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            if(rs.next())
            {
                return rs.getString(1);
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
            return null;
        }
    }
    public String searchCatelog(String room_id){
        String sql = "select catelog_id from room where room_id = ?";
        try
        {
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            p.setString(1, room_id);
            rs = p.executeQuery();
            if(rs.next())
            {
                return rs.getString(1);
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
            return null;
        }
    }
    public String searchCheckin_date(String room_id){
        String sql = "select checkin_date from reservation where room_id = ?";
        try
        {
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            p.setString(1, room_id);
            rs = p.executeQuery();
            if(rs.next())
            {
                return rs.getString(1);
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
            return null;
        }
    }
    public String searchReserveRoom(String custID, int j){
        String sql = "select room_id from reservation where customer_id = ?";
        try
        {
            String room = null;
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            p.setString(1, custID);
            rs = p.executeQuery();
            for(int i=0;rs.next()&&i<=j;i++)
            {
                room = rs.getString(1);
            }
            return room;
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    public String getFirst_reserve(){
        return First_reserve;
    }
    public String getLast_reserve(){
        return Last_reserve;
    }
    public ResultSet reserve(String cust_ID, String room_ID, String day){
        String sql = "insert into reservation values(?, ?, ?, ?, ?, ?)";
        String sql1 = "update room set status = 'ไม่ว่าง' where room_id = ?";
        String sql2 = "select * from room where room_id = ?";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now();  
        String checkin_date = dtf.format(now);
        int dayInt = Integer.parseInt(day);

        MonthCalculate mc = new Calendar();
        String due_date = mc.Calculate(checkin_date, dayInt); 
        try
        {
            Connection con = connectDB();
            
            p = con.prepareStatement(sql);
            p.setString(1, cust_ID);
            p.setString(2, room_ID);
            p.setString(3, day);
            p.setString(4, checkin_date);
            p.setString(5, due_date);
            p.setString(6, "จองแล้ว");
            p.execute();
            
            PreparedStatement p1 = null;
            p1 = con.prepareStatement(sql1); 
            p1.setString(1, room_ID);
            p1.execute();
            
            PreparedStatement p2 = null;
            p2 = con.prepareStatement(sql2);
            p2.setString(1, room_ID);
            rs = p2.executeQuery();
            if(rs.next())
            {
                return rs;
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    public double checkPrice(String ID){
        String sql = "call showlistcheckout" + "(" +ID+ ")";
        double price = 0;
        double price_b = 0;
        double day = 0;
        try
        {
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next())
            {
                day = Integer.parseInt(rs.getString("re.oconpancy_no"));
                price_b = Integer.parseInt(rs.getString("c.room_price"));
                price += price_b*day;
            }
            return price;
        }
        catch(Exception e)
        {
            return -1;
        }
    }
    public double checkPromo(String code){
        String sql = "select discount_rate from promotion where promotion_code = ?";
        try
        {
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            p.setString(1, code);
            rs = p.executeQuery();
            if(rs.next())
            {
                double dr = Double.valueOf(rs.getString("discount_rate"));
                if(dr >= 100)
                    return 100;
                else if(dr <=0)
                    return 0;
                else return dr;
            }
            else return 0;
        }
        catch(Exception e)
        {
            return 0;
        }
    }
    public void deleteReservation(String customer_id){
        String sql = "delete from reservation where customer_id = ?";
        try
        {
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            p.setString(1, customer_id);
            p.execute();
        }
        catch(Exception e){
            
        }
    }
    public ResultSet searchHistory(String word)
    {
        String sql = "select * from customer as c "
                + "right outer join invoice as v on c.customer_id = v.customer_id "
                + "left outer join history as h on v.invoice_id = h.invoice_id "
                + "where c.firstname like '%" + word +"%'";
        try
        {
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            return rs;
        }
        catch (SQLException e)
        {
            return null;
        }
    }
}
