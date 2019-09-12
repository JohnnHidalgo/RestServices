package com.platform;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TeacherController {

    private final TeacherRepository repository;

    TeacherController(TeacherRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/teachers")
    List<Teacher> all() {
        return repository.findAll();
    }

    @PostMapping("/teachers")
    Teacher newTeacher(@RequestBody Teacher newTeacher) {
        return repository.save(newTeacher);
    }

    // Single item

    @GetMapping("/teachers/{id}")
    Teacher one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @PutMapping("/teachers/{id}")
    Teacher replaceTeacher(@RequestBody Teacher newTeacher, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newTeacher.getName());
                    employee.setRole(newTeacher.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newTeacher.setId(id);
                    return repository.save(newTeacher);
                });
    }

    @DeleteMapping("/teachers/{id}")
    void deleteTeacher(@PathVariable Long id) {
        repository.deleteById(id);
    }
}