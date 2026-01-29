package org.example.exception;

import java.io.IOException;

public class NotValidCityDataException extends IOException {
    public NotValidCityDataException(Throwable cause) {
        super(cause);
    }

    public NotValidCityDataException() {
        super();
    }

    public NotValidCityDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidCityDataException(String message) {
        super(message);
    }
}
