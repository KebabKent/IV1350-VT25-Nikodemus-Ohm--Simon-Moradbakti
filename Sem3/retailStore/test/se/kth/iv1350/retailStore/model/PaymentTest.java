package se.kth.iv1350.retailStore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Payment payment;

    @BeforeEach
    public void setUp() {
        payment = new Payment();
    }

    @AfterEach
    public void tearDown() {
        payment = null;
    }

    @Test
    public void testSetTotalPrice() {
        float expectedPrice = 100.0f;
        payment.setTotalPrice(expectedPrice);
        assertEquals(expectedPrice, payment.returnTotalPrice(), "Total price should be set correctly.");
    }

    @Test
    public void testCalculateTotalPrice() {

    }
}
