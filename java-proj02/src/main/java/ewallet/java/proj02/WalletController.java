package ewallet.java.proj02;

import ewallet.java.proj02.javafx.CardWindow;
import ewallet.java.proj02.javafx.NoteWindow;
import ewallet.java.proj02.javafx.PaymentPanel;
import ewallet.java.proj02.sampledata.StoredWallet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Random;

/**
 * This acts as a controller class for the E-Wallet app. This controls the events that happen following the click of
 * buttons as well as updating the combobox list of cards and notes. This manipulates the ewallet object as actions are
 * handled.
 */
public class WalletController {

    private EWallet wallet;
    private StoredWallet storedWallet;

    private String name;

    private ComboBox cbCards;
    private ComboBox cbNotes;
    private Label lblCashDisplay;



    public WalletController(ComboBox cbCards, ComboBox cbNotes, Label lblCashDisplay) {
        this.wallet = new EWallet(new ArrayList<Card>(), new ArrayList<Note>(), 0);
        this.name = "john";
        this.cbCards = cbCards;
        this.cbNotes = cbNotes;
        this.lblCashDisplay = lblCashDisplay;
        this.storedWallet = new StoredWallet();
        updateCashDisplay(); //set the display initally
    }

    public void handleCardCreationInput(CardWindow cardCreationPane) {
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


    }

    public void handleNoteDeletion(TextField tfNoteIdInput) {
        this.wallet.deleteNote(tfNoteIdInput.getText());
        updateNoteDropdownList();
    }

    public void handleCardDeletion(TextField tfCardIdInput) {
        this.wallet.deleteCard(tfCardIdInput.getText());
        updateCardDropdownList();
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

    public void handleAddCash(PaymentPanel paymentPanel) {
        double cashAmount = 0;
        try {
            cashAmount = Double.parseDouble(paymentPanel.getAddCashInput());
            this.wallet.setCash(this.wallet.getCash() + cashAmount);
            paymentPanel.setErrorMessage("");
            updateCashDisplay();
        } catch (NumberFormatException exc) {
            paymentPanel.setErrorMessage("Invalid amount");
        }
    }

    public void handlePayWithCash(PaymentPanel paymentPanel) {
        double payAmount = 0;
        payAmount = Double.parseDouble(paymentPanel.getPayCashInput());
        if (payAmount <= this.wallet.getCash()) {
            this.wallet.setCash(this.wallet.getCash() - payAmount);
            paymentPanel.setErrorMessage("");
            updateCashDisplay();
        } else {
            paymentPanel.setErrorMessage("Not enough cash.");
        }
    }

    public void handlePayWithCard(PaymentPanel paymentPanel) {
        double payAmount = 0;
        payAmount = Double.parseDouble(paymentPanel.getPayCardInput());
        String cardNumberInput = paymentPanel.getCardNumberInput();
        try {
            boolean isSuccessful = this.wallet.makePayment(cardNumberInput, payAmount);
            if (!isSuccessful) {
                paymentPanel.setErrorMessage("Not enough funds; transaction declined");
            } else {
                paymentPanel.setErrorMessage("");
                updateCardDropdownList();
            }
        } catch (IllegalArgumentException exc) {
            paymentPanel.setErrorMessage(exc.getMessage());
        }

    }

    //SAVE / LOAD controller
    public void handleSaveWallet() {
        this.storedWallet.setStoredWallet(new EWallet(this.wallet));
        updateCashDisplay();
        updateNoteDropdownList();
        updateCashDisplay();
    }
    public void handleLoadWallet() {

        this.wallet = new EWallet(this.storedWallet.getStoredWallet());
        updateCashDisplay();
        updateNoteDropdownList();
        updateCardDropdownList();
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
            this.cbNotes.setPromptText("akshan");
            this.cbNotes.setValue(null);
        }

    }

    private void updateCashDisplay() {
        this.lblCashDisplay.setText("$" + ((double)Math.round(100 *this.wallet.getCash()) / 100));
    }



}
