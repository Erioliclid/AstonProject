package org.example.files;

public class ErrorLogger {
    /** Логирует ошибку */
    public void logError(Exception e, String errorType, String line) {
        String message = errorType + e.getMessage();
        if (line != null) {
            String truncated = line.length() > 30 ? line.substring(0, 30) + "..." : line;
            message += " Строка: " + truncated;
        }
        System.err.println(message);
    }
}
