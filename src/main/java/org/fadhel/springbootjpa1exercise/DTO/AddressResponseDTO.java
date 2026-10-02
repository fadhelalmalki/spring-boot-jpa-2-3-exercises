package org.fadhel.springbootjpa1exercise.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDTO {

    private Integer teacherId;
    private String area;
    private String street;
    private String buildingNumber;

}
