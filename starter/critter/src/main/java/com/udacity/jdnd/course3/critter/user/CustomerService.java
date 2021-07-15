package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetNotFoundException;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PetRepository petRepository;

    @Transactional
    public CustomerDTO saveCustomer(CustomerDTO customerDTO){
        Customer customer=customerRepository.save(DTOConverter.convertToCustomerEntity(customerDTO));
        for(Long petId:customerDTO.getPetIds()){
            Pet pet=petRepository.findById(petId).orElseThrow(PetNotFoundException::new);
            addPetToCustomer(customer,pet);
        }
        return DTOConverter.convertToCustomerDTO(customer);
    }

    public CustomerDTO getCustomerById(Long customerId){
        Customer customer=customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        return DTOConverter.convertToCustomerDTO(customer);
    }

    public List<CustomerDTO> getAllCustomers(){
        Iterable<Customer> iterable=customerRepository.findAll();
        List<CustomerDTO> customerList=new ArrayList<>();
        for(Customer customer:iterable){
            customerList.add(DTOConverter.convertToCustomerDTO(customer));
        }
        return customerList;
    }

    @Transactional
    public void addPetToCustomer(Customer customer, Pet pet){
        List<Pet> petList=customer.getPets();
        if(petList==null) petList=new ArrayList<>();
        pet.setCustomer(customer);
        petRepository.save(pet);
        petList.add(pet);
        customer.setPets(petList);
        customerRepository.save(customer);
    }

    public CustomerDTO getOwnerByPet(long petId) {
        Pet pet=petRepository.findById(petId).orElseThrow(PetNotFoundException::new);
        return DTOConverter.convertToCustomerDTO(pet.getCustomer());
    }
}
