
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

    public DebitCard(String securityCode, int limit, Date expiryDate, String cardHolderName, String cardNumber) {
        super(securityCode, limit, expiryDate, cardHolderName, cardNumber);
    }

    @Override
    public boolean checkAvailableFunds(int amount) {
        return (amount <= this.getLimit());
    }

    @Override
    public boolean withdraw(int amount) {
        if (this.getLimit() - amount < 0) {
            return false;
        }
        setLimit(this.getLimit() - amount); //subtract from  bank balance, return the amount.
        return true;
    }
    
}
