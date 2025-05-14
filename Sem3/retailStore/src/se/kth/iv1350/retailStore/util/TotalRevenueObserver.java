package se.kth.iv1350.retailStore.util;

import se.kth.iv1350.retailStore.dto.AmountDTO;

import java.time.LocalTime;

public interface TotalRevenueObserver {

    public void newRevenue(AmountDTO newRevenue, LocalTime saleTime);
}
