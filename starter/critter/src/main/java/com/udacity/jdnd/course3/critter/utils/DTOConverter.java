package com.udacity.jdnd.course3.critter.utils;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

public class DTOConverter {
    public static Pet convertToPetEntity(PetDTO dto){
        Pet pet=new Pet();
        BeanUtils.copyProperties(dto,pet);
        return pet;
    }
    public static PetDTO convertToPetDTO(Pet pet){
        PetDTO petDTO=new PetDTO();
        BeanUtils.copyProperties(pet,petDTO);
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
        CustomerDTO dto=new CustomerDTO();
        BeanUtils.copyProperties(customer,dto);
        return dto;
    }

    public static Schedule convertToScheduleEntity(ScheduleDTO dto){
        Schedule schedule=new Schedule();
        BeanUtils.copyProperties(dto,schedule);
        return schedule;
    }

    public static ScheduleDTO convertToScheduleDTO(Schedule schedule){
        ScheduleDTO dto=new ScheduleDTO();
        BeanUtils.copyProperties(schedule,dto);
        return dto;
    }
}
