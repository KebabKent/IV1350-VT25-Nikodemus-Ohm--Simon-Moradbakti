package se.kth.iv1350.retailStore.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Prints log messages to a file. The log file will be in the
 * current directory and will be called log.txt.
 */
public class FileLogger {
    private PrintWriter logStream;
    private File logFile;

    /**
     * Creates a new instance and also creates a new log file.
     * Appends to the file if it already exists.
     */
    public FileLogger() {
        try {
            File logDir = new File("log");
            if (!logDir.exists()) {
                logDir.mkdir();
            }

            int logNumber = 1;
            do {
                logFile = new File(logDir, "log" + logNumber + ".txt");
                logNumber++;
            } while (logFile.exists());

            logStream = new PrintWriter(new FileWriter(logFile), true);

        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * Prints the specified string to the log file.
     *
     * @param message The string that will be printed to the
     *                log file.
     */
    public void log(String message) {
        logStream.println(message);
    }

    /**
     * Writes a log entry describing a thrown exception.
     *
     * @param exception The exception that shall be logged.
     */
    public void log(Exception exception) {
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append(", Exception was thrown: ");
        logMsgBuilder.append(exception.getMessage());
        logStream.println(logMsgBuilder);
        exception.printStackTrace(logStream);
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}