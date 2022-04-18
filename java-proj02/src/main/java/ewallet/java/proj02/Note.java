package ewallet.java.proj02;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1811257
 */
public class Note {
    private String noteId;
    private Date dateOfCreation;
    private String noteBody;

    public static int currentNoteIdIndex = 1;

    public Note(int day,int month,int year, String noteBody) {
        this.dateOfCreation = new Date(day,month,year);
        this.noteBody = noteBody;
        this.noteId = String.format("%05d", currentNoteIdIndex);
        Note.currentNoteIdIndex++;
    }

    //only for junit
    public Note(int noteId, int day,int month,int year, String noteBody) {
        this.dateOfCreation = new Date(day,month,year);
        this.noteBody = noteBody;
        this.noteId = String.format("%05d", noteId);
    }


    public String getNoteId() {
        return this.noteId;
    }

    public Date getDateOfCreation() {
        return this.dateOfCreation;
    }

    public String getNoteBody() {
        return this.noteBody;
    }



    @Override
    public boolean equals(Object note) {
        if (!(note instanceof Note)) {
            System.out.println("not equal type");
            return false;
        }
        return this.noteId.equals(((Note) note).noteId);
    }
    @Override
    public String toString(){
        return "This note was written on " + this.dateOfCreation + "\n" + this.noteBody;
    }
    
    
    
}
