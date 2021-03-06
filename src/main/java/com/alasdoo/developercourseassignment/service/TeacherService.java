package com.alasdoo.developercourseassignment.service;

import com.alasdoo.developercourseassignment.dto.TeacherDTO;

public interface TeacherService extends CrudService<TeacherDTO> {

    public TeacherDTO findByTeacherNameAndTeacherSurname(String name, String surname);

    public TeacherDTO findByTeacherEmail(String email);
}
