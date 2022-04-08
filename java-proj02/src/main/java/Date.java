/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1811257
 */
public class Date {
    private int month;
    private int year;
    private int day;
    public Date(int year, int month){
        this.month = month;
        this.year = year;
    }
    public Date(int year, int month, int day){
      this.month = month;
      this.year = year;
      this.day = day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    @Override
    public String toString() {
        if(this.day == 0){
            return "month=" + month + ", year=" + year;
        }
      
        return "month=" + month + ", year=" + year + "day=" + day;
        
            
    }
    
}
