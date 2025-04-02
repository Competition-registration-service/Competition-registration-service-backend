package ru.vsu.cs.sakovea.exceptions;

import java.util.HashMap;
import java.util.Map;

public class CustomException extends RuntimeException {
    private final String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Map<String, String> getMessageAsJson() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        return errorResponse;
    }
}
