package com.alasdoo.developercourseassignment.controller;

import com.alasdoo.developercourseassignment.dto.TeacherDTO;
import com.alasdoo.developercourseassignment.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping(value = "/getTeacher/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDTO> selectTeacher(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(teacherService.findOne(id), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return new ResponseEntity<>(teacherService.findAll(), HttpStatus.OK) ;
    }

    @PostMapping(value = "/addTeacher", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDTO> saveTeacher(@RequestBody TeacherDTO teacherDTO) {
        return new ResponseEntity<>(teacherService.save(teacherDTO), HttpStatus.CREATED) ;
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable("id") Integer id, @RequestBody TeacherDTO teacherDTO) {
        return new ResponseEntity<>(teacherService.update(id, teacherDTO), HttpStatus.OK) ;
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Integer id) {
        teacherService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/get/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDTO> findByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(teacherService.findByTeacherEmail(email), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{name}/{surname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDTO> findByNameAndSurname(@PathVariable("name") String name, @PathVariable("surname") String surname) {
        return new ResponseEntity<>(teacherService.findByTeacherNameAndTeacherSurname(name, surname), HttpStatus.OK) ;
    }

}
