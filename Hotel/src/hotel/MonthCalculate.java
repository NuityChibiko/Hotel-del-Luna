/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

/**
 *
 * @author ENTER
 */
public class MonthCalculate {
    int[] maxday = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    public int d;
    public int m;
    public int y;
    
    public String Calculate(String date, int day){
        String[] arrDate = date.split("-", 3); 
        y = Integer.parseInt(arrDate[0]);
        m = Integer.parseInt(arrDate[1]);
        d = Integer.parseInt(arrDate[2]);
        if(y%4 == 0 && y%100 != 0){
            maxday[2] = 29;
        }
        else if(y%4 == 0 && y%400 == 0){
            maxday[2] = 29;
        }
        return null;
    }
}
