/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1811257
 */
import java.util.List;
public class EWallet {
    private List<Card> cardList;
    private List<Note> noteList;
    private double cash;

    public EWallet(List<Card> CardList, List<Note> NoteList, double cash) {
        this.cardList = CardList;
        this.noteList = NoteList;
        this.cash = cash;
    }

    public List<Card> getCardList() {
        return this.cardList;
    }

    public List<Note> getNoteList() {
        return this.noteList;
    }

    public double getCash() {
        return this.cash;
    }


    public void addCard(Card newCard) {
        if (this.cardList.size() >= 10) {
            throw new IllegalArgumentException("Maximum number of cards reached!");
        }
        this.cardList.add(newCard);
    }

    public void deleteCard(int index) {
        this.cardList.remove(index);
    }

    public void makePayment(int index, int amount) {
        //use polymorphism instead; TODO change later
        if (this.cardList.get(index) instanceof CreditCard) {
            ((CreditCard) this.cardList.get(index)).payCard(index);
        } else {
            throw  new IllegalArgumentException("not a credit card");
        }
    }
    /*
    The operations of the e-wallet are:
1)	Add a card. Kyle
2)	Delete a card.  Kyle
3)	Pay.  Kyle
4)	Browse through the notes. dany
5)	Add a note. dany
6)	Delete a note. dany
7)	Save e-wallet to DB.
8)	Load e-wallet from DB.
9)	Display how much cash we have Kyle
10)	Add cash dany


     */
}
