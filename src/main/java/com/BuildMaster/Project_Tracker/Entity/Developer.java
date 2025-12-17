package com.BuildMaster.Project_Tracker.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(min = 5, max = 100)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    private String email;


    @CollectionTable(name = "developers_skills", joinColumns = @JoinColumn(name = "developers_id"))
    @Column(name = "skills")
    private String skill;

    @OneToMany(mappedBy = "developer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}
