package com.platform;

class TeacherNotFoundException extends RuntimeException {

    TeacherNotFoundException(Long id) {
        super("Could not find teacher " + id);
    }
}