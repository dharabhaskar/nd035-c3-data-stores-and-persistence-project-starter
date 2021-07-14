package com.udacity.jdnd.course3.critter.user;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds;

    public List<Long> getPetIds(){
        if(petIds==null){
            petIds=new ArrayList<>();
        }
        return petIds;
    }
}
