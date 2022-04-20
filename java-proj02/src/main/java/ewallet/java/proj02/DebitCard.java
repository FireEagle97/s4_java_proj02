package ewallet.java.proj02;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**Represents a Debit card which is a Card and a PaymentCard. Debit cards have a limit which is = to the money available in the bank
 * This limit is deduced as payments are done and cannot go below 0.
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
