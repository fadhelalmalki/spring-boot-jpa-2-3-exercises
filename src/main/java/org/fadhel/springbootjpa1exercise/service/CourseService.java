package org.fadhel.springbootjpa1exercise.service;


import lombok.RequiredArgsConstructor;
import org.fadhel.springbootjpa1exercise.DTO.CourseRequestDTO;
import org.fadhel.springbootjpa1exercise.DTO.CourseResponseDTO;
import org.fadhel.springbootjpa1exercise.DTO.StudentResponseDTO;
import org.fadhel.springbootjpa1exercise.api.ApiException;
import org.fadhel.springbootjpa1exercise.model.Course;
import org.fadhel.springbootjpa1exercise.model.Teacher;
import org.fadhel.springbootjpa1exercise.repository.CourseRepository;
import org.fadhel.springbootjpa1exercise.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<CourseResponseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::mapToCourseResponse)
                .collect(Collectors.toList());
    }

    public CourseResponseDTO addCourse(CourseRequestDTO dto) {
        Course course = new Course();
        course.setName(dto.getName());

        if (dto.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new ApiException("Teacher not found with ID: " + dto.getTeacherId()));
            course.setTeacher(teacher);
        }

        Course saved = courseRepository.save(course);
        return mapToCourseResponse(saved);
    }

    public CourseResponseDTO updateCourse(Integer id, CourseRequestDTO dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ApiException("Course not found with ID: " + id));

        course.setName(dto.getName());

        if (dto.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new ApiException("Teacher not found with ID: " + dto.getTeacherId()));
            course.setTeacher(teacher);
        } else {
            course.setTeacher(null);
        }

        Course updated = courseRepository.save(course);
        return mapToCourseResponse(updated);
    }

    public void deleteCourse(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ApiException("Course not found with ID: " + id));
        courseRepository.delete(course);
    }

    public String getTeacherNameByCourseId(Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ApiException("Course not found with ID: " + courseId));

        if (course.getTeacher() == null) {
            return "No teacher assigned to this course";
        }
        return course.getTeacher().getName();
    }

    public List<StudentResponseDTO> getStudentsByCourseId(Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ApiException("Course not found with ID: " + courseId));

        return course.getStudents().stream()
                .map(s -> new StudentResponseDTO(s.getId(), s.getName(), s.getAge(), s.getMajor()))
                .collect(Collectors.toList());
    }

    private CourseResponseDTO mapToCourseResponse(Course course) {
        String teacherName = (course.getTeacher() != null) ? course.getTeacher().getName() : null;
        return new CourseResponseDTO(course.getId(), course.getName(), teacherName);
    }

}
