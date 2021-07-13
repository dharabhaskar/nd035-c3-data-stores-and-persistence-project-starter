package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Nationalized
    @Column(length = 100)
    private PetType type;

    private String name;
    private LocalDate birthDate;
    private String notes;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToMany
    private List<Schedule> schedules;
}
