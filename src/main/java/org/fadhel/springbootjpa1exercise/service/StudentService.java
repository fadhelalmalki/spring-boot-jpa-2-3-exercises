package org.fadhel.springbootjpa1exercise.service;

import lombok.RequiredArgsConstructor;
import org.fadhel.springbootjpa1exercise.DTO.StudentRequestDTO;
import org.fadhel.springbootjpa1exercise.DTO.StudentResponseDTO;
import org.fadhel.springbootjpa1exercise.api.ApiException;
import org.fadhel.springbootjpa1exercise.model.Course;
import org.fadhel.springbootjpa1exercise.model.Student;
import org.fadhel.springbootjpa1exercise.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToStudentResponse)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO addStudent(StudentRequestDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setMajor(dto.getMajor());

        Student saved = studentRepository.save(student);
        return mapToStudentResponse(saved);
    }

    public StudentResponseDTO updateStudent(Integer id, StudentRequestDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Student not found with ID: " + id));

        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setMajor(dto.getMajor());

        Student updated = studentRepository.save(student);
        return mapToStudentResponse(updated);
    }

    public void deleteStudent(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Student not found with ID: " + id));
        studentRepository.delete(student);
    }

    @Transactional
    public StudentResponseDTO changeStudentMajorAndDropCourses(Integer studentId, String newMajor) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ApiException("Student not found with ID: " + studentId));

        student.setMajor(newMajor);

        // Remove student from all enrolled courses
        if (student.getCourses() != null) {
            for (Course course : new ArrayList<>(student.getCourses())) {
                course.getStudents().remove(student);
            }
            student.getCourses().clear();
        }

        Student updated = studentRepository.save(student);
        return mapToStudentResponse(updated);
    }

    private StudentResponseDTO mapToStudentResponse(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getMajor()
        );
    }
}
