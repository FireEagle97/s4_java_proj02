package ewallet.java.proj02;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**Represents a personal card which is a Card. Cannot use for payment and has a short description what this card is for.
 *
 * @author 1811257
 */
public class PersonalCard extends Card {
    private Date expiryDate;
    private String description; // ex: Driver's license, business card, medicare

    public PersonalCard(String cardHolderName, String cardNumber,int expiryYear, int expiryMonth, String description) {
        super(cardHolderName, cardNumber);
        this.expiryDate = new Date(expiryYear, expiryMonth);
        this.description = description;
    }

    public PersonalCard(String cardHolderName, String cardNumber, String description) {
        super(cardHolderName, cardNumber);
        this.expiryDate = null;
        this.description = description;
    }


    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(Object card){
        if (!(card instanceof PersonalCard)){
            System.out.println("not equal type");
            return false;
        }
        return true;
        
    }
    @Override
    public String toString() {
        return "cardHolderName: " + this.getCardHolderName() + "\n"
                + "cardNumber: " + this.getCardNumber()+ "\n"
                + "expiryDate: " + (expiryDate != null ? expiryDate : "N/A") + "\n"
                +"description: " + description;
    }

}
