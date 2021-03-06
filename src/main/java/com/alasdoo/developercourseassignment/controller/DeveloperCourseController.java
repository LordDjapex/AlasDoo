package com.alasdoo.developercourseassignment.controller;

import com.alasdoo.developercourseassignment.dto.DeveloperCourseDTO;
import com.alasdoo.developercourseassignment.service.DeveloperCourseService;
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
@RequestMapping("/developercourse")
//it better practice to put this string into some variable
@CrossOrigin(origins = "http://localhost:3000")
public class DeveloperCourseController {

    //was impl
    @Autowired
    private DeveloperCourseService developerCourseService;

    @GetMapping(value = "/getCourse/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeveloperCourseDTO> selectDeveloperCourse(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(developerCourseService.findOne(id), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeveloperCourseDTO>> getAllDeveloperCourses() {
        return new ResponseEntity<>(developerCourseService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/addDeveloperCourse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeveloperCourseDTO> saveDeveloperCourse(@RequestBody DeveloperCourseDTO developerCourseDTO) {
        return new ResponseEntity<>(developerCourseService.save(developerCourseDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeveloperCourseDTO> updateDeveloperCourse(@PathVariable("id") Integer id, @RequestBody DeveloperCourseDTO developerCourseDTO) {
        return new ResponseEntity<>(developerCourseService.update(id, developerCourseDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteDeveloperCourse(@PathVariable("id") Integer id) {
        developerCourseService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/get/{courseName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeveloperCourseDTO>> findByDeveloperCourseName(@PathVariable("courseName") String courseName) {
        return new ResponseEntity<>(developerCourseService.findByDeveloperCourseName(courseName), HttpStatus.OK);
    }

    @GetMapping(value = "/getByStudentId/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeveloperCourseDTO>> getDeveloperCourseByStudentId(@PathVariable("studentId") Integer studentId) {
        return new ResponseEntity<>(developerCourseService.findByDeveloperCourseByStudentId(studentId), HttpStatus.OK);
    }

    @GetMapping(value = "/getByTeacherId/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeveloperCourseDTO>> getDeveloperCourseByTeacherId(@PathVariable("studentId") Integer teacherId) {
        return new ResponseEntity<>(developerCourseService.findByDeveloperCourseByTeacherId(teacherId), HttpStatus.OK);
    }

}
