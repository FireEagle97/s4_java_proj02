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

    public void makePayment(int index, int amount) {
        //use polymorphism instead; TODO change later
        if (this.cardList.get(index) instanceof CreditCard) {
            ((CreditCard) this.cardList.get(index)).payBalance(index);
        } else {
            throw  new IllegalArgumentException("not a credit card");
        }
    }



    
}
