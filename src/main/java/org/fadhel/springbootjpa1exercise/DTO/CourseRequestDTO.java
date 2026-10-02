package org.fadhel.springbootjpa1exercise.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {

    @NotBlank(message = "Course name cannot be empty")
    private String name;

    @NotNull(message = "Teacher ID is required")
    private Integer teacherId;

}
