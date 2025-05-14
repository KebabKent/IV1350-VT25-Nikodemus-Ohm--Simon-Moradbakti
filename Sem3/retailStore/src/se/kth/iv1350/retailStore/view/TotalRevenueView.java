package se.kth.iv1350.retailStore.view;

import se.kth.iv1350.retailStore.util.TotalRevenueObserver;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import se.kth.iv1350.retailStore.dto.AmountDTO;

public class TotalRevenueView implements TotalRevenueObserver {
    private AmountDTO totalRevenue;

    public TotalRevenueView() {
        this.totalRevenue = new AmountDTO(0);
    }

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
