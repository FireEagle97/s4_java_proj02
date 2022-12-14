package ewallet.java.proj02;

import ewallet.java.proj02.javafx.CardWindow;
import ewallet.java.proj02.javafx.NoteWindow;
import ewallet.java.proj02.javafx.PaymentPanel;
import ewallet.java.proj02.sampledata.StoredWallet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Random;

/**
 * This acts as a controller class for the E-Wallet app. This controls the events that happen following the click of
 * buttons as well as updating the combobox list of cards and notes. This manipulates the ewallet object as actions are
 * handled.
 *
 */
public class WalletController {

    private EWallet wallet;
    private StoredWallet storedWallet;
    private String name;
    private ComboBox cbCards;
    private ComboBox cbNotes;
    private Label lblCashDisplay;



    public WalletController() {
        this.wallet = new EWallet(new ArrayList<Card>(), new ArrayList<Note>(), 0);
        //this.wallet.setObservers();
        this.lblCashDisplay = new Label("$0.0");
        this.name = "john";
        this.cbCards = new ComboBox();
        this.cbCards.setPromptText("choose a card");
        this.cbNotes = new ComboBox();
        this.cbNotes.setPromptText("choose a note");
        this.storedWallet = new StoredWallet();
        updateCashDisplay(); //set the display initally
    }
    
    public ObservableList<String> cbNotesInitiater(){
        String[] notesList = {"test"};
        ObservableList<String> notesInitiater = FXCollections.observableArrayList(notesList);
        return notesInitiater;
    }
    
    public ObservableList<String> cbCardsInitiater(){
        String[] cardsList = {"choose a card"};
        ObservableList<String> cardsInitiater = FXCollections.observableArrayList(cardsList);
        return cardsInitiater;
    }

    //handles the action of adding a card pressing the add card button
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
        //this.wallet.setObservers();
    }

    //handles the action of adding a note pressing the add note button
    public void handleNoteCreationInput(NoteWindow noteCreationPane) {
        int month = Integer.parseInt(noteCreationPane.getMonthInput());
        int day = Integer.parseInt(noteCreationPane.getDayInput());
        int year = Integer.parseInt(noteCreationPane.getYearInput());

        String body = noteCreationPane.getNoteBodyInput();
        this.wallet.addNote(new Note(day, month, year, body));
        this.wallet.listNotes();
        updateNoteDropdownList();


    }

    //handles the action of pressing delete note
    public void handleNoteDeletion(String noteIndex) {
        this.wallet.deleteNote(noteIndex);
        updateNoteDropdownList();
    }

    //handles the action of pressing delete card
    public void handleCardDeletion(String noteIndex) {
        this.wallet.deleteCard(noteIndex);
        updateCardDropdownList();
    }

    //handles the action of viewing a card
    public Label handleViewCard(Label lblCard) {

        if (!this.cbCards.getItems().isEmpty()) {
            String selectedCardNumber = this.cbCards.getValue().toString();
            if(! selectedCardNumber.equals("choose a card")){
                Card selectedCard = wallet.getCardList().get(wallet.findCardByNumber(selectedCardNumber));
                lblCard.setText(selectedCard.toString());
            }
            else{
                lblCard.setText("");
            }
        }
        return lblCard;

    }

    //handles the action to view notes
    public Label handleViewNote(Label lblNote) {

        if (!this.cbNotes.getItems().isEmpty()) {
            String selectedNoteId = this.cbNotes.getValue().toString();
            if(! selectedNoteId.equals("choose a card")){
                Note selectedNote = wallet.getNoteList().get(wallet.findNoteById(selectedNoteId));
                lblNote.setText(selectedNote.toString());
            }
            else{
                lblNote.setText("");
            }
        }
        return lblNote;
    }

    //handles the action of adding cash upon clicking add cash button
    public void handleAddCash(PaymentPanel paymentPanel) {
        double cashAmount = 0;
        try {
            cashAmount = Double.parseDouble(paymentPanel.getAddCashInput());
            if (cashAmount < 0) {
                paymentPanel.setErrorMessage("Amount cannot be negative");
                return;
            }
            this.wallet.setCash(this.wallet.getCash() + cashAmount);
            paymentPanel.setErrorMessage("");
            updateCashDisplay();
        } catch (NumberFormatException exc) {
            paymentPanel.setErrorMessage("Invalid amount");
        }
    }

    //handles the action of paying with cash upon clicking pay with cash button
    public void handlePayWithCash(PaymentPanel paymentPanel) {
        double payAmount = 0;
        payAmount = Double.parseDouble(paymentPanel.getPayCashInput());
        if (payAmount < 0) {
            paymentPanel.setErrorMessage("Amount cannot be negative");
            return;
        }
        if (payAmount <= this.wallet.getCash()) {
            this.wallet.setCash(this.wallet.getCash() - payAmount);
            paymentPanel.setErrorMessage("");
            updateCashDisplay();
        } else {
            paymentPanel.setErrorMessage("Not enough cash.");
        }
    }

    //handles the action of paying with a card upon clicking pay with card button
    public void handlePayWithCard(PaymentPanel paymentPanel) {
        double payAmount = 0;
        payAmount = Double.parseDouble(paymentPanel.getPayCardInput());
        String cardNumberInput = paymentPanel.getCardNumberInput();
        try {
            if (payAmount < 0) {
                paymentPanel.setErrorMessage("Amount cannot be negative");
                return;
            }
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

    //handles the action of saving the current wallet upon clicking save wallet
    public void handleSaveWallet() {
        this.storedWallet.setStoredWallet(new EWallet(this.wallet));
        updateCashDisplay();
        updateNoteDropdownList();
        updateCashDisplay();
    }

    //handles the action of loading the wallet upon clicking load wallet
    public void handleLoadWallet() {

        this.wallet = new EWallet(this.storedWallet.getStoredWallet());
        this.wallet.setObservers();
        updateCashDisplay();
        updateNoteDropdownList();
        updateCardDropdownList();
    }

    //updates the card dropdown list for the current card list of the wallet
     public void updateCardDropdownList() {
        ArrayList<String> cardIds = new ArrayList<>();
        for (Card card : this.wallet.getCardList()) {
            cardIds.add(card.getCardNumber());
        }
        ObservableList<String> cards = FXCollections.observableArrayList(cardIds);
        this.cbCards.getItems().clear();
        this.cbCards.getItems().addAll(cards);
        this.cbNotes.setPromptText("choose a card");

    }


    //example of multithreading use, just prints all the notes to the console
    public void handlePrintAllNotes() {
        Thread noteThread = new Thread(this.wallet);
        noteThread.start();
    }

    //updates the note dropdown list for the current note list of the wallet
    private void updateNoteDropdownList() {
        ArrayList<String> noteIds = new ArrayList<>();
        for (Note note : this.wallet.getNoteList()) {
            noteIds.add(note.getNoteId());
        }
        ObservableList<String> notes = FXCollections.observableArrayList(noteIds);
        this.cbNotes.getItems().clear();
        this.cbNotes.getItems().addAll(notes);
        this.cbNotes.setPromptText("choose a note");
        if (!notes.isEmpty()) {
            this.cbNotes.setPromptText("akshan");
            this.cbNotes.setValue(null);
        }

    }

    //updates the cash display of the ewallet which sets the current cash value of the ewallet
    private void updateCashDisplay() {
        this.lblCashDisplay.setText("$" + ((double)Math.round(100 *this.wallet.getCash()) / 100));
    }

    public ComboBox getCbNotes() {
        return cbNotes;
    }

    public ComboBox getCbCards() {
        return cbCards;
    }

    public Label getLblCashDisplay(){
        return lblCashDisplay;
    }

}
