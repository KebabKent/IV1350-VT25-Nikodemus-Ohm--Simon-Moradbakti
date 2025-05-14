package se.kth.iv1350.retailStore.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import se.kth.iv1350.retailStore.dto.AmountDTO;

import java.time.LocalTime;

/**
 * Prints log messages to a file. The log file will be in the
 * current directory and will be called log.txt.
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private PrintWriter logStream;
    private File logFile;

    /**
     * Creates a new instance and also creates a new log file.
     * Appends to the file if it already exists.
     */
    public TotalRevenueFileOutput() {
        try {
            File logDir = new File("log/totalRevenue");
            if (!logDir.exists()) {
                logDir.mkdir();
            }

            int logNumber = 1;
            do {
                logFile = new File(logDir, "totalRevenue" + logNumber + ".txt");
                logNumber++;
            } while (logFile.exists());

            logStream = new PrintWriter(new FileWriter(logFile), true);

        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    public void newRevenue(AmountDTO newRevenue, LocalTime saleTime) {
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append("Total revenue: ");
        logMsgBuilder.append(newRevenue.getAmount());
        logMsgBuilder.append(" Sale was made: " + saleTime);
        logStream.println(logMsgBuilder);
    }
}