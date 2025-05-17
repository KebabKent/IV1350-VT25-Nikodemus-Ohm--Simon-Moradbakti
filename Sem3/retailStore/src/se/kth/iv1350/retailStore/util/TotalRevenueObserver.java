package se.kth.iv1350.retailStore.util;

import se.kth.iv1350.retailStore.dto.AmountDTO;

import java.time.LocalTime;

/**
 * This interface defines the observer pattern for observing changes in total
 * revenue.
 * Implementing classes can register to receive updates when new revenue is
 * generated.
 */
public interface TotalRevenueObserver {

    /**
     * This method is called when new revenue is generated.
     *
     * @param newRevenue The new revenue amount.
     * @param saleTime   The time of the sale.
     */
    public void newRevenue(AmountDTO newRevenue, LocalTime saleTime);
}
