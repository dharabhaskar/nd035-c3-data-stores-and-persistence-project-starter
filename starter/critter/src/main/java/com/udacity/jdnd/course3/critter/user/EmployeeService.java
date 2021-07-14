package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO saveEmployeeDetails(EmployeeDTO dto) {
        Employee employee = DTOConverter.convertToEmployeeEntity(dto);
        Employee savedEmployee = employeeRepository.save(employee);
        return DTOConverter.convertToEmployeeDTO(savedEmployee);
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> dtos=new ArrayList<>();
        for(Employee e:employees){
            dtos.add(DTOConverter.convertToEmployeeDTO(e));
        }
        return dtos;
    }
    public EmployeeDTO getEmployeeById(Long empId){
        Employee employee=employeeRepository.findById(empId).orElseThrow(EmployeeNotFoundException::new);
        return DTOConverter.convertToEmployeeDTO(employee);
    }

    public EmployeeDTO addEmployeeSchedule(Long employeeId, Set<DayOfWeek> schedules){
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
        employee.setDaysAvailable(schedules);
        employeeRepository.save(employee);
        return DTOConverter.convertToEmployeeDTO(employee);
    }

    public List<EmployeeDTO> getEmployeesForService(EmployeeRequestDTO employeeDTO) {
        DayOfWeek dayOfWeek=employeeDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> skills=employeeDTO.getSkills();
        List<Employee> employees=employeeRepository.findEmployeeByDaysAvailable(dayOfWeek)
                .stream().filter(employee -> employee.getSkills().containsAll(skills)).collect(Collectors.toList());

        List<EmployeeDTO> dtos=new ArrayList<>();
        for(Employee e:employees){
            dtos.add(DTOConverter.convertToEmployeeDTO(e));
        }
        return dtos;
    }
}
