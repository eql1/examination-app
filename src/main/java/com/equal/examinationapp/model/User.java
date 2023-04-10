package com.equal.examinationapp.model;

import com.equal.examinationapp.enumeration.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String institutionInformation;
    private String name;
    private String surname;
    @Column(name = "role", updatable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(nullable = false)
    private String personalCode;

    @Column(nullable = false, updatable = false)
    private String userCode;

    // List of available exam IDs
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_exam",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<Exam> availableExams;

}
