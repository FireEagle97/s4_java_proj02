
import java.util.ArrayList;
import java.util.List;

public class TestAkshan {

    public static void main(String[] args) {

        //Card card1 = new PaymentCard("001", 2000, 2022,04, "Dany Makhoul", "123456");
        //Card card2 = new PersonalCard("Kyle veloso", "9881234", 2025,03, "Health card");
        CreditCard card3 = new CreditCard("001", 2000,04,2022, "Dany Makhoul", "123456");
        System.out.println(card3);
        CreditCardView view = new CreditCardView();
        
        LimitNotifier notifier = new LimitNotifier();
        notifier.attach(view);
        //card3.setAmountOwed(1500);
        notifier.notifyUpdate(card3);

//        Card card1 = new DebitCard("001", 2000, 2022,04, "Dany Makhoul", "123456");
//        //Card card3 = new CreditCard("002", 3000,2022,07,"Kyle veloso","245456");
//        Card card2 = new PersonalCard("Kyle veloso", "9881234", 2025,03, "Healthcare card");
//        Note note1 = new Note(29,04,2022,"you have a dentist appointment");
//        Note note2 = new Note(15,04,2022,"don't forget to go to Costco");
//        System.out.println(card3);
//        System.out.println(card3);

                   
    }
}
 