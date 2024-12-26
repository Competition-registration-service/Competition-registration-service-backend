package ru.vsu.cs.sakovea.exeptions;

public class MyEntityNotFoundException extends RuntimeException {

    public MyEntityNotFoundException(Long id) {
        super("Entity is not found, id="+id);
    }
}