package ewallet.java.proj02;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Objects;

/**Represent a Card containing a Card Number and a card holder name
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
    
    @Override
    public String toString(){
        return "the card holder is " + this.cardHolderName + " and the card number is " + this.cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardHolderName, card.cardHolderName) && Objects.equals(cardNumber, card.cardNumber);
    }

}
