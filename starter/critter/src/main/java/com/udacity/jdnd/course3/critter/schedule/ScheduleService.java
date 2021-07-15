package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.*;
import com.udacity.jdnd.course3.critter.user.*;
import com.udacity.jdnd.course3.critter.utils.DTOConverter;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ScheduleService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PetService petService;

    private static Logger logger = LoggerFactory.getLogger(Schedule.class);


    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        List<Pet> pets = getPets(scheduleDTO.getPetIds());
        List<Employee> employees = getEmployees(scheduleDTO.getEmployeeIds());
        Set<EmployeeSkill> skills = scheduleDTO.getActivities();

        Schedule schedule = new Schedule();
        schedule.setActivities(skills);
        schedule.setLocalDate(scheduleDTO.getDate());
        schedule.setEmployees(employees);
        schedule.setPets(pets);

        Schedule savedSchedule = scheduleRepository.save(schedule);
        logger.error("Saved Schedule details: ");
        logger.error(savedSchedule.toString());
        return DTOConverter.convertToScheduleDTO(savedSchedule);
    }

    private List<Pet> getPets(List<Long> petIds) {
        if(petIds==null) return null;
        return petIds.stream().map(id -> petRepository.findById(id).orElseThrow(PetNotFoundException::new)).collect(Collectors.toList());
    }

    private List<Employee> getEmployees(List<Long> iDs) {
        if(iDs==null) return null;
        return iDs.stream().map(id -> employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new)).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(DTOConverter::convertToScheduleDTO).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForEmployee(long employeeId) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
        List<Schedule> schedules=scheduleRepository.findAllByEmployees(employee);
        return schedules.stream()
                .map(DTOConverter::convertToScheduleDTO).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForCustomer(long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        System.out.println(customer.getName() + " " + customer.getPets());
        List<Pet> pets = customer.getPets();
        List<Schedule> schedules=scheduleRepository.findAllByPetsIn(pets);
        return schedules.stream()
                .map(DTOConverter::convertToScheduleDTO).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForPet(long petId) {
        Pet pet=petRepository.findById(petId).orElseThrow(PetNotFoundException::new);
        List<Schedule> schedules=scheduleRepository.findAllByPets(pet);
        return schedules.stream()
                .map(DTOConverter::convertToScheduleDTO).collect(Collectors.toList());
    }
}
