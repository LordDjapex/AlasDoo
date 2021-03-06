package com.alasdoo.developercourseassignment.controller;

import com.alasdoo.developercourseassignment.dto.StudentDeveloperCourseDTO;
import com.alasdoo.developercourseassignment.service.StudentDeveloperCourseService;
import com.alasdoo.developercourseassignment.service.impl.StudentDeveloperCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/studentdevelopercourse")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentDeveloperCourseController {

    @Autowired
    private StudentDeveloperCourseService studentDeveloperCourseService;

    @GetMapping(value = "/getStudentCourse/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDeveloperCourseDTO> selectStudentDeveloperCourse(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(studentDeveloperCourseService.findOne(id), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDeveloperCourseDTO>> getAllStudentDeveloperCourses() {
        return new ResponseEntity<>(studentDeveloperCourseService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/addStudentDeveloperCourse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDeveloperCourseDTO> saveStudentDeveloperCourse(@RequestBody StudentDeveloperCourseDTO studentDeveloperCourseDTO) {
        return new ResponseEntity<>(studentDeveloperCourseService.save(studentDeveloperCourseDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDeveloperCourseDTO>updateStudentDeveloperCourse(@PathVariable("id") Integer id,
                                                                                 @RequestBody StudentDeveloperCourseDTO studentDeveloperCourseDTO) {
        return new ResponseEntity<>(studentDeveloperCourseService.update(id, studentDeveloperCourseDTO), HttpStatus.OK) ;
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteStudentDeveloperCourse(@PathVariable("id") Integer id) {
        studentDeveloperCourseService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/get/student/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDeveloperCourseDTO> findByStudentId(@PathVariable("studentId") Integer studentId) {
        return new ResponseEntity<>(studentDeveloperCourseService.findByStudentId(studentId), HttpStatus.OK);
    }

    @GetMapping(value = "/get/course/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDeveloperCourseDTO>> findByCourseId(@PathVariable("courseId") Integer courseId) {
        return new ResponseEntity<>(studentDeveloperCourseService.findByDeveloperCourseId(courseId), HttpStatus.OK);
    }
}
