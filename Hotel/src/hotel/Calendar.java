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
public class Calendar extends MonthCalculate{
    
    public String Calculate(String date, int day){
        super.Calculate(date, day);
        d = d + day;
        for(;;){
            if(d > maxday[m]){
                d -= maxday[m];
                m +=1;
                if(m > 12){
                    y += 1;
                    m -= 12;
                }
            }
            if(d <= maxday[m]) break;
        }
        String res = Integer.toString(y) + "-" + Integer.toString(m) + "-" + Integer.toString(d);
        return res;
    }
}
