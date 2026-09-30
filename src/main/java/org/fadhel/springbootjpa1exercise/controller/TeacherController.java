package org.fadhel.springbootjpa1exercise.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fadhel.springbootjpa1exercise.DTO.TeacherDTOIn;
import org.fadhel.springbootjpa1exercise.DTO.TeacherDTOOut;
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

    @GetMapping("/get")
    public ResponseEntity<List<TeacherDTOOut>> getAllTeachers() {
        return ResponseEntity.status(200).body(teacherService.getAllTeachers());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTeacher(@Valid @RequestBody TeacherDTOIn teacherDTOIn) {
        teacherService.addTeacher(teacherDTOIn);
        return ResponseEntity.status(201).body(new ApiResponse("Teacher added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateTeacher(@PathVariable Integer id, @Valid @RequestBody TeacherDTOIn teacherDTOIn) {
        teacherService.updateTeacher(id, teacherDTOIn);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher deleted successfully"));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<TeacherDTOOut> getTeacherDetails(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(teacherService.getTeacherDetails(id));
    }

}
