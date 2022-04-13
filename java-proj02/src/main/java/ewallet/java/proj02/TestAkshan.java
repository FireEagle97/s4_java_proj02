package ewallet.java.proj02;

public class TestAkshan {

    public static void main(String[] args) {

        //ewallet.java.proj02.Card card1 = new ewallet.java.proj02.PaymentCard("001", 2000, 2022,04, "Dany Makhoul", "123456");
        //ewallet.java.proj02.Card card2 = new ewallet.java.proj02.PersonalCard("Kyle veloso", "9881234", 2025,03, "Health card");
        CreditCard card3 = new CreditCard("001", 2000,04,2022, "Dany Makhoul", "123456");
        System.out.println(card3);
        CreditCardView view = new CreditCardView();
        
        LimitNotifier notifier = new LimitNotifier();
        notifier.attach(view);
        //card3.setAmountOwed(1500);
        notifier.notifyUpdate(card3);

//        ewallet.java.proj02.Card card1 = new ewallet.java.proj02.DebitCard("001", 2000, 2022,04, "Dany Makhoul", "123456");
//        //ewallet.java.proj02.Card card3 = new ewallet.java.proj02.CreditCard("002", 3000,2022,07,"Kyle veloso","245456");
//        ewallet.java.proj02.Card card2 = new ewallet.java.proj02.PersonalCard("Kyle veloso", "9881234", 2025,03, "Healthcare card");
//        ewallet.java.proj02.Note note1 = new ewallet.java.proj02.Note(29,04,2022,"you have a dentist appointment");
//        ewallet.java.proj02.Note note2 = new ewallet.java.proj02.Note(15,04,2022,"don't forget to go to Costco");
//        System.out.println(card3);
//        System.out.println(card3);

                   
    }
}
 