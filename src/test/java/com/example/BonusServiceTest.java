package com.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusServiceTest {
    private final BonusService service = new BonusService();

    @Test
    void testReturnCode1_BothEmpty() {
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
    void testSuccess_StandardBonus() {
        ArrayList<Sale> sales = new ArrayList<>(List.of(new Sale("IT", 5000)));
        Employee dev = new Employee("Alice", "IT", "Dev", 4000);
        service.giveBonus(1, new ArrayList<>(List.of(dev)), 1, sales);
        assertEquals(5000, dev.salary);
    }

    @Test
    void testBoundary_SalaryExactly5000() {
        ArrayList<Sale> sales = new ArrayList<>(List.of(new Sale("IT", 1000)));
        Employee emp = new Employee("Limit", "IT", "Staff", 5000);
        service.giveBonus(1, new ArrayList<>(List.of(emp)), 1, sales);
        assertEquals(6000, emp.salary);
    }

    @Test
    void testBoundary_Salary4999() {
        ArrayList<Sale> sales = new ArrayList<>(List.of(new Sale("IT", 1000)));
        Employee emp = new Employee("Below", "IT", "Staff", 4999);
        service.giveBonus(1, new ArrayList<>(List.of(emp)), 1, sales);
        assertEquals(5999, emp.salary);
    }

    @Test
    void testBoundary_Salary5001() {
        ArrayList<Sale> sales = new ArrayList<>(List.of(new Sale("IT", 1000)));
        Employee emp = new Employee("Above", "IT", "Staff", 5001);
        service.giveBonus(1, new ArrayList<>(List.of(emp)), 1, sales);
        assertEquals(5501, emp.salary);
    }

    @Test
    void testBoundary_ManagerBonus() {
        ArrayList<Sale> sales = new ArrayList<>(List.of(new Sale("IT", 1000)));
        Employee manager = new Employee("Boss", "IT", "Manager", 3000);
        service.giveBonus(1, new ArrayList<>(List.of(manager)), 1, sales);
        assertEquals(3500, manager.salary);
    }
}
