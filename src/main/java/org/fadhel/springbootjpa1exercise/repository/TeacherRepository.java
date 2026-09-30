package org.fadhel.springbootjpa1exercise.repository;

import org.fadhel.springbootjpa1exercise.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher findTeacherById(Integer id);
}
