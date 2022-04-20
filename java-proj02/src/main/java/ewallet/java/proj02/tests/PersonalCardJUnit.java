package ewallet.java.proj02.tests;
import ewallet.java.proj02.PersonalCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class PersonalCardJUnit {

    @Test
    public void testConstructor() {
        PersonalCard personalCard = new PersonalCard("John Doe", "11223344", 2020, 4, "Good luck Card");
        assertEquals("John Doe", personalCard.getCardHolderName());
        assertEquals("Good luck Card", personalCard.getDescription());
    }
}
