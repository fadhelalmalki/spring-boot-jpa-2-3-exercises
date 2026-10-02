package org.fadhel.springbootjpa1exercise.service;

import lombok.RequiredArgsConstructor;
import org.fadhel.springbootjpa1exercise.DTO.*;
import org.fadhel.springbootjpa1exercise.api.ApiException;
import org.fadhel.springbootjpa1exercise.model.Address;
import org.fadhel.springbootjpa1exercise.model.Teacher;
import org.fadhel.springbootjpa1exercise.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::mapToTeacherResponse)
                .collect(Collectors.toList());
    }

    public TeacherResponseDTO addTeacher(TeacherRequestDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setName(dto.getName());
        teacher.setAge(dto.getAge());
        teacher.setEmail(dto.getEmail());
        teacher.setSalary(dto.getSalary());

        Teacher saved = teacherRepository.save(teacher);
        return mapToTeacherResponse(saved);
    }

    public TeacherResponseDTO updateTeacher(Integer id, TeacherRequestDTO dto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ApiException("Teacher not found with ID: " + id));

        teacher.setName(dto.getName());
        teacher.setAge(dto.getAge());
        teacher.setEmail(dto.getEmail());
        teacher.setSalary(dto.getSalary());

        Teacher updated = teacherRepository.save(teacher);
        return mapToTeacherResponse(updated);
    }

    public void deleteTeacher(Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ApiException("Teacher not found with ID: " + id));
        teacherRepository.delete(teacher);
    }

    public TeacherDetailsDTO getTeacherDetails(Integer teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ApiException("Teacher not found with ID: " + teacherId));

        TeacherDetailsDTO details = new TeacherDetailsDTO();
        details.setId(teacher.getId());
        details.setName(teacher.getName());
        details.setAge(teacher.getAge());
        details.setEmail(teacher.getEmail());
        details.setSalary(teacher.getSalary());

        if (teacher.getAddress() != null) {
            AddressResponseDTO addressDTO = new AddressResponseDTO(
                    teacher.getAddress().getId(),
                    teacher.getAddress().getArea(),
                    teacher.getAddress().getStreet(),
                    teacher.getAddress().getBuildingNumber()
            );
            details.setAddress(addressDTO);
        }

        if (teacher.getCourses() != null) {
            List<CourseResponseDTO> courseDTOs = teacher.getCourses().stream()
                    .map(c -> new CourseResponseDTO(c.getId(), c.getName(), teacher.getName()))
                    .collect(Collectors.toList());
            details.setCourses(courseDTOs);
        }

        return details;
    }

    private TeacherResponseDTO mapToTeacherResponse(Teacher teacher) {
        return new TeacherResponseDTO(
                teacher.getId(),
                teacher.getName(),
                teacher.getAge(),
                teacher.getEmail(),
                teacher.getSalary()
        );
    }
}
