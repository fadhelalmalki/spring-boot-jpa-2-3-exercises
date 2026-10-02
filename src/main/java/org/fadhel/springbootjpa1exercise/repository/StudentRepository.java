package org.fadhel.springbootjpa1exercise.repository;

import org.fadhel.springbootjpa1exercise.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
