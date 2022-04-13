package ewallet.java.proj02;

import ewallet.java.proj02.javafx.NoteWindow;
import javafx.event.ActionEvent;

import java.util.ArrayList;

public class WalletController {

    private EWallet wallet;
    private String name;

    public WalletController() {
        this.wallet = new EWallet(new ArrayList<Card>(), new ArrayList<Note>(), 0);
        this.name = "john";

    }


    public void handleNoteCreationInput(ActionEvent evt, NoteWindow noteCreationPane) {
        System.out.println();
        System.out.println(noteCreationPane.getDayInput());
        System.out.println(noteCreationPane.getYearInput());
        System.out.println(noteCreationPane.getNoteBodyInput());
//        int month = Integer.parseInt(noteCreationPane.getMonthInput());
//        Note newNote = new Note(Integer.parseInt())
//        this.wallet.addNote(new Note());


    }
}
