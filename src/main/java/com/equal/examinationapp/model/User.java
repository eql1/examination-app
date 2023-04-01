package com.equal.examinationapp.model;

import com.equal.examinationapp.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String personalCode;

    @Column(nullable = false, updatable = false)
    private String userCode;
}
