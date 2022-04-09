/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1811257
 */
public class Card {

    private String cardHolderName;
    private String cardNumber;

    public Card(String cardHolderName, String cardNumber){
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return this.cardHolderName;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    
}
