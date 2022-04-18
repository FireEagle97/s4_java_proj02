package ewallet.java.proj02.tests;

import ewallet.java.proj02.Note;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class NoteJUnit {

    @Test
    public void testEquals() {
        Note sampleNote = new Note(1, 5,10,2005, "Sample note. The Id of the notes is that makes a note equal, as ids are unique to each note");
        Note otherNote = new Note(1, 6,2,1,"This note is equal to the first even though the message is different");
        assertEquals(true, sampleNote.equals(otherNote));
    }

    @Test
    public void testEqualsNotEqual() {
        Note sampleNote = new Note(1, 5, 10, 2005, "different id means not equal");
        Note otherNote = new Note(2, 5, 10, 2005,"different id means not equal");
        assertEquals(false, sampleNote.equals(otherNote));
    }

    @Test
    public void testGetId() {
        Note sampleNote = new Note(5, 10, 2005, "different id means not equal");
        assertEquals("00001", sampleNote.getNoteId());
    }

    @Test
    public void testGetId2() {
        Note sampleNote = new Note(5, 10, 2005, "different id means not equal");
        Note sampleNote2 = new Note(5, 10, 2005, "different id means not equal");
        //makes sure the id is 00002 for the second note
        assertEquals("00002", sampleNote2.getNoteId());
    }

    @Test
    public void testGetNoteBody() {
        Note sampleNote = new Note(4, 1, 2022, "This is the message");
        assertEquals("This is the message", sampleNote.getNoteBody());
    }
}
