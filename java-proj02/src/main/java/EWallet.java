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

    public EWallet(List<Card> cardList, List<Note> noteList, double cash) {
        this.cardList = cardList;
        this.noteList = noteList;
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

    public void deleteNote(Note aNote){
        for(Note note: noteList){
            if(note.equals(aNote)){
                noteList.remove(note);
            }
        }
    }

    public void deleteNote(int index){
        this.noteList.remove(index);
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
        for (int i = 0; i < this.cardList.size(); i++) {
            if (this.cardList.get(i).getCardNumber().equals(cardNumberInput)) {
                this.cardList.remove(i);
            }
        }
    }


    public Card findCardByNumber(String cardNumberInput) {
        for (int i = 0; i < this.cardList.size(); i++) {
            if (this.cardList.get(i).getCardNumber().equals(cardNumberInput)) {
                return this.cardList.get(i);
            }
        }
        return null;
    }

    public void makePayment(String CardNumber, int amount) {
        Card selectedCard = findCardByNumber(CardNumber);

        if (selectedCard == null) {
            throw new IllegalArgumentException("Cannot find card");
        }
        //check if a card is a credit card by comparing to a random made-up credit card
        if (!selectedCard.equals(new CreditCard("000", 0, 1, 2000, selectedCard.getCardHolderName(), selectedCard.getCardNumber()))) {
            throw new IllegalArgumentException("Not a credit card");
        }

        ((CreditCard)selectedCard).payBalance(amount);
    }



    
}
