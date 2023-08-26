package pro.sky.skyprohwSpringMockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.skyprohwSpringMockito.exeption.EmployeeNotFoundException;
import pro.sky.skyprohwSpringMockito.model.Employees;
import pro.sky.skyprohwSpringMockito.service.DepartmentService;
import pro.sky.skyprohwSpringMockito.service.EmployeeService;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pro.sky.skyprohwSpringMockito.generator.EmployeeGenerator.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void getEmployeeWithMaxSalary_success() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employees employeeWithMaxSalary = getPetrFirstDep();
        Employees petrFirstDep = getPetrFirstDep();
        Employees ivanFirstDep = getIvanFirstDep();
        Employees ilyaSecondDep = getIlyaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );

        //Начало теста
        Employees actualEmployeeWithMaxSalary = departmentService.getEmployeeWithMaxSalary(depId);
        assertEquals(employeeWithMaxSalary, actualEmployeeWithMaxSalary);
        assertEquals(depId, actualEmployeeWithMaxSalary.getDepartmentId());
        assertTrue(petrFirstDep.getSalary() > ivanFirstDep.getSalary());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeeWithMaxSalary_withEmployeeNotFoundException() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.singletonList(
                getIlyaSecondDep()
        ));
        String expectedMessage = "404 Сотрудник с максимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMaxSalary(depId));
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeeWithMinSalary_success() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employees employeeWithMinSalary = getIvanFirstDep();

        Employees petrFirstDep = getPetrFirstDep();
        Employees ivanFirstDep = getIvanFirstDep();
        Employees ilyaSecondDep = getIlyaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );

        //Начало теста
        Employees actualEmployeeWithMinSalary = departmentService.getEmployeeWithMinSalary(depId);
        assertEquals(employeeWithMinSalary, actualEmployeeWithMinSalary);
        assertEquals(depId, actualEmployeeWithMinSalary.getDepartmentId());
        assertTrue(petrFirstDep.getSalary() > ivanFirstDep.getSalary());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeeWithMinSalary_EmployeeNotFoundException() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.singletonList(
                getIlyaSecondDep()
        ));
        String expectedMessage = "404 Сотрудник с минимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMinSalary(depId));
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeesByDepartment_success() {
        //Подготовка входных данных
        Integer depId = null;

        //Подготовка ожидаемого результата
        Employees petrFirstDep = getPetrFirstDep();
        Employees ivanFirstDep = getIvanFirstDep();
        Employees ilyaSecondDep = getIlyaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );
        Map<Integer, List<Employees>> expectedEmployeeMap = new HashMap<>();
        expectedEmployeeMap.put(FIRST_DEPARTMENT_ID, List.of(petrFirstDep, ivanFirstDep));
        expectedEmployeeMap.put(SECOND_DEPARTMENT_ID, Collections.singletonList(ilyaSecondDep));

        //Начало теста
        Map<Integer, List<Employees>> actualEmployeeMap = departmentService.getEmployeesByDepartment(depId);
        assertEquals(expectedEmployeeMap, actualEmployeeMap);
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeesByDepartment_whenDepIdIsNull() {
        //Подготовка входных данных
        Integer depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employees petrFirstDep = getPetrFirstDep();
        Employees ivanFirstDep = getIvanFirstDep();
        Employees ilyaSecondDep = getIlyaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );
        Map<Integer, List<Employees>> expectedEmployeeMap = new HashMap<>();
        expectedEmployeeMap.put(FIRST_DEPARTMENT_ID, List.of(petrFirstDep, ivanFirstDep));

        //Начало теста
        Map<Integer, List<Employees>> actualEmployeeMap = departmentService.getEmployeesByDepartment(depId);
        assertEquals(expectedEmployeeMap, actualEmployeeMap);
        verify(employeeService).getAll();
    }
}
