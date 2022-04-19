package ewallet.java.proj02;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1811257
 */
public class DebitCard extends PaymentCard {
    private String securityCode;
    private int limit;
    private Date expiryDate;
    private String type;
    private String message;
    
    public DebitCard(String securityCode, int limit, int expiryMonth, int expiryYear, String cardHolderName, String cardNumber) {
        super(securityCode, limit, expiryMonth, expiryYear, cardHolderName, cardNumber);
        this.message = "Your balance is less or equal to 30$";
        this.type="Debit";
        
    }


   
    public boolean pay(int amount) {
        System.out.println("paying with debit...");
        if (!checkAvailableFunds(amount)) {
            return false;
        }
        return withdraw(amount);
    }


    public boolean checkAvailableFunds(int amount) {
        return (amount <= this.getLimit());
    }

    public boolean withdraw(int amount) {
        setLimit(this.getLimit() - amount); //subtract from  bank balance
        return true;
    }
    
    public int getLimit() {
        return this.limit;
    }
    
    public void setLimit(int newLimit) {
        this.limit = newLimit;
    }
    
    public String notifyUser(){
        return this.message;
    };
    

    @Override
    public boolean equals(Object otherCard) {
        if (!(otherCard instanceof DebitCard)) {
            return false;
        }
        if (!((DebitCard) otherCard).getCardNumber().equals(this.getCardNumber())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "cardHolderName: " + this.getCardHolderName() + "\n"
                +"cardNumber: " + this.getCardNumber() + "\n"
                +"securityCode: " + securityCode + "\n"
                + "limit: " + limit + "\n"
                + "expiryDate: " + expiryDate + type +":DEBIT";
    }
}
