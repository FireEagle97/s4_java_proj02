
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
public class CreditCard extends PaymentCard {

    public CreditCard(String securityCode, int limit, Date expiryDate, String cardHolderName, String cardNumber) {
        super(securityCode, limit, expiryDate, cardHolderName, cardNumber);
    }

    @Override
    boolean checkAvailableFunds(int amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int withdraw(int amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //notify(int amount)
}
