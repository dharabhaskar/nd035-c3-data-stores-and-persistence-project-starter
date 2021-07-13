package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO saveCustomer(CustomerDTO customerDTO){
        Customer customer=customerRepository.save(DTOConverter.convertToCustomerEntity(customerDTO));
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
}
