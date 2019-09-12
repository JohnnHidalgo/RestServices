package com.platform;

public class TeacherNotFoundException extends RuntimeException {

    TeacherNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
