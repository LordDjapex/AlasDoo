package com.alasdoo.developercourseassignment.controller;

import com.alasdoo.developercourseassignment.dto.TeacherDeveloperCourseDTO;
import com.alasdoo.developercourseassignment.service.TeacherDeveloperCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacherdevelopercourse")
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherDeveloperCourseController {

    @Autowired
    private TeacherDeveloperCourseService teacherDeveloperCourseService;

    @GetMapping(value = "/getTeacherCourse/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDeveloperCourseDTO> selectTeacherDeveloperCourse(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(teacherDeveloperCourseService.findOne(id), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TeacherDeveloperCourseDTO>> getAllTeacherDeveloperCourses() {
        return new ResponseEntity<>(teacherDeveloperCourseService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/addTeacherCourse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDeveloperCourseDTO> saveTeacherDeveloperCourse(@RequestBody TeacherDeveloperCourseDTO teacherDeveloperCourseDTO) {
        return new ResponseEntity<>(teacherDeveloperCourseService.save(teacherDeveloperCourseDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDeveloperCourseDTO> updateTeacherDeveloperCourse(@PathVariable("id") Integer id,
                                                                  @RequestBody TeacherDeveloperCourseDTO teacherDeveloperCourseDTO) {
        return new ResponseEntity<>(teacherDeveloperCourseService.update(id, teacherDeveloperCourseDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTeacherDeveloperCourse(@PathVariable("id") Integer id) {
        teacherDeveloperCourseService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/get/teacher/{teacherId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDeveloperCourseDTO> findByTeacherId(@PathVariable("teacherId") Integer teacherId) {
        return new ResponseEntity<>(teacherDeveloperCourseService.findByTeacherId(teacherId), HttpStatus.OK);
    }

}
