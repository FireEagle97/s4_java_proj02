
import java.util.ArrayList;
import java.util.List;

public class TestAkshan {

    public static void main(String[] args) {
        Card card1 = new DebitCard("001", 2000, 2022,04, "Dany Makhoul", "123456");
        Card card3 = new CreditCard("002", 3000,2022,07,"Kyle veloso","245456");
        Card card2 = new PersonalCard("Kyle veloso", "9881234", 2025,03, "Healthcare card");
        Note note1 = new Note(29,04,2022,"you have a dentist appointment");
        Note note2 = new Note(15,04,2022,"don't forget to go to Costco");
        System.out.println(card3);
        System.out.println(card3);
//        List<Card> cardsList = new ArrayList<>();
//        List<Note> notesList = new ArrayList<>();
//        EWallet wal1 = new EWallet(cardsList, notesList, 200);
//        System.out.println("cash"+wal1.getCash());
//        wal1.addNote(note1);
//        wal1.addNote(note2);
//        wal1.listNotes();
//        wal1.deleteNote(note1);
//        System.out.println("after deletion");
//        wal1.listNotes();
//        wal1.addCard(card1);
//        wal1.addCard(card2);
//         List<Card> cardsafter = wal1.getCardList();
//        for (Card aCard: cardsafter){
//            System.out.println(aCard);
//            
//        }
                
        
        
                   
    }
}
 