package ewallet.java.proj02;
//import javax.naming.LimitExceededException;

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

    private int amountOwed;
    private String message;
    private String type;
    
    public CreditCard(String securityCode, int limit, int expiryMonth, int expiryYear, String cardHolderName, String cardNumber) {
        super(securityCode,limit,expiryMonth,expiryYear,cardHolderName,cardNumber);
        this.type = "Credit";
        this.message = "You exceeded the 50% of the limit";
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

    @Override
    public String toString() {
        return super.toString() + "\n" + "TYPE: CREDIT";
    }
}
