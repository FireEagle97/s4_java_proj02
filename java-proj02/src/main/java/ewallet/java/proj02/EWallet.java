package ewallet.java.proj02;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**Represents a digital wallet. Can store up to 10 cards of any type, 10 notes, and stores cash.
 *
 * @author 1811257
 */
import java.util.ArrayList;
import java.util.List;

public class EWallet implements Runnable {
    private List<Card> cardList;
    private List<Note> noteList;
    private double cash;
    private LimitNotifier notifier = new LimitNotifier();

    public EWallet(List<Card> cardList, List<Note> noteList, double cash) {
        this.cardList = cardList;
        this.noteList = noteList;
        this.cash = cash;
    }

    public EWallet(EWallet otherWallet) {
        List<Card> cards = new ArrayList<>();
        List<Note> notes = new ArrayList<>();

        for (Card c : otherWallet.cardList) {
            cards.add(c);
        }
        for (Note n : otherWallet.noteList) {
            notes.add(n);
        }
        this.cardList = cards;
        this.noteList = notes;
        this.cash = otherWallet.cash;
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

    public void setCash(double amount) {
        this.cash = amount;
    }

    public void listNotes(){
        for(Note note: noteList){
            System.out.println(note);
        }
    }

    //adds a note given a new note as param, up to 10
    public void addNote(Note newNote){
        System.out.println(this.noteList.size());
        if (noteList.size() >= 10){
            throw new IllegalArgumentException("number of notes exceeds the limit");
        }
        noteList.add(newNote);
    }


    //delete a note given a note id, if the note id exists
    public void deleteNote(String noteId){
        int index = findNoteById(noteId);
        if (index != -1) {
            this.noteList.remove(index);
        }
    }

    //finds the note by its note id; if no note found, return -1
    public int findNoteById(String noteId) {
        for (int i = 0; i < this.noteList.size(); i++) {
            if (this.noteList.get(i).getNoteId().equals(noteId)) {
                return i;
            }
        }
        return -1;
    }


    //finds the card by its card number; if no card found, return -1
    public int findCardByNumber(String cardNumberInput) {
        for (int i = 0; i < this.cardList.size(); i++) {
            if (this.cardList.get(i).getCardNumber().equals(cardNumberInput)) {
                return i;
            }
        }
        return -1;
    }
    
    public void setObservers(){
        for (Card card:this.cardList){
            if(card instanceof CreditCard){
                CreditCardView view = new CreditCardView();
                notifier.attach(view); 
            } 
        }
    }

    //adds a card given a new card as param, up to 10
    public void addCard(Card newCard) {
        if (this.cardList.size() >= 10) {
            throw new IllegalArgumentException("Maximum number of cards reached!");
        }
        this.cardList.add(newCard);
    }


    //delete a card given a card number, if the card number exists
    public void deleteCard(String cardNumberInput) {
        int index = findCardByNumber(cardNumberInput);
        if (index != -1) {
            this.cardList.remove(index);
        }
    }

    //pays a card, true if successful.
    public boolean makePayment(String CardNumber, double amount) {
        if (this.cardList.isEmpty()) {
            throw new IllegalArgumentException("No cards in this wallet!");
        }
        int cardIndex = findCardByNumber(CardNumber);
        if (cardIndex == -1) {
            throw new IllegalArgumentException("Cannot find card with this number");
        }

        Card selectedCard = this.cardList.get(cardIndex);
        notifier.notifyUpdate((CreditCard)selectedCard);
        

        if (selectedCard instanceof PaymentCard) {
            boolean isSuccessful = ((PaymentCard) selectedCard).pay((int)amount);
            return isSuccessful;
        } else {
            throw new IllegalArgumentException("Not a payment card.");
        }
        

    }

    @Override
    public void run() {
        listNotes();
    }
}
