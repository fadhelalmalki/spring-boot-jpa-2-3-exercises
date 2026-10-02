package org.fadhel.springbootjpa1exercise.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fadhel.springbootjpa1exercise.DTO.CourseRequestDTO;
import org.fadhel.springbootjpa1exercise.DTO.CourseResponseDTO;
import org.fadhel.springbootjpa1exercise.DTO.StudentResponseDTO;
import org.fadhel.springbootjpa1exercise.api.ApiResponse;
import org.fadhel.springbootjpa1exercise.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> addCourse(@Valid @RequestBody CourseRequestDTO dto) {
        return ResponseEntity.status(200).body(courseService.addCourse(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Integer id, @Valid @RequestBody CourseRequestDTO dto) {
        return ResponseEntity.status(200).body(courseService.updateCourse(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully"));
    }

    @GetMapping("/{courseId}/teacher-name")
    public ResponseEntity<ApiResponse> getTeacherNameByCourseId(@PathVariable Integer courseId) {
        String teacherName = courseService.getTeacherNameByCourseId(courseId);
        return ResponseEntity.status(200).body(new ApiResponse(teacherName));
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByCourseId(@PathVariable Integer courseId) {
        return ResponseEntity.status(200).body(courseService.getStudentsByCourseId(courseId));
    }

}
