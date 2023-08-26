package pro.sky.skyprohwSpringMockito.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprohwSpringMockito.exeption.EmployeeNotFoundException;
import pro.sky.skyprohwSpringMockito.model.Employees;


import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employees getEmployeeWithMaxSalary(Integer departmentId) {

        return employeeService.getAll().stream()
                .filter(employees -> employees.getDepartmentId() == departmentId)
                .max(Comparator.comparing(Employees::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с максимальной зарплатой не найден"));
    }

    public Employees getEmployeeWithMinSalary(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(employees -> employees.getDepartmentId() == departmentId)
                .min(Comparator.comparing(Employees::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с минимальной зарплатой не найден"));
    }

    public Map<Integer, List<Employees>> getEmployeesByDepartment(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> departmentId == null || e.getDepartmentId() == departmentId)
                .collect(groupingBy(Employees::getDepartmentId, toList()));
    }
}