package se.kth.iv1350.retailStore.view;

import se.kth.iv1350.retailStore.util.TotalRevenueObserver;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import se.kth.iv1350.retailStore.dto.AmountDTO;

/**
 * This class implements the TotalRevenueObserver interface and is responsible
 * for
 * displaying the total revenue of the store.
 * It updates the total revenue whenever a new sale is made and logs the
 * information.
 */
public class TotalRevenueView implements TotalRevenueObserver {
    private AmountDTO totalRevenue;

    /**
     * Constructs a new TotalRevenueView object with an initial total revenue of 0.
     */
    public TotalRevenueView() {
        this.totalRevenue = new AmountDTO(0);
    }

    /**
     * Updates the total revenue with the new revenue from a sale and logs the
     * information.
     *
     * @param newRevenue The new revenue from the sale.
     * @param saleTime   The time of the sale.
     */
    public void newRevenue(AmountDTO newRevenue, LocalTime saleTime) {
        this.totalRevenue = new AmountDTO(
                this.totalRevenue.getAmount() +
                        newRevenue.getAmount());

        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append("----------------------------------------------------Total revenue: ");
        logMsgBuilder.append(totalRevenue.getAmount());
        logMsgBuilder.append(" Sale was made: " + saleTime);
        System.out.println(logMsgBuilder);
    }
}
