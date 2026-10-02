package org.fadhel.springbootjpa1exercise.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    // Course * ---- 1 Teacher
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Teacher teacher;

    // Course * ---- * Student
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}
