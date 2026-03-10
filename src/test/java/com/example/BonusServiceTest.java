package com.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusServiceTest {
    private final BonusService service = new BonusService();

    @Test
    void testReturnCode1_EmptyData() {
        int result = service.giveBonus(0, new ArrayList<>(), 0, new ArrayList<>());
        assertEquals(1, result);
    }

    @Test
    void testReturnCode1_EmptyEmployees() {
        ArrayList<Sale> sales = new ArrayList<>(List.of(new Sale("IT", 100)));
        int result = service.giveBonus(0, new ArrayList<>(), 1, sales);
        assertEquals(1, result);
    }

    @Test
    void testReturnCode1_EmptySales() {
        ArrayList<Employee> employees = new ArrayList<>(List.of(new Employee("John", "Sales", "Staff", 3000)));
        int result = service.giveBonus(1, employees, 0, new ArrayList<>());
        assertEquals(1, result);
    }

    @Test
    void testReturnCode2_NoEmployeesInWinningDept() {
        ArrayList<Sale> sales = new ArrayList<>(List.of(new Sale("IT", 100)));
        ArrayList<Employee> employees = new ArrayList<>(List.of(new Employee("John", "Sales", "Staff", 3000)));

        int result = service.giveBonus(1, employees, 1, sales);
        assertEquals(2, result);
    }

    @Test
    void testSuccessAndSalaryLogic() {
        ArrayList<Sale> sales = new ArrayList<>(List.of(new Sale("IT", 5000)));

        Employee dev = new Employee("Alice", "IT", "Dev", 4000);
        Employee manager = new Employee("Bob", "IT", "Manager", 3000);
        Employee senior = new Employee("Charlie", "IT", "Dev", 6000);

        ArrayList<Employee> employees = new ArrayList<>(Arrays.asList(dev, manager, senior));

        int result = service.giveBonus(3, employees, 1, sales);

        assertEquals(0, result);
        assertEquals(5000, dev.salary);
        assertEquals(3500, manager.salary);
        assertEquals(6500, senior.salary);
    }
}
