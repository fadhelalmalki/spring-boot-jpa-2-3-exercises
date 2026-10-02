package org.fadhel.springbootjpa1exercise.DTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {

    @NotBlank(message = "Student name cannot be empty")
    private String name;

    @NotNull(message = "Age cannot be empty")
    @Min(value = 5, message = "Age must be at least 5")
    private Integer age;

    @NotBlank(message = "Major cannot be empty")
    private String major;
}
