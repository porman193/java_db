package com.example.andres;

import View.SwingApp;
import com.example.andres.model.Employee;
import com.example.andres.repository.EmployeeRepository;
import com.example.andres.repository.Repository;
import com.example.andres.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("-----All employees----");
        Repository<Employee> repository = new EmployeeRepository();

        List<Employee> employees = repository.findAll();
        employees.forEach(System.out::println);

        System.out.println("-----Searching by id------");
        System.out.println(repository.getById(4).toString());


    }

}