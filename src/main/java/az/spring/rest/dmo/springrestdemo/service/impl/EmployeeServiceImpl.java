package az.spring.rest.dmo.springrestdemo.service.impl;
import az.spring.rest.dmo.springrestdemo.enums.ErrorCodeEnum;
import az.spring.rest.dmo.springrestdemo.exception.CustomNotFoundException;
import az.spring.rest.dmo.springrestdemo.model.Employee;
import az.spring.rest.dmo.springrestdemo.repostiory.EmployeeRepository;
import az.spring.rest.dmo.springrestdemo.rest.model.dto.EmployeeDto;
import az.spring.rest.dmo.springrestdemo.rest.model.response.EmployeeResponse;
import az.spring.rest.dmo.springrestdemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse getAllEmployees() {
        List<EmployeeDto> employeeDtos = employeeRepository.findAll()
                .stream()
                .map(employee -> convertToDto(employee))
                .collect(Collectors.toList());
        return EmployeeResponse.builder()
                .employees(employeeDtos)
                .build();

    }

    @Override
    public EmployeeDto getEmployee(long id) {
        return employeeRepository.findById(id)
                .map(employee -> convertToDto(employee))
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND_EMPLOYEE));
    }

    @Override
    public EmployeeResponse getEmployeeByNameAndSurname(String name, String surname) {
        List<EmployeeDto> employees = employeeRepository.findByNameAndSurname(name, surname)
                .stream()
                .map(employee -> convertToDto(employee))
                .collect(Collectors.toList());
        return makeEmployeeResponse(employees);

    }

    @Override
    public void insert(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        employeeRepository.save(employee);
    }

    @Override
    public void update(EmployeeDto employeeDto, long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND_EMPLOYEE));
        employee.setName(employeeDto.getName());
        employee.setSurname(employee.getSurname());
        employee.setAge(employee.getAge());
        employee.setSalary(employeeDto.getSalary());
        employeeRepository.save(employee);
    }

    @Override
    public void updateSome(EmployeeDto employeeDto, long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND_EMPLOYEE));
        if (employeeDto.getName() != null) {
            employee.setName(employeeDto.getName());
        }
        if (employeeDto.getSurname() != null) {
            employee.setSurname(employeeDto.getSurname());
        }
        if (employeeDto.getAge() != 0) {
            employee.setAge(employeeDto.getAge());
        }
        if (employeeDto.getSalary() != 0) {
            employee.setSalary(employeeDto.getSalary());
        }

        employeeRepository.save(employee);
    }

    @Override
    public void delete(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND_EMPLOYEE));
        employeeRepository.delete(employee);
    }

    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto);
        return employeeDto;
    }

    private EmployeeResponse makeEmployeeResponse(List<EmployeeDto> employeeDtoList) {
        return EmployeeResponse.builder()
                .employees(employeeDtoList)
                .build();
    }

}
