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
    private List<Card> CardList;
    private List<Note> NoteList;
    private double cash;

    public EWallet(List<Card> CardList, List<Note> NoteList, double cash) {
        this.CardList = CardList;
        this.NoteList = NoteList;
        this.cash = cash;
    }

    public List<Card> getCardList() {
        return this.CardList;
    }

    public List<Note> getNoteList() {
        return this.NoteList;
    }

    public double getCash() {
        return this.cash;
    }

    
}
