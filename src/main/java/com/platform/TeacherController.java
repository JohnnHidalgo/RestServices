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

    @GetMapping("/teacher")
    List<Teacher> all() {
        return repository.findAll();
    }

    @PostMapping("/teacher")
    Teacher newEmployee(@RequestBody Teacher newTeacher) {
        return repository.save(newTeacher);
    }

    // Single item

    @GetMapping("/teacher/{id}")
    Teacher one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @PutMapping("/teacher/{id}")
    Teacher replaceEmployee(@RequestBody Teacher newTeacher, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newTeacher.getName());
                    employee.setCourse(newTeacher.getCourse());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newTeacher.setId(id);
                    return repository.save(newTeacher);
                });
    }

    @DeleteMapping("/teacher/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
