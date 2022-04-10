
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
    
    public CreditCard(String securityCode, int limit, int expiryMonth, int expiryYear, String cardHolderName, String cardNumber) {
        super(securityCode, limit,expiryMonth, expiryYear, cardHolderName, cardNumber);

    }

    public void notifyUser() {
        if (getAmountOwed() > 0.5*getLimit()){
            System.out.println("You exceeded the 50% of the limit");
        }
    }

    public void payBalance(int amount) {
        this.amountOwed =- amount;
    }



    public int getAmountOwed() {
        return amountOwed;
    }


    public void setAmountOwed(int amountOwed) {
        this.amountOwed = amountOwed;
    }



}
