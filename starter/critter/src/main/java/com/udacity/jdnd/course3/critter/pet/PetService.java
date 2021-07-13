package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public PetDTO savePet(PetDTO petDTO) {
        Pet pet = DTOConverter.convertToPetEntity(petDTO);
        Pet savedPet=petRepository.save(pet);
        return DTOConverter.convertToPetDTO(savedPet);
    }

    public PetDTO findPet(Long petId){
        Pet pet= petRepository.findById(petId)
                .orElseThrow(PetNotFoundException::new);
        return DTOConverter.convertToPetDTO(pet);
    }

    public List<PetDTO> getAllPets() {
        Iterable<Pet> pets = petRepository.findAll();
        List<PetDTO> petDTOS=new ArrayList<>();
        for(Pet p:pets){
            petDTOS.add(DTOConverter.convertToPetDTO(p));
        }
        return petDTOS;
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        Customer customer=customerRepository.findById(ownerId).orElseThrow(CustomerNotFoundException::new);
        Iterable<Pet> pets=customer.getPets();
        List<PetDTO> petDTOS=new ArrayList<>();
        for(Pet p:pets){
            petDTOS.add(DTOConverter.convertToPetDTO(p));
        }
        return petDTOS;
    }
}