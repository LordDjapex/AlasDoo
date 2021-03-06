package com.alasdoo.developercourseassignment.service.impl;

import com.alasdoo.developercourseassignment.dto.StudentDTO;
import com.alasdoo.developercourseassignment.entity.Student;
import com.alasdoo.developercourseassignment.mapper.StudentMapper;
import com.alasdoo.developercourseassignment.repository.StudentRepository;
import com.alasdoo.developercourseassignment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public StudentDTO findOne(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new IllegalArgumentException
                ("Student with the following id = " + id + " is not found.");
        }
        return studentMapper.transformToDTO(student.get());
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(i -> studentMapper.transformToDTO(i)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentDTO save(StudentDTO studentDTO) {
        Student student = studentMapper.transformToEntity(studentDTO);
        return studentMapper.transformToDTO(studentRepository.save(student));
    }

    @Override
    @Transactional
    public void remove(Integer id) throws IllegalArgumentException {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new IllegalArgumentException
                ("Student with the following id = " + id + " is not found.");
        }
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public StudentDTO update(Integer id, StudentDTO studentDTO) {
        Optional<Student> oldStudent = studentRepository.findById(id);
        if (!oldStudent.isPresent()) {
            throw new IllegalArgumentException
                ("Student with the following id = " + id + " is not found.");
        }

        /*i don't use mapper transform to entity here because it would take the id from teacherDTO which, depending on request, might me different from
        one that we've sent

        It is be a good practice to put mapping of properties from dto to entity in seperate method, but since we're mapping only 3 properties, i didn't
        feel as if it were necessary.

        private setStudentProperties(Teacher student, studentDTO studentDTO) {
            student.setStudentEmail(studentDTO.getSetEmail())
            ...etc
        }
         */
        oldStudent.get().setName(studentDTO.getName());
        oldStudent.get().setSurname(studentDTO.getSurname());
        oldStudent.get().setAccountName(studentDTO.getAccountName());
        oldStudent.get().setPassword(studentDTO.getPassword());
        oldStudent.get().setEmail(studentDTO.getEmail());
        oldStudent.get().setBankCardNumber(studentDTO.getBankCardNumber());

        studentRepository.save(oldStudent.get());
        return studentMapper.transformToDTO(oldStudent.get());
    }

    @Override
    public StudentDTO findByAccountName(String accountName) {
        Optional<Student> student = studentRepository.findByAccountName(accountName);
        if (!student.isPresent()) {
            throw new IllegalArgumentException
                ("Student with the following account name = " + accountName + " is not found.");
        }
        return studentMapper.transformToDTO(student.get());
    }

    @Override
    public StudentDTO findByAccountNameAndPassword(String accountName, String password) {
        Optional<Student> student = studentRepository.findByAccountNameAndPassword(accountName, password);
        if (!student.isPresent()) {
            throw new IllegalArgumentException
                ("Student with the provided account name and password combination is not found.");
        }
        return studentMapper.transformToDTO(student.get());
    }
}
