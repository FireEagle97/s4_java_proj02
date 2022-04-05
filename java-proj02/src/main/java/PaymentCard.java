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
        return this.securityCode;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int newLimit) {
        this.limit = newLimit;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }
    
    public abstract boolean checkAvailableFunds(int amount);
   
    public abstract int withdraw(int amount);
    
}
