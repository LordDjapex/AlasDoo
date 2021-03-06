package com.alasdoo.developercourseassignment.controller;

import com.alasdoo.developercourseassignment.dto.StudentDTO;
import com.alasdoo.developercourseassignment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/getStudent/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> selectStudent(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(studentService.findOne(id), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/addStudent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO) {
            return new ResponseEntity<>(studentService.save(studentDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Integer id, @RequestBody StudentDTO studentDTO) {
        return new ResponseEntity<>(studentService.update(id, studentDTO), HttpStatus.OK) ;
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Integer id) {
        studentService.remove(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping(value = "/get/{accountName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> findByAccountName(@PathVariable("accountName") String accountName) {
        return new ResponseEntity<>(studentService.findByAccountName(accountName), HttpStatus.OK) ;
    }

    @GetMapping(value = "/get/{accountName}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> findByAccountName(@PathVariable("accountName") String accountName, @PathVariable("password") String password) {
        return new ResponseEntity<>(studentService.findByAccountNameAndPassword(accountName, password), HttpStatus.OK);
    }
}
