package com.platform;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
public class TeacherController {
    private final TeacherRepository repository;
    private final TeacherResourceAssembler assembler;
    TeacherController(TeacherRepository repository,
                      TeacherResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping("/teacher")
    List<Teacher> all() {
        return repository.findAll();
    }

    @PostMapping("/teacher")
    Teacher newTeacher(@RequestBody Teacher newTeacher) {
        return repository.save(newTeacher);
    }

    // Single item

    @GetMapping("/teacher/{id}")
    Teacher one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @PutMapping("/teacher/{id}")
    Teacher replaceTeacher(@RequestBody Teacher newTeacher, @PathVariable Long id) {

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
    void deleteTeacher(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
