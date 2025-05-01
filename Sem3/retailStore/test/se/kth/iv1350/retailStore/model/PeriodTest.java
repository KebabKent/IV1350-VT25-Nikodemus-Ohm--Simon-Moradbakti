package se.kth.iv1350.retailStore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PeriodTest {
    private Period period;

    @BeforeEach
    public void setUp() {
        period = new Period();
    }

    @AfterEach
    public void tearDown() {
        period = null;
    }

    @Test
    public void testSetEndTime() {
        period.setEndTime();
        assertNotNull(period.getSaleEndTime(), "Sale end time should not be null after setting it.");
    }

}
