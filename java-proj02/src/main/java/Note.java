/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1811257
 */
public class Note {
    private Date dateOfCreation;
    private String noteBody;

    public Note(int day,int month,int year, String noteBody) {
        this.dateOfCreation = new Date(day,month,year);
        this.noteBody = noteBody;
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
        return true;
    }
    @Override
    public String toString(){
        return "This note was written on " + this.dateOfCreation + "\n" + this.noteBody;
    }
    
    
    
}
