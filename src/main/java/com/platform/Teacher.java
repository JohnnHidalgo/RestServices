package com.platform;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Teacher {

  private @Id @GeneratedValue Long id;
  private String name;
  private String course;

  Teacher() {}

  Teacher(String name, String course) {
    this.name = name;
    this.course = course;
  }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        String[] parts =name.split(" ");
        this.name = parts[0];
    }
}