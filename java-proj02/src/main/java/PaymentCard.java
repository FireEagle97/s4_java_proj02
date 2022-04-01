/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1811257
 */
import java.text.SimpleDateFormat;
import java.util.Date;
public abstract class PaymentCard extends Card {
    private String securityCode;
    private int limit;
    private Date expiryDate;

    public PaymentCard(String securityCode, int limit, Date expiryDate, String cardHolderName, String cardNumber) {
        super(cardHolderName, cardNumber);
        this.securityCode = securityCode;
        this.limit = limit;
        this.expiryDate = expiryDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public int getLimit() {
        return limit;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    
   abstract boolean checkAvailableFunds(int amount);
   
   abstract int withdraw(int amount);
    
}
