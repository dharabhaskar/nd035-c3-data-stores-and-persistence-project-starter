package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    private List<Employee> employees;

    @ManyToMany
    private List<Pet> pets;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    private LocalDate localDate;
}