package ewallet.java.proj02.tests;
import static org.junit.jupiter.api.Assertions.*;

import ewallet.java.proj02.*;
import org.junit.jupiter.api.Test;

public class CreditCardJUnit {


    @Test
    public void testGetAmountOwedCredit() {
        CreditCard sampleCreditCard = new CreditCard("775", 500, 10, 2030, "John Doe", "12345678");
        assertEquals(0, sampleCreditCard.getAmountOwed());
    }

    @Test
    public void testGetAmountOwedCreditAfterUsing() {
        CreditCard sampleCreditCard = new CreditCard("775", 500, 10, 2030, "John Doe", "12345678");
        sampleCreditCard.pay(200);
        assertEquals(200, sampleCreditCard.getAmountOwed());
    }


}
