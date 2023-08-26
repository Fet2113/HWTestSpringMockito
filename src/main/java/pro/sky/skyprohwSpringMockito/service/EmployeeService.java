package pro.sky.skyprohwSpringMockito.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprohwSpringMockito.exeption.EmployeeAlreadyAddedException;
import pro.sky.skyprohwSpringMockito.exeption.EmployeeNotFoundException;
import pro.sky.skyprohwSpringMockito.exeption.EmployeeStorageIsFullException;
import pro.sky.skyprohwSpringMockito.model.Employees;


import java.util.*;


@Service
public class EmployeeService {

    private final List<Employees> employees = new ArrayList<>();

    private final static int MAX_SIZE = 1;

    public Employees add(String firstName, String lastName, double salary, int departmentId) {

        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employees newEmployee = new Employees(firstName, lastName, salary, departmentId);

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }

        employees.add(newEmployee);
        return newEmployee;
    }

    public Employees find(String firstName, String lastName, double salary, int departmentId) {
        Employees employeeForFound = new Employees(firstName, lastName, salary, departmentId);
        for (Employees e : employees) {
            if (e.equals(employeeForFound)) {
                return e;
            }
        }

        throw new EmployeeNotFoundException("Такого сотрудника нет");
    }

    public Employees remove(String firstName, String lastName, double salary, int departmentId) {
        Employees employeeForRemove = new Employees(firstName, lastName, salary, departmentId);

        boolean removeResult = employees.remove(employeeForRemove);
        if (removeResult) {
            return employeeForRemove;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не удален - не был найден в базе");
        }
    }

    public List<Employees> getAll() {
        return employees;
    }
}
