package ewallet.java.proj02;
//import javax.naming.LimitExceededException;

import java.util.ArrayList;
import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**Represent a credit card that is a Card and a Payment card. when the card is used the amount owed is increased and cannot go above the credit limit
 *
 * @author 1811257
 */
public class CreditCard extends PaymentCard implements Subject{

    private int amountOwed;
    private String message;
    private List<Observer> observers;
    
    public CreditCard(String securityCode, int limit, int expiryMonth, int expiryYear, String cardHolderName, String cardNumber) {

        super(securityCode, limit,expiryMonth, expiryYear, cardHolderName, cardNumber);
        final int MIN_LIMIT = 100;
        final int MAX_LIMIT = 5000;
        if (limit < MIN_LIMIT || limit > MAX_LIMIT) {
            throw new IllegalArgumentException("Limit must be within " + MIN_LIMIT + " to " + MAX_LIMIT);
        }

        this.message = "You exceeded the 50% of the limit";
        this.observers = new ArrayList<>();
    }

    
    public String notifyUser() {
            return this.message;
    }

    @Override
    public boolean pay(int amount) {
        System.out.println("paying with credit...");
        if (this.amountOwed + amount > this.getLimit()) {
            return false;
        }
        this.amountOwed += amount;
        return true;
    }


    public int getAmountOwed() {
        return amountOwed;
    }
    
    public void setAmountOwed(int amount){
        this.amountOwed=amount;
    }
    
     @Override
    public void attach(Observer o){
        observers.add(o);
        
    }
    
    @Override
    public void detach(Observer o){
        observers.remove(o);
    }
    
    @Override
    public void notifyUpdate(CreditCard card){
        for(Observer o: observers) {
            o.update(card);
        }
    }
    
    @Override
    public String toString() {
        return super.toString() + "\n"
                + "TYPE: CREDIT" + "\n"
                + "amountOwed:" + getAmountOwed();
                
    }
}
