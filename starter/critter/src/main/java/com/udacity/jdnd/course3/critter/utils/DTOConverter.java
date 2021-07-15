package com.udacity.jdnd.course3.critter.utils;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {
    public static Pet convertToPetEntity(PetDTO dto){
        Pet pet=new Pet();
        BeanUtils.copyProperties(dto,pet);
        return pet;
    }
    public static PetDTO convertToPetDTO(Pet pet){
        PetDTO petDTO=new PetDTO();
        BeanUtils.copyProperties(pet,petDTO);
        if(pet.getCustomer()!=null) {
            petDTO.setOwnerId(pet.getCustomer().getId());
        }
        return petDTO;
    }

    public static Employee convertToEmployeeEntity(EmployeeDTO dto){
        Employee employee=new Employee();
        BeanUtils.copyProperties(dto,employee);
        return employee;
    }

    public static EmployeeDTO convertToEmployeeDTO(Employee employee){
        EmployeeDTO dto=new EmployeeDTO();
        BeanUtils.copyProperties(employee,dto);
        return dto;
    }

    public static Customer convertToCustomerEntity(CustomerDTO dto){
        Customer customer=new Customer();
        BeanUtils.copyProperties(dto,customer);
        return  customer;
    }

    public static CustomerDTO convertToCustomerDTO(Customer customer){
        /*CustomerDTO dto=new CustomerDTO();
        BeanUtils.copyProperties(customer,dto);
        return dto;*/
        CustomerDTO savedCustomerDto = new CustomerDTO();
        savedCustomerDto.setId(customer.getId());
        savedCustomerDto.setName(customer.getName());
        savedCustomerDto.setPhoneNumber(customer.getPhoneNumber());
        List<Pet> pets =  customer.getPets();
        List<Long> petsIds = new ArrayList<>();
        if (pets!= null) {
            pets.forEach((pet) -> petsIds.add(pet.getId()));
        }

        savedCustomerDto.setPetIds(petsIds);
        return savedCustomerDto;
    }

    public static Schedule convertToScheduleEntity(ScheduleDTO dto){
        Schedule schedule=new Schedule();
        BeanUtils.copyProperties(dto,schedule);
        return schedule;
    }

    public static ScheduleDTO convertToScheduleDTO(Schedule schedule){
        ScheduleDTO dto=new ScheduleDTO();
        dto.setActivities(schedule.getActivities());
        dto.setDate(schedule.getLocalDate());
        dto.setEmployeeIds(schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        dto .setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        return dto;
    }
}
