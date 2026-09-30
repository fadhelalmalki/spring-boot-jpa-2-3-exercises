package org.fadhel.springbootjpa1exercise.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTOOut {

    private String name;
    private Integer age;
    private String email;
    private Double salary;
    private String area;
    private String street;
    private Integer buildingNumber;

}
