package pro.sky.skyprohwSpringMockito.generator;

import pro.sky.skyprohwSpringMockito.model.Employees;


public class EmployeeGenerator {
    public static final String IVAN_FIRST_NAME = "Ivan";
    public static final String IVAN_LAST_NAME = "Ivanov";
    public static final double IVAN_SALARY = 790;

    public static final String PETR_FIRST_NAME = "Petr";
    public static final String PETR_LAST_NAME = "Petrov";
    public static final double PETR_SALARY = 819;

    public static final String ILYA_FIRST_NAME = "Ilya";
    public static final String ILYA_LAST_NAME = "Ilyn";
    public static final double ILYA_SALARY = 777;


    public static final int FIRST_DEPARTMENT_ID = 1;
    public static final int SECOND_DEPARTMENT_ID = 2;

    public static Employees getIvanFirstDep() {
        return new Employees(IVAN_FIRST_NAME, IVAN_LAST_NAME, IVAN_SALARY, FIRST_DEPARTMENT_ID);
    }

    public static Employees getPetrFirstDep() {
        return new Employees(PETR_FIRST_NAME, PETR_LAST_NAME, PETR_SALARY, FIRST_DEPARTMENT_ID);
    }

    public static Employees getIlyaSecondDep() {
        return new Employees(ILYA_FIRST_NAME, ILYA_LAST_NAME, ILYA_SALARY, SECOND_DEPARTMENT_ID);
    }
}
