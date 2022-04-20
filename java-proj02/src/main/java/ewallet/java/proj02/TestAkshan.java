package ewallet.java.proj02;

import java.util.ArrayList;
import java.util.List;

public class TestAkshan {

    public static void main(String[] args) {


        CreditCard card3 = new CreditCard("001", 2000,04,2022, "Dany Makhoul", "123456");
        System.out.println(card3);
        CreditCardView view = new CreditCardView();
        
        LimitNotifier notifier = new LimitNotifier();
        notifier.attach(view);
        card3.setAmountOwed(1500);
        notifier.notifyUpdate(card3);

//        List<Note> notes = new ArrayList<Note>();
//        notes.add(new Note(10,10,2005,"heelo note"));
//        notes.add(new Note(2,13,2005,"delete this"));
//        notes.add(new Note(10,10,2005,"3rd"));
//        notes.add(new Note(10,10,2005,"last but not leaast"));
//
//        List<Card> cards = new ArrayList<>();
//        cards.add(new DebitCard("555", 1000, 12, 2024, "kyle", "123456789"));
//        cards.add(new DebitCard("666", 700, 12, 2024, "kyle", "42442423"));
//        cards.add(new CreditCard("022", 2000,04,20332, "Dany Makhoul", "4141"));
//        cards.add(new CreditCard("111", 2000,04,20232, "Dany Makhoul", "22314114"));
//        EWallet wallet = new EWallet(cards, notes, 69.00);
//        for (Note n : wallet.getNoteList()) {
//            System.out.println(n.getNoteId());
//            System.out.println(n);
//        }
//        wallet.deleteNote("13/2/2005-00002");
//        for (Note n : wallet.getNoteList()) {
//            System.out.println(n.getNoteId());
//            System.out.println(n);
//        }
//        for (Card c : wallet.getCardList()) {
//            System.out.println(c);
//        }
//        wallet.deleteCard("4141");
//        for (Card c : wallet.getCardList()) {
//            System.out.println(c);
//        }


    }
}
 