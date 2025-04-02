package ru.vsu.cs.sakovea.exceptions;

public class MyEntityNotFoundException extends RuntimeException {

    public MyEntityNotFoundException(Long id) {
        super("Entity is not found, id="+id);
    }
}