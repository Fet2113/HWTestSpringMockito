package pro.sky.skyprohwSpringMockito;

import org.junit.jupiter.api.Test;
import pro.sky.skyprohwSpringMockito.exeption.EmployeeNotFoundException;
import pro.sky.skyprohwSpringMockito.exeption.EmployeeStorageIsFullException;
import pro.sky.skyprohwSpringMockito.generator.EmployeeGenerator;
import pro.sky.skyprohwSpringMockito.model.Employees;
import pro.sky.skyprohwSpringMockito.service.EmployeeService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.sky.skyprohwSpringMockito.generator.EmployeeGenerator.*;

class EmployeesServiceTest {

    private final EmployeeService employeeService = new EmployeeService();

    @Test
    void add_success() {
        //Подготовка входных данных
        String firstName = IVAN_FIRST_NAME;
        String lastName = IVAN_LAST_NAME;
        double salary = IVAN_SALARY;
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employees expectedEmployee = getIvanFirstDep();

        //Начало теста
        Employees addedEmployee = employeeService.add(firstName, lastName, salary, depId);
        assertEquals(expectedEmployee, addedEmployee);
    }

    @Test
    void add_withEmployeeStorageIsFullException() {
        //Подготовка входных данных
        String firstName = PETR_FIRST_NAME;
        String lastName = PETR_LAST_NAME;
        double salary = PETR_SALARY;
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        employeeService.add(IVAN_FIRST_NAME, IVAN_LAST_NAME, IVAN_SALARY, depId);
        String expectedMessage = "400 Массив сотрудников переполнен";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeStorageIsFullException.class,
                () -> employeeService.add(firstName, lastName, salary, depId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void find_success() {
        //Подготовка входных данных
        String firstName = IVAN_FIRST_NAME;
        String lastName = IVAN_LAST_NAME;
        double salary = IVAN_SALARY;
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        employeeService.add(IVAN_FIRST_NAME, IVAN_LAST_NAME, IVAN_SALARY, depId);
        Employees expectedEmployee = getIvanFirstDep();

        //Начало теста
        Employees actualEmployee = employeeService.find(firstName, lastName, salary, depId);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void find_withEmployeeNotFoundException() {
        //Подготовка входных данных
        String firstName = IVAN_FIRST_NAME;
        String lastName = IVAN_LAST_NAME;
        double salary = IVAN_SALARY;
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        String expectedMessage = "404 Такого сотрудника нет";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.find(firstName, lastName, salary, depId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }



        @Test
        void remove_withEmployeeNotFoundException () {
            //Подготовка входных данных
            String firstName = IVAN_FIRST_NAME;
            String lastName = IVAN_LAST_NAME;
            double salary = IVAN_SALARY;
            int depId = FIRST_DEPARTMENT_ID;

            //Подготовка ожидаемого результата
            String expectedMessage = "404 Такого сотрудника нет";
            //Начало теста
            Exception exception = assertThrows(
                    EmployeeNotFoundException.class,
                    () -> employeeService.find(firstName, lastName, salary, depId)
            );
            assertEquals(expectedMessage, exception.getMessage());
        }
    @Test
    void remove_success() {
        //Подготовка входных данных
        String firstName = IVAN_FIRST_NAME;
        String lastName = IVAN_LAST_NAME;
        double salary = IVAN_SALARY;
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        employeeService.add(IVAN_FIRST_NAME, IVAN_LAST_NAME, IVAN_SALARY, depId);
        Employees expectedEmployee = getIvanFirstDep();

        //Начало теста
        Employees actualEmployee = employeeService.remove(firstName, lastName, salary, depId);
        assertEquals(expectedEmployee, actualEmployee);

    }
}