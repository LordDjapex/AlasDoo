package com.alasdoo.developercourseassignment.service.impl;

import com.alasdoo.developercourseassignment.dto.TeacherDTO;
import com.alasdoo.developercourseassignment.entity.Teacher;
import com.alasdoo.developercourseassignment.mapper.TeacherMapper;
import com.alasdoo.developercourseassignment.repository.TeacherRepository;
import com.alasdoo.developercourseassignment.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public TeacherDTO findOne(Integer id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (!teacher.isPresent()) {
            throw new IllegalArgumentException
                    ("Teacher with the following id = " + id + " is not found.");
        }
        return teacherMapper.transformToDTO(teacher.get());
    }

    @Override
    public List<TeacherDTO> findAll() {
        return teacherRepository.findAll().stream().map(i -> teacherMapper.transformToDTO(i)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TeacherDTO save(TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.transformToEntity(teacherDTO);
        return teacherMapper.transformToDTO(teacherRepository.save(teacher));
    }

    @Override
    @Transactional
    public void remove(Integer id) throws IllegalArgumentException {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (!teacher.isPresent()) {
            throw new IllegalArgumentException
                    ("Teacher with the following id = " + id + " is not found.");
        }
        teacherRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TeacherDTO update(Integer id, TeacherDTO teacherDTO) {
        Optional<Teacher> oldTeacher = teacherRepository.findById(id);
        if (!oldTeacher.isPresent()) {
            throw new IllegalArgumentException
                    ("Teacher with the following id = " + id + " is not found.");
        }
        /*i don't use mapper transform to entity here because it would take the id from teacherDTO which, depending on request, might me different from
        one that we've sent

        It is be a good practice to put mapping of properties from dto to entity in seperate method, but since we're mapping only 3 properties, i didn't
        feel as if it were necessary.

        private setTeacherProperties(Teacher teacher, TeacherDTO teacherDTO) {
            teacher.setTeacherEmail(teacherDTO.getTeacherEmail())
            ...etc
        }
         */
        oldTeacher.get().setTeacherEmail(teacherDTO.getTeacherEmail());
        oldTeacher.get().setTeacherName(teacherDTO.getTeacherName());
        oldTeacher.get().setTeacherSurname(teacherDTO.getTeacherSurname());

        teacherRepository.save(oldTeacher.get());
        return teacherMapper.transformToDTO(oldTeacher.get());
    }

    @Override
    public TeacherDTO findByTeacherNameAndTeacherSurname(String name, String surname) {
        Optional<Teacher> teacher = teacherRepository.findByTeacherNameAndTeacherSurname(name, surname);
        if (!teacher.isPresent()) {
            throw new IllegalArgumentException
                    ("Teacher with the following name = " + name + "and surname = " + surname +  " is not found.");
        }
        return teacherMapper.transformToDTO(teacher.get());
    }

    @Override
    public TeacherDTO findByTeacherEmail(String email) {
        Optional<Teacher> teacher = teacherRepository.findByTeacherEmail(email);
        if (!teacher.isPresent()) {
            throw new IllegalArgumentException
                    ("Student with the following email = " + email + " is not found.");
        }
        return teacherMapper.transformToDTO(teacher.get());
    }
}
