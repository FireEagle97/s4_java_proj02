package ewallet.java.proj02;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dany's Computer
 */
public class CreditCardView implements Observer {

    public static String update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String update(CreditCard card){
            //card = (CreditCard) card;
            if(card.getAmountOwed() > card.getLimit()){
                return card.notifyUser();
            }
            else{
                return "testing observer";
            }
    }
}
