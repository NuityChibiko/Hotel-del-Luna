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
import javax.swing.JOptionPane;

/**
 *
 * @author ENTER
 */
public class Promotion 
{
    private String promotion_code;
    private int discount;
    private String detail_pro;
    public Promotion()
    {
        
    }
    public Promotion(String promotion_code, int discount, String detail_pro)
    {
        this.promotion_code = promotion_code;
        this.discount = discount;
        this.detail_pro = detail_pro;
    }

    
    public String getpromotion_code()
    {
        return promotion_code;
    }
    public int getdiscount()
    {
        return discount;
    }
    public String getdetailpro()
    {
        return detail_pro;
    }
    Connection con = null;
    PreparedStatement p = null;
    ResultSet rs = null;
    public String generateCode(String discount, String detail)
    {
        int disrate = Integer.parseInt(discount);
        if(disrate < 0 || disrate >= 100)
        {
            JOptionPane.showMessageDialog(null, "Generate Not Complete \nPlease input again!");
        }
        else
        {
            String code = "";
            for(int i=0;i<10;i++)
            {
                int j = RNG.random(0, 2);
                if(j == 0){
                    int k = RNG.random(48, 57);
                    char r = (char) k;
                    code += r;
                } 
                else if(j == 1){
                    int k = RNG.random(65, 90);
                    char r = (char) k;
                    code += r;
                }
                else if(j == 2){
                    int k = RNG.random(97, 122);
                    char r = (char) k;
                    code += r;
                } 
            }
            String sql = "insert into promotion values(?, ?, ?)";
            try
            {
                Connection con = connectDB();
                p = con.prepareStatement(sql);
                p.setString(1, code);
                p.setString(2, discount);
                p.setString(3, detail);
                p.execute();
                JOptionPane.showMessageDialog(null, "Generate Complete");
                return code;
            }
            catch(Exception e)
            {
                return null;
            }
        
        }
        return null;
    }
    public void deleteCode(String code){
        String sql = "delete from promotion where promotion_code = ?";
        try
        {
            Connection con = connectDB();
            p = con.prepareStatement(sql);
            p.setString(1, code);
            p.execute();
        }
        catch(Exception e)
        {
        }
    }
}
