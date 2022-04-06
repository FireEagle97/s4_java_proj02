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
public class PersonalCard extends Card {
    private Date expiryDate;
    private String description; // ex: Driver's license, business card, medicare

    public PersonalCard(String cardHolderName, String cardNumber, Date expiryDate, String description) {
        super(cardHolderName, cardNumber);
        this.expiryDate = expiryDate;
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
}
