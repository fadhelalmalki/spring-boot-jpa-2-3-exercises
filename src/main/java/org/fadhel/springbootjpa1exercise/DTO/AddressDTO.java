package org.fadhel.springbootjpa1exercise.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotNull(message = "Teacher ID is required")
    private Integer teacher_id;

    @NotEmpty(message = "Area cannot be empty")
    private String area;

    @NotEmpty(message = "Street cannot be empty")
    private String street;

    @NotNull(message = "Building number cannot be null")
    @Positive(message = "Building number must be positive")
    private Integer buildingNumber;

}
