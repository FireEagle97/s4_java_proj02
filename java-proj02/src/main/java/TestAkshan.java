import java.util.ArrayList;
import java.util.List;

public class TestAkshan {

    public static void main(String[] args) {
        System.out.println("test");
        Card card1 = new PaymentCard("001", 2000, 2022,04, "Dany Makhoul", "123456");
        Card card2 = new PersonalCard("Kyle veloso", "9881234", 2025,03, "Health card");
        //Note note1 = new Note("2022")
        //Note note2
        System.out.println(card1);
        System.out.println(card2);


        System.out.println("kyle's test");
        Card card11 = new PaymentCard("001", 2000, 2022,04, "Dany Makhoul", "123456");
        Card card12 = new PersonalCard("Kyle veloso", "9881234", 2025,03, "Drivers licence");
        Card card13 = new DebitCard("622", 2000, 3, 024, "John", "123241451");
        List<Card> cards = new ArrayList<Card>();
        cards.add(card11);
        cards.add(card12);
        cards.add(card13);
        EWallet wallet = new EWallet(cards, new ArrayList<Note>(), 0.00);
        for (Card x : wallet.getCardList()) {
            System.out.println(x);
        }
        wallet.makePayment("123241451", 5);
                   
    }
}
 