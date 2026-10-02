package org.fadhel.springbootjpa1exercise.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDetailsDTO {
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private Double salary;
    private AddressResponseDTO address;
    private List<CourseResponseDTO> courses;
}
