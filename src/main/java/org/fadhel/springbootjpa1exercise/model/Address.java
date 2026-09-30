package org.fadhel.springbootjpa1exercise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Integer buildingNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Teacher teacher;

}
