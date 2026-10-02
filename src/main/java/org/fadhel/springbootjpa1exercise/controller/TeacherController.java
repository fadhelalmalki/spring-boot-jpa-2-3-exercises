package org.fadhel.springbootjpa1exercise.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fadhel.springbootjpa1exercise.DTO.TeacherDetailsDTO;
import org.fadhel.springbootjpa1exercise.DTO.TeacherRequestDTO;
import org.fadhel.springbootjpa1exercise.DTO.TeacherResponseDTO;
import org.fadhel.springbootjpa1exercise.api.ApiResponse;
import org.fadhel.springbootjpa1exercise.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherResponseDTO>> getAllTeachers() {
        return ResponseEntity.status(200).body(teacherService.getAllTeachers());
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDTO> addTeacher(@Valid @RequestBody TeacherRequestDTO dto) {
        return ResponseEntity.status(200).body(teacherService.addTeacher(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@PathVariable Integer id, @Valid @RequestBody TeacherRequestDTO dto) {
        return ResponseEntity.status(200).body(teacherService.updateTeacher(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher deleted successfully"));
    }

    @GetMapping("/{teacherId}/details")
    public ResponseEntity<TeacherDetailsDTO> getTeacherDetails(@PathVariable Integer teacherId) {
        return ResponseEntity.status(200).body(teacherService.getTeacherDetails(teacherId));
    }
}
