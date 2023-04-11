package com.equal.examinationapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Integer durationMinutes;
    @JsonIgnore
    @ManyToMany(mappedBy = "availableExams", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<User> usersWithAccess;

}
