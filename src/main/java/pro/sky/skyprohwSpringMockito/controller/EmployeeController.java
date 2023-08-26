package pro.sky.skyprohwSpringMockito.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import pro.sky.skyprohwSpringMockito.model.Employees;
import pro.sky.skyprohwSpringMockito.service.EmployeeService;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @ExceptionHandler({HttpStatusCodeException.class})
    public String handleException(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode() + ". Error: " + e.getMessage();
    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employees add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam Integer departmentId) {
        return employeeService.add(firstName, lastName, salary, departmentId);
    }

    @GetMapping("/find")
    public Employees find(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam Integer departmentId) {
        return employeeService.find(firstName, lastName, salary, departmentId);
    }

    @GetMapping("/remove")
    public Employees remove(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam Integer departmentId) {
        return employeeService.remove(firstName, lastName, salary, departmentId);
    }

    @GetMapping
    public List<Employees> getAll() {
        return employeeService.getAll();
    }
}