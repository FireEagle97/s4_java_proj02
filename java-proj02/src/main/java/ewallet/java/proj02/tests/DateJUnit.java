package ewallet.java.proj02.tests;
import static org.junit.jupiter.api.Assertions.*;

import ewallet.java.proj02.Date;
import org.junit.jupiter.api.Test;


public class DateJUnit {

    @Test
    public void testConstructor() {
        Date sampleDate = new Date(2022, 12);
        assertEquals(2022, sampleDate.getYear());
        assertEquals(12, sampleDate.getMonth());
        assertEquals(1, sampleDate.getDay()); //day is defaulted to 1 if no day given
    }

    @Test
    public void testConstructor2() {
        Date sampleDate = new Date(2022, 12, 25);
        assertEquals(2022, sampleDate.getYear());
        assertEquals(12, sampleDate.getMonth());
        assertEquals(25, sampleDate.getDay()); //day is defaulted to 1 if no day given
    }


}
