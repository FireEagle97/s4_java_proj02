/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1811257
 */
import java.text.SimpleDateFormat;
import java.util.Date;
public class Note {
    private Date dateOfCreation;
    private String noteBody;

    public Note(Date dateOfCreation, String noteBody) {
        this.dateOfCreation = dateOfCreation;
        this.noteBody = noteBody;
    }
    
    public Date getDateOfCreation() {
        return this.dateOfCreation;
    }

    public String getNoteBody() {
        return this.noteBody;
    }

    
    
    
}
