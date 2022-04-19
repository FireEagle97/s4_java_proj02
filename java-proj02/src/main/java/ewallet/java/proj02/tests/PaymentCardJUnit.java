package ewallet.java.proj02.tests;

import ewallet.java.proj02.*;
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
    public void testGetLimitDebit() {
        PaymentCard sampleDebitCard = new DebitCard("001", 312, 12, 2028, "John Doe", "12345678");
        assertEquals(312, sampleDebitCard.getLimit());
    }

    @Test
    public void testSetLimitDebit() {
        PaymentCard sampleDebitCard = new DebitCard("001", 1000, 12, 2028, "John Doe", "12345678");
        sampleDebitCard.setLimit(sampleDebitCard.getLimit() - 200);
        assertEquals(800, sampleDebitCard.getLimit());
    }


    @Test
    public void testGetAmountOwedCredit() {
        PaymentCard sampleCreditCard = new CreditCard("775", 500, 10, 2030, "John Doe", "12345678");
        assertEquals(0, ((CreditCard)sampleCreditCard).getAmountOwed());
    }

    @Test
    public void testPayCredit() {
        PaymentCard sampleCreditCard = new CreditCard("775", 500, 10, 2030, "John Doe", "12345678");
        boolean isSuccess = sampleCreditCard.pay(250);
        assertEquals(250, ((CreditCard)sampleCreditCard).getAmountOwed());
    }

    @Test
    public void testPayCreditExcessLimit() {
        PaymentCard sampleCreditCard = new CreditCard("775", 500, 10, 2030, "John Doe", "12345678");
        boolean isSuccess = sampleCreditCard.pay(600);
        assertEquals(false, isSuccess);
    }

    @Test
    public void testLimitAfterPayment() {
        PaymentCard sampleDebitCard = new DebitCard("222", 1500, 2, 2025, "John Doe", "11223344");
        boolean isSuccess = sampleDebitCard.pay(500);
        assertEquals(1000, sampleDebitCard.getLimit());
    }

    @Test
    public void testPayDebit() {
        PaymentCard sampleDebitCard = new DebitCard("222", 1500, 2, 2025, "John Doe", "11223344");
        boolean isSuccess = sampleDebitCard.pay(500);
        assertEquals(true, isSuccess);
    }

    @Test
    public void testPayDebitInsufficientFunds() {
        PaymentCard sampleDebitCard = new DebitCard("222", 1500, 2, 2025, "John Doe", "11223344");
        boolean isSuccess = sampleDebitCard.pay(2022);
        assertEquals(false, isSuccess);
    }

    @Test
    public void testEquals() {
        Card paymentCard = new DebitCard("222", 1500, 2, 2025, "John Doe", "11223344");
        Card paymentCard2 = new DebitCard("222", 1500, 2, 2025, "John Doe", "11223344");
        assertEquals(true, paymentCard.equals(paymentCard2));
    }

    // Different card types are not equal (Personal card should not equal payment card)
    @Test
    public void testNotEquals() {
        Card paymentCard = new DebitCard("222", 1500, 2, 2025, "John Doe", "11223344");
        Card personalCard = new PersonalCard("John Doe", "11223344", 2020, 4, "Fishing License");
        assertEquals(false, paymentCard.equals(personalCard));
    }

}
