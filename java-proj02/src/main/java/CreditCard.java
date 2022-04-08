
import java.util.Date;

//import javax.naming.LimitExceededException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1811257
 */
public class CreditCard extends PaymentCard {

    private int amountOwed;
    
    public CreditCard(String securityCode, int limit, Date expiryDate, String cardHolderName, String cardNumber) {
        super(securityCode, limit, expiryDate, cardHolderName, cardNumber);
    }

    
    // public boolean checkAvailableFunds(int amount) {
    //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    // }

      
    // public boolean withdraw(int amount) {
    //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    // }
    
    public void notifyUser() {
        if (getAmountOwed() > 0.5*getLimit()){
            System.out.println("You exceeded the 50% of the limit");
        }
    }

    public void payCard(int amount) {
        this.amountOwed =- amount;
    }


    public int getAmountOwed() {
        return amountOwed;
    }


    public void setAmountOwed(int amountOwed) {
        this.amountOwed = amountOwed;
    }



}
