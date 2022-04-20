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

    @Override
    public void update(CreditCard card){
            if(card.getAmountOwed() > 0.5*card.getLimit()){
            System.out.println(card.notifyUser());
        }

    }

}
