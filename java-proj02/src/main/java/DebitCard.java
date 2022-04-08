
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1811257
 */
public class DebitCard extends PaymentCard {

    public DebitCard(String securityCode, int limit, int expiryMonth, int expiryYear, String cardHolderName, String cardNumber) {
        super(securityCode, limit, expiryMonth, expiryYear, cardHolderName, cardNumber);
    }

    
    public boolean checkAvailableFunds(int amount) {
        return (amount <= this.getLimit());
    }

    public boolean withdraw(int amount) {
        if (this.getLimit() - amount < 0) {
            return false;
        }
        setLimit(this.getLimit() - amount); //subtract from  bank balance, return the amount.
        return true;
    }
    
    public void addCash(int amount){
        this.setLimit(this.getLimit()+ amount);
    }
    
}
