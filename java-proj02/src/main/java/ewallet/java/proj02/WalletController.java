package ewallet.java.proj02;

import ewallet.java.proj02.javafx.CardWindow;
import ewallet.java.proj02.javafx.NoteWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Random;

public class WalletController {

    private EWallet wallet;
    private String name;

    private ComboBox cbCards;
    private ComboBox cbNotes;

    public WalletController(ComboBox cbCards, ComboBox cbNotes) {
        this.wallet = new EWallet(new ArrayList<Card>(), new ArrayList<Note>(), 0);
        this.name = "john";
        this.cbCards = cbCards;
        this.cbNotes = cbNotes;

    }

    public void handleCardCreationInput(CardWindow cardCreationPane) {
        //TODO Add functionality to akshan combobox of cards
        //TODO verify actual card types created (radio buttons already tested)
        Random rng = new Random();
        int expMonth = Integer.parseInt(cardCreationPane.getTfExpMonth());
        int expYear = Integer.parseInt(cardCreationPane.getTfExpYear());
        int limit;
        String personalCardDesc = cardCreationPane.getTfCardDescription();
        if (cardCreationPane.rbPersonalIsSelected()) {
            System.out.println("Personal card created");
            this.wallet.addCard(new PersonalCard(this.name, ""+rng.nextInt(10000000), expYear, expMonth, personalCardDesc));
        } else {
            System.out.println("Payment card created");
            limit = Integer.parseInt(cardCreationPane.getTfLimit());
            PaymentCard newPaymentCard = cardCreationPane.rbDebitIsSelected() ?
                    new DebitCard(""+rng.nextInt(1000), limit, expMonth, expYear, this.name, ""+rng.nextInt(10000000)) :
                    new CreditCard(""+rng.nextInt(1000), limit, expMonth, expYear, this.name, ""+rng.nextInt(10000000));
            this.wallet.addCard(newPaymentCard);
        }
        for (Card x : this.wallet.getCardList()) {
            System.out.println(x);
        }
        updateCardDropdownList();
    }

    public void handleNoteCreationInput(NoteWindow noteCreationPane) {
        int month = Integer.parseInt(noteCreationPane.getMonthInput());
        int day = Integer.parseInt(noteCreationPane.getDayInput());
        int year = Integer.parseInt(noteCreationPane.getYearInput());

        String body = noteCreationPane.getNoteBodyInput();
        this.wallet.addNote(new Note(day, month, year, body));
        this.wallet.listNotes();
        updateNoteDropdownList();
        //TODO Add functionality when the max threshhold is reached, display message

    }

    public void handleNoteDeletion(TextField tfNoteIdInput) {
        this.wallet.deleteNote(tfNoteIdInput.getText());
        updateNoteDropdownList();
        //TODO Add functionality when an existing card could not be found, display message
    }

    public void handleViewCard(Label lblCard) {

        if (!this.cbCards.getItems().isEmpty()) {
            String selectedCardNumber = this.cbCards.getValue().toString();
            Card selectedCard = wallet.getCardList().get(wallet.findCardByNumber(selectedCardNumber));
            lblCard.setText(selectedCard.toString());
        }

    }

    public void handleViewNote(Label lblNote) {

        if (!this.cbNotes.getItems().isEmpty()) {
            String selectedNoteId = this.cbNotes.getValue().toString();
            Note selectedNote = wallet.getNoteList().get(wallet.findNoteById(selectedNoteId));
            lblNote.setText(selectedNote.toString());
        }

    }

    private void updateCardDropdownList() {
        ArrayList<String> cardIds = new ArrayList<>();
        for (Card card : this.wallet.getCardList()) {
            cardIds.add(card.getCardNumber());
        }
        ObservableList<String> cards = FXCollections.observableArrayList(cardIds);
        this.cbCards.getItems().clear();
        this.cbCards.getItems().addAll(cards);
        if (!cards.isEmpty()) {
            cbCards.setValue(cards.get(cards.size() - 1));
        }

    }

    private void updateNoteDropdownList() {
        ArrayList<String> noteIds = new ArrayList<>();
        for (Note note : this.wallet.getNoteList()) {
            noteIds.add(note.getNoteId());
        }
        ObservableList<String> notes = FXCollections.observableArrayList(noteIds);
        this.cbNotes.getItems().clear();
        this.cbNotes.getItems().addAll(notes);
        if (!notes.isEmpty()) {
            cbNotes.setValue(notes.get(notes.size() - 1));
        }

    }


}
