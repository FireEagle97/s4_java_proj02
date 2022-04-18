package ewallet.java.proj02.tests;

import ewallet.java.proj02.CreditCard;
import ewallet.java.proj02.Date;
import ewallet.java.proj02.DebitCard;
import ewallet.java.proj02.PaymentCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentCardJUnit {

    @Test
    public void testConstructorCredit() {
        PaymentCard paymentCard = new CreditCard("001", 1000, 12, 2028, "John Doe", "12345678");
        Date testDate = new Date(2028, 12);
        assertEquals("001", paymentCard.getSecurityCode());
        assertEquals(1000, paymentCard.getLimit());
        assertEquals(true, paymentCard.getExpiryDate().equals(testDate));
        assertEquals("John Doe", paymentCard.getCardHolderName());
        assertEquals("12345678", paymentCard.getCardNumber());
    }

    @Test
    public void testConstructorDebit() {
        PaymentCard paymentCard = new DebitCard("001", 1000, 12, 2028, "John Doe", "12345678");
        Date testDate = new Date(2028, 12);
        assertEquals("001", paymentCard.getSecurityCode());
        assertEquals(1000, paymentCard.getLimit());
        assertEquals(true, paymentCard.getExpiryDate().equals(testDate));
        assertEquals("John Doe", paymentCard.getCardHolderName());
        assertEquals("12345678", paymentCard.getCardNumber());
    }

    @Test
    public void testInvalidLimit1() {
        try{
            PaymentCard sampleCreditCard = new CreditCard("001", 50, 12, 2028, "John Doe", "12345678");
            fail("should have thrown exception here");
        } catch (IllegalArgumentException exc) {
            //passes
        } catch (Exception exc) {
            fail("Wrong type of exception");
        }
    }

    @Test
    public void testInvalidLimit2() {
        try{
            PaymentCard sampleCreditCard = new CreditCard("001", 5001, 12, 2028, "John Doe", "12345678");
            fail("should have thrown exception here");
        } catch (IllegalArgumentException exc) {
            //passes
        } catch (Exception exc) {
            fail("Wrong type of exception");
        }
    }

    @Test
    public void testGetLimit() {
        PaymentCard sampleCreditCard = new CreditCard("001", 312, 12, 2028, "John Doe", "12345678");
        assertEquals(312, sampleCreditCard.getLimit());
    }

    @Test
    public void testSetLimit() {
        PaymentCard sampleCreditCard = new CreditCard("001", 1000, 12, 2028, "John Doe", "12345678");
        sampleCreditCard.setLimit(sampleCreditCard.getLimit() - 200);
        assertEquals(800, sampleCreditCard.getLimit());
    }



}
