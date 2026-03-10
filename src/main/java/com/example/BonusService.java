package com.example;

import java.util.ArrayList;

public class BonusService {

    public int giveBonus(int numEmployees, ArrayList<Employee> employees,
                         int numSales, ArrayList<Sale> sales) {

        if (numEmployees <= 0 || numSales <= 0) return 1;

        String bestDept = "";
        double maxSale = -1;
        for (Sale s : sales) {
            if (s.sumSale > maxSale) {
                maxSale = s.sumSale;
                bestDept = s.department;
            }
        }

        boolean foundInDept = false;
        for (Employee e : employees) {
            if (e.department.equals(bestDept)) {
                foundInDept = true;


                if (e.salary > 5000 || e.function.equalsIgnoreCase("manager")) {
                    e.salary += 500;
                } else {
                    e.salary += 1000;
                }
            }
        }

        return foundInDept ? 0 : 2;
    }
}
