package pro.sky.skyprohwSpringMockito.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import pro.sky.skyprohwSpringMockito.model.Employees;
import pro.sky.skyprohwSpringMockito.service.DepartmentService;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @ExceptionHandler({HttpStatusCodeException.class})
    public String handleException(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode() + ". Error: " + e.getMessage();
    }

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employees getEmployeeWithMaxSalary(@RequestParam Integer departmentId) {
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employees getEmployeeWithMinSalary(@RequestParam Integer departmentId) {
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employees>> getEmployeesByDepartment(@RequestParam(required = false) Integer departmentId) {
        return departmentService.getEmployeesByDepartment(departmentId);
    }
}