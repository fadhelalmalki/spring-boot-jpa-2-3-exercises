package org.fadhel.springbootjpa1exercise.DTO;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTOIn {

    @NotEmpty(message = "Teacher name cannot be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Teacher age cannot be null")
    @Min(value = 21, message = "Age must be at least 21")
    private Integer age;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be greater than zero")
    private Double salary;
}
