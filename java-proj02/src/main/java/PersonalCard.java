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

    public PersonalCard(String cardHolderName, String cardNumber, Date expiryDate) {
        super(cardHolderName, cardNumber);
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    
}
