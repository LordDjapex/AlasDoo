package com.alasdoo.developercourseassignment.repository;

import com.alasdoo.developercourseassignment.entity.DeveloperCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperCourseRepository extends JpaRepository<DeveloperCourse, Integer> {

    Optional<List<DeveloperCourse>> findByDeveloperCourseName(String developerCourseName);

    //changed  from native to jqpl
    @Query(value = "SELECT dc " +
        "FROM DeveloperCourse dc " +
        "JOIN Student s " +
        "WHERE s.id = :id")
    Optional<List<DeveloperCourse>> findDevCourseByStudentId(@Param("id") Integer id);

    //changed  from native to jqpl
    @Query(value = "SELECT dc " +
        "FROM DeveloperCourse dc " +
        "JOIN Teacher t " +
        "WHERE t.id = :id")
    Optional<List<DeveloperCourse>> findDevCourseByTeacherId(@Param("id") Integer id);

}
