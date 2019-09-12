package com.platform;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;

import org.springframework.stereotype.Component;

@Component
public class TeacherResourceAssembler implements ResourceAssembler<Teacher, Resource<Teacher>> {

    @Override
    public Resource<Teacher> toResource(Teacher teacher) {

        return new Resource<>(teacher,
                linkTo(methodOn(TeacherController.class).one(teacher.getId())).withSelfRel(),
                linkTo(methodOn(TeacherController.class).all()).withRel("teacher"));
    }
}

