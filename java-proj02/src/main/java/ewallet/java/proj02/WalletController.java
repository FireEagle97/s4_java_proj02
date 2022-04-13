package ewallet.java.proj02;

import ewallet.java.proj02.javafx.NoteWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

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


    public void handleNoteCreationInput(ActionEvent evt, NoteWindow noteCreationPane) {
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

    public void handleViewNote(ActionEvent evt, Label lblNote) {

        if (!this.cbNotes.getItems().isEmpty()) {
            String selectedNoteId = this.cbNotes.getValue().toString();
            Note selectedNote = wallet.getNoteList().get(wallet.findNoteById(selectedNoteId));
            lblNote.setText(selectedNote.toString());
        }

    }

    private void updateNoteDropdownList() {
        ArrayList<String> noteIds = new ArrayList<String>();
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
