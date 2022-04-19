package ewallet.java.proj02;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1811257
 */
import java.util.ArrayList;
import java.util.List;

public class EWallet {
    private List<Card> cardList;
    private List<Note> noteList;
    private double cash;

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
    
    public void addNote(Note newNote){
        if (noteList.size() > 10){
            throw new IllegalArgumentException("number of notes exceeds the limit");
        } 
        noteList.add(newNote);
    }


    public void deleteNote(String noteId){
        this.noteList.remove(findNoteById(noteId));
    }

    public int findNoteById(String noteId) {
        for (int i = 0; i < this.noteList.size(); i++) {
            if (this.noteList.get(i).getNoteId().equals(noteId)) {
                return i;
            }
        }
        return -1;
    }
    
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
                LimitNotifier notifier = new LimitNotifier();
                notifier.attach(view);
                notifier.notifyUpdate((CreditCard)card);
            } 
        }
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

    public void deleteCard(String cardNumberInput) {
        this.cardList.remove(findCardByNumber(cardNumberInput));
    }


    



    public boolean makePayment(String CardNumber, double amount) {
        if (this.cardList.isEmpty()) {
            throw new IllegalArgumentException("No cards in this wallet!");
        }
        int cardIndex = findCardByNumber(CardNumber);
        if (cardIndex == -1) {
            throw new IllegalArgumentException("Cannot find card with this number");
        }

        Card selectedCard = this.cardList.get(cardIndex);

        if (selectedCard == null) {
            throw new IllegalArgumentException("Cannot find card");
        }

        if (selectedCard instanceof PaymentCard) {
            boolean isSuccessful = ((PaymentCard) selectedCard).pay((int)amount);
            return isSuccessful;
        } else {
            throw new IllegalArgumentException("Not a payment card.");
        }


    }

    
}
