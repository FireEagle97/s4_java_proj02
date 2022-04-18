package ewallet.java.proj02;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Objects;

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
        this.day = 1;
    }
    public Date(int year, int month, int day){
      this.month = month;
      this.year = year;
      this.day = day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public int getYear() {
        return this.year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return month == date.month && year == date.year && day == date.day;
    }

    @Override
    public String toString() {
        if(this.day == 0){
            return month + "/" + year;
        }
      
        return month + "/" + year + "/" + day;
        
            
    }
    
}
