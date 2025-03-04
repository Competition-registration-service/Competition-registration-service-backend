package ru.vsu.cs.sakovea.exeptions;

public class ThrowMyException extends RuntimeException {
    public ThrowMyException() {
    }

    public ThrowMyException(String message) {
        super(message);
    }
}
