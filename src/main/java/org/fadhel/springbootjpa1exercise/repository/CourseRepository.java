package org.fadhel.springbootjpa1exercise.repository;


import org.fadhel.springbootjpa1exercise.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
