package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Nationalized
    private String name;

    private String phoneNumber;
    private String notes;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Pet> pets;

    public void addPet(Pet pet){
        if(this.pets==null){
            pets=new ArrayList<>();
        }
        pets.add(pet);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", notes='" + notes + '\'' +
                ", pets=" + pets +
                '}';
    }
}
