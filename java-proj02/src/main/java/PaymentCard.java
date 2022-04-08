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
//import java.util.Date;
public class PaymentCard extends Card {
    private String securityCode;
    private int limit;
    private ExpiryDate expiryDate;

    public PaymentCard(String securityCode, int limit, int expiryMonth, int expiryYear, String cardHolderName, String cardNumber) {
        super(cardHolderName, cardNumber);
        this.securityCode = securityCode;
        this.limit = limit;
        this.expiryDate = new ExpiryDate(expiryYear,expiryMonth);
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

    public ExpiryDate getExpiryDate() {
        return this.expiryDate;
    }
    
    @Override
    public boolean equals(Object card){
        if (!(card instanceof PaymentCard)){
            System.out.println("not equal type");
            return false;
        }
        return true;
        
    }

}
