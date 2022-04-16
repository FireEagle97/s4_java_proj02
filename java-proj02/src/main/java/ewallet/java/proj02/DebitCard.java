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

    public DebitCard(String securityCode, int limit, int expiryMonth, int expiryYear, String cardHolderName, String cardNumber) {
        super(securityCode, limit, expiryMonth, expiryYear, cardHolderName, cardNumber);
    }

    @Override
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
    
    public void addCash(int amount){
        this.setLimit(this.getLimit()+ amount);
    }

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
        return super.toString() + "\n" + "TYPE: DEBIT";
    }
}
