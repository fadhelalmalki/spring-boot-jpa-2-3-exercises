package org.fadhel.springbootjpa1exercise.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {

    @NotNull(message = "Teacher ID is required")
    private Integer teacherId;

    @NotBlank(message = "Area cannot be empty")
    private String area;

    @NotBlank(message = "Street cannot be empty")
    private String street;

    @NotBlank(message = "Building number cannot be empty")
    private String buildingNumber;
}
