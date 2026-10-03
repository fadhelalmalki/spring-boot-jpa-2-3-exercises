package org.fadhel.springbootjpa1exercise.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fadhel.springbootjpa1exercise.DTO.StudentRequestDTO;
import org.fadhel.springbootjpa1exercise.DTO.StudentResponseDTO;
import org.fadhel.springbootjpa1exercise.api.ApiResponse;
import org.fadhel.springbootjpa1exercise.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> addStudent(@Valid @RequestBody StudentRequestDTO dto) {
        return ResponseEntity.status(200).body(studentService.addStudent(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Integer id, @Valid @RequestBody StudentRequestDTO dto) {
        return ResponseEntity.status(200).body(studentService.updateStudent(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
    }

    @PutMapping("/{studentId}/major/{major}")
    public ResponseEntity<StudentResponseDTO> changeMajorAndDropCourses(
            @PathVariable Integer studentId,
            @PathVariable String major) {
        return ResponseEntity.status(200).body(studentService.changeStudentMajorAndDropCourses(studentId, major));
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<ApiResponse> assignStudentToCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        studentService.assignStudentToCourse(studentId, courseId);
        return ResponseEntity.status(200).body(new ApiResponse("Student assigned to course successfully"));
    }
}
