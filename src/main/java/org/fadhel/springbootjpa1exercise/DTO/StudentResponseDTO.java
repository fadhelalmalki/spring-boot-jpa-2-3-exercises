package org.fadhel.springbootjpa1exercise.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

    private Integer id;
    private String name;
    private Integer age;
    private String major;
}
