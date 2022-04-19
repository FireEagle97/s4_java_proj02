package ewallet.java.proj02.tests;

import ewallet.java.proj02.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EWalletJUnit {

    //This is the sample wallet that will be used for testing
    public EWallet sampleWallet() {
        Card card1 = new PersonalCard("John Doe", "123456789", 2028, 6, "Health Card");
        Card card2 = new DebitCard("001", 1000, 12, 2028, "John Doe", "000000001");
        Note note1 = new Note(6, 10, 2022, "Sample note");

        List<Card> sampleCards = new ArrayList<>();
        List<Note> sampleNotes = new ArrayList<>();

        sampleCards.add(card1);
        sampleCards.add(card2);
        sampleNotes.add(note1);

        EWallet sampleWallet = new EWallet(sampleCards, sampleNotes, 300);
        return sampleWallet;
    }

    public EWallet sampleWallet2() {
        Card card1 = new CreditCard("111", 1000, 9, 2030, "John Doe", "11111111");
        Card card2 = new DebitCard("222", 1500, 12, 2028, "John Doe", "22222222");
        Note note1 = new Note(1, 1, 10, 2022, "Sample note");

        List<Card> sampleCards = new ArrayList<>();
        List<Note> sampleNotes = new ArrayList<>();

        sampleNotes.add(note1);
        sampleCards.add(card1);
        sampleCards.add(card2);

        EWallet sampleWallet = new EWallet(sampleCards, sampleNotes, 1000);
        return sampleWallet;
    }

    public EWallet sampleWallet3() {
        Card card1 = new CreditCard("111", 1000, 9, 2030, "John Doe", "00000001");
        Note note1 = new Note(1, 1, 10, 2022, "Sample note");

        List<Card> sampleCards = new ArrayList<>();
        List<Note> sampleNotes = new ArrayList<>();

        sampleNotes.add(note1);
        sampleCards.add(card1);

        EWallet sampleWallet = new EWallet(sampleCards, sampleNotes, 500);
        return sampleWallet;
    }

    public EWallet sampleEmptyWallet() {
        List<Card> sampleCards = new ArrayList<>();
        List<Note> sampleNotes = new ArrayList<>();

        EWallet sampleWallet = new EWallet(sampleCards, sampleNotes, 1000);
        return sampleWallet;
    }

    @Test
    public void testTwoCards() {
        EWallet sampleWallet = sampleWallet();
        assertEquals(2, sampleWallet.getCardList().size());
    }

    @Test
    public void testOneNote() {
        EWallet sampleWallet = sampleWallet();
        assertEquals(1, sampleWallet.getNoteList().size());
    }

    @Test
    public void testAddCard() {
        EWallet sampleWallet = sampleWallet();
        Card newCard = new PersonalCard("John Doe", "11111111", 2025, 1, "License");
        sampleWallet.addCard(newCard);
        assertEquals(3, sampleWallet.getCardList().size());

    }

    @Test
    public void testAddCard2() {
        EWallet sampleWallet = sampleWallet();
        Card newCard = new DebitCard("001", 1000, 12, 2028, "John Doe", "12345678");
        sampleWallet.addCard(newCard);
        assertEquals(3, sampleWallet.getCardList().size());

    }

    @Test
    public void testAddNote() {
        EWallet sampleWallet = sampleWallet();
        Note newNote = new Note(6, 4, 2022, "New note");
        sampleWallet.addNote(newNote);
        assertEquals(2, sampleWallet.getNoteList().size());
    }

    @Test
    public void testDeleteCard() {
        EWallet sampleWallet = sampleWallet3();
        sampleWallet.deleteCard("00000001");
        assertEquals(0, sampleWallet.getCardList().size()); //list is now empty
    }

    @Test
    public void testDeleteCardNoCardFound() {
        EWallet sampleWallet = sampleWallet3();
        sampleWallet.deleteCard("69696969");
        assertEquals(1, sampleWallet.getCardList().size()); //list still has one card; list untouched
    }

    @Test
    public void testDeleteNote() {
        EWallet sampleWallet = sampleWallet3();
        sampleWallet.deleteNote("00001");
        assertEquals(0, sampleWallet.getNoteList().size()); //list is now empty
    }

    @Test
    public void testDeleteNoteNoNoteFound() {
        EWallet sampleWallet = sampleWallet3();
        sampleWallet.deleteNote("42069");
        assertEquals(1, sampleWallet.getNoteList().size()); //list still has one note; list untouched
    }

    @Test
    public void testFindCardByNumber() {
        EWallet sampleWallet = sampleWallet();
        assertEquals(1, sampleWallet.findCardByNumber("000000001"));
    }

    @Test
    public void testFindCardByNumberNotFound() {
        EWallet sampleWallet = sampleWallet();
        assertEquals(-1, sampleWallet.findCardByNumber("99999999"));
    }

    @Test
    public void testFindNoteByNumber() {
        EWallet sampleWallet = sampleWallet2();
        assertEquals(0, sampleWallet.findNoteById("00001"));
    }

    @Test
    public void testFindNoteByNumberNotFound() {
        EWallet sampleWallet = sampleWallet2();
        assertEquals(-1, sampleWallet.findNoteById("65536"));
    }

    @Test
    public void testMakePayment() {
        EWallet sampleWallet = sampleWallet2();
        boolean isSuccessful = sampleWallet.makePayment("11111111", 0);
        assertEquals(true, isSuccessful);
    }

    @Test
    public void testMakePaymentNoCardsInWallet() {
        EWallet sampleWallet = sampleEmptyWallet();
        System.out.println("Akshan test throw exception");
        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class, () -> sampleWallet.makePayment("69696969696969696969", 0));

        assertEquals("No cards in this wallet!", exc.getMessage());
    }

    @Test
    public void testMakePaymentCardCannotBeFound() {
        EWallet sampleWallet = sampleWallet2();
        System.out.println("Akshan test throw exception");

        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class, () -> sampleWallet.makePayment("420691337", 0));

        assertEquals("Cannot find card with this number", exc.getMessage());
    }

    @Test
    public void testMakePaymentGivenNotPaymentCard() {
        EWallet sampleWallet = sampleWallet();
        System.out.println("Akshan test throw exception");

        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class, () -> sampleWallet.makePayment("123456789", 0));

        assertEquals("Not a payment card.", exc.getMessage());
    }

}
