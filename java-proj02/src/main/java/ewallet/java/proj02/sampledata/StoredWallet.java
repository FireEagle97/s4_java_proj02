package ewallet.java.proj02.sampledata;

import ewallet.java.proj02.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This acts as a Mock database for storing one wallet.
 */
public class StoredWallet {
    private EWallet storedWallet;

    public StoredWallet() {
        List<Card> cards = new ArrayList<>();
        cards.add(new DebitCard("222", 500, 4, 2028, "john", "1"));
        cards.add(new CreditCard("214", 1000, 4, 2028, "john", "2"));
        cards.add(new CreditCard("544", 413, 1, 2024, "john", "3"));
        cards.add(new DebitCard("372", 300, 6, 2024, "john", "4"));
        cards.add(new PersonalCard("john", "00010001", 12, 2030, "License"));
        cards.add(new PersonalCard("john", "00010002", 9, 2027, "Health Card"));
        cards.add(new PersonalCard("john", "00010003", 2, 2022, "Dawson ID card"));

        List<Note> notes = new ArrayList<>();
        notes.add(new Note(6, 10, 2022, "School's out"));
        notes.add(new Note(5, 1, 2022, "Rent of 800 dollars is due!"));
        notes.add(new Note(9, 6, 2022, "Plan for back to school"));
        notes.add(new Note(10, 30, 2022, "Buy lots of candies for Halloween"));
        notes.add(new Note(11, 25, 2022, "Play league of legends as Akshan"));

        this.storedWallet = new EWallet(cards, notes, 750.00);
    }

    public EWallet getStoredWallet() {
        return this.storedWallet;
    }

    public void setStoredWallet(EWallet wallet) {
        this.storedWallet = wallet;
    }
}
