package ewallet.java.proj02;

import ewallet.java.proj02.javafx.NoteWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

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
        System.out.println();
        System.out.println(noteCreationPane.getDayInput());
        System.out.println(noteCreationPane.getYearInput());
        System.out.println(noteCreationPane.getNoteBodyInput());
        int month = Integer.parseInt(noteCreationPane.getMonthInput());
        int day = Integer.parseInt(noteCreationPane.getDayInput());
        int year = Integer.parseInt(noteCreationPane.getYearInput());
        String body = noteCreationPane.getNoteBodyInput();

        this.wallet.addNote(new Note(day, month, year, body));
        System.out.println("sova note success!");
        this.wallet.listNotes();
        updateNoteDropdownList();




    }

    private void updateNoteDropdownList() {
        ArrayList<String> noteDays = new ArrayList<String>();
        for (Note note : this.wallet.getNoteList()) {
            Date creationDate = note.getDateOfCreation();
            noteDays.add(creationDate.getMonth() + "-" + creationDate.getDay() + "-" + creationDate.getYear());
        }
        ObservableList<String> notes = FXCollections.observableArrayList(noteDays);
        cbNotes.getItems().clear();
        cbNotes.getItems().addAll(notes);
    }
}
