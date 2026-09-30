package org.fadhel.springbootjpa1exercise.service;

import lombok.RequiredArgsConstructor;
import org.fadhel.springbootjpa1exercise.DTO.TeacherDTOIn;
import org.fadhel.springbootjpa1exercise.DTO.TeacherDTOOut;
import org.fadhel.springbootjpa1exercise.api.ApiException;
import org.fadhel.springbootjpa1exercise.model.Address;
import org.fadhel.springbootjpa1exercise.model.Teacher;
import org.fadhel.springbootjpa1exercise.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<TeacherDTOOut> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDTOOut> teacherDTOOuts = new ArrayList<>();

        for (Teacher teacher : teachers) {
            Address address = teacher.getAddress();
            teacherDTOOuts.add(new TeacherDTOOut(
                    teacher.getName(),
                    teacher.getAge(),
                    teacher.getEmail(),
                    teacher.getSalary(),
                    address != null ? address.getArea() : null,
                    address != null ? address.getStreet() : null,
                    address != null ? address.getBuildingNumber() : null
            ));
        }

        return teacherDTOOuts;
    }

    public void addTeacher(TeacherDTOIn dto) {
        Teacher teacher = new Teacher(
                null,
                dto.getName(),
                dto.getAge(),
                dto.getEmail(),
                dto.getSalary(),
                null
        );
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer id, TeacherDTOIn dto) {
        Teacher existingTeacher = teacherRepository.findTeacherById(id);
        if (existingTeacher == null) {
            throw new ApiException("Teacher not found with ID: " + id);
        }

        existingTeacher.setName(dto.getName());
        existingTeacher.setAge(dto.getAge());
        existingTeacher.setEmail(dto.getEmail());
        existingTeacher.setSalary(dto.getSalary());

        teacherRepository.save(existingTeacher);
    }

    public void deleteTeacher(Integer id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) {
            throw new ApiException("Teacher not found with ID: " + id);
        }
        teacherRepository.delete(teacher);
    }

    public TeacherDTOOut getTeacherDetails(Integer id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) {
            throw new ApiException("Teacher not found with ID: " + id);
        }

        Address address = teacher.getAddress();

        return new TeacherDTOOut(
                teacher.getName(),
                teacher.getAge(),
                teacher.getEmail(),
                teacher.getSalary(),
                address != null ? address.getArea() : null,
                address != null ? address.getStreet() : null,
                address != null ? address.getBuildingNumber() : null
        );
    }
}
