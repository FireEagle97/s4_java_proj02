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

public class WalletController {

    private EWallet wallet;
    private StoredWallet storedWallet;
    private String name;
    private ComboBox cbCards;
    private ComboBox cbNotes;
    private Label lblCashDisplay;



    public WalletController() {
        this.wallet = new EWallet(new ArrayList<Card>(), new ArrayList<Note>(), 0);
        this.wallet.setObservers();
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
        this.wallet.setObservers();
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

    public void handleNoteDeletion(String noteIndex) {
        this.wallet.deleteNote(noteIndex);
        updateNoteDropdownList();
        //TODO Add functionality when an existing note could not be found, display message
    }

    public void handleCardDeletion(String noteIndex) {
        this.wallet.deleteCard(noteIndex);
        updateCardDropdownList();
        //TODO Add functionality when an existing note could not be found, display message
    }

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

    public void handleAddCash(PaymentPanel paymentPanel) {
        double cashAmount = 0;
        cashAmount = Double.parseDouble(paymentPanel.getAddCashInput());
        //TODO add exception handling for non numbers
        this.wallet.setCash(this.wallet.getCash() + cashAmount);
        updateCashDisplay();
    }

    public void handlePayWithCash(PaymentPanel paymentPanel) {
        double payAmount = 0;
        payAmount = Double.parseDouble(paymentPanel.getPayCashInput());
        if (payAmount <= this.wallet.getCash()) {
            this.wallet.setCash(this.wallet.getCash() - payAmount);
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

    public void updateNoteDropdownList() {
        ArrayList<String> noteIds = new ArrayList<>();
        for (Note note : this.wallet.getNoteList()) {
            noteIds.add(note.getNoteId());
        }
        ObservableList<String> notes = FXCollections.observableArrayList(noteIds);
        this.cbNotes.getItems().clear();
        this.cbNotes.getItems().addAll(notes);
        this.cbNotes.setPromptText("choose a note");
    }

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
