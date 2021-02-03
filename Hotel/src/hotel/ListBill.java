/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

/**
 *
 * @author CHON_STRIX
 */
public class ListBill 
{
    private int room_id;
    private String catelog;
    private int price;
    private int oconpancy_no;
    public ListBill(int id, String catlog, int price, int oconpancy_no)
    {
        this.room_id = id;
        this.catelog = catlog;
        this.price = price;
        this.oconpancy_no = oconpancy_no;
        System.out.println(room_id);
        System.out.println(catelog);
        System.out.println(price);
    }

    public int getRoom_id() {
        return room_id;
    }

    public String getCatelog() {
        return catelog;
    }

    public int getPrice() {
        return price;
    }

    Object getoconpancy_no() {
        return oconpancy_no;
    }
    
}
