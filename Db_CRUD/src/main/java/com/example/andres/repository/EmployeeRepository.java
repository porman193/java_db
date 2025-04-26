package com.example.andres.repository;

import com.example.andres.model.Employee;
import com.example.andres.util.DataBaseConnection;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {


    private Connection getConnection() throws  SQLException{
        return  DataBaseConnection.getConnection();
    }


    @Override
    public List<Employee> findAll() throws  SQLException{
        List<Employee> employees = new ArrayList<>();
        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM employees");
        ) {
            while (result.next()) {
                employees.add(createEmployee(result));
            }
        }
        return employees;
    }


    @Override
    public Employee getById(Integer id) throws SQLException {
        String query = "SELECT * FROM employees WHERE id = ?";
        Employee employee = null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ){
            statement.setInt(1,id);
            try(ResultSet result = statement.executeQuery();){
                if(result.next()){
                    employee=createEmployee(result);
                }
            }
        }
        return employee;
    }

    @Override
    public void save(Employee employee) throws  SQLException {
        String query = "INSERT INTO employees (first_name,pa_surname,ma_surname,email,salary,curp) VALUES (?,?,?,?,?,?)";
        if(  employee.getId() != null && employee.getId() > 0){
            query = "UPDATE employees SET first_name=?,pa_surname=?,ma_surname=?,email=?,salary= ?,curp=? WHERE id = ?";
        };
        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ){
            statement.setString(1,employee.getFirst_name());
            statement.setString(2,employee.getPa_surname());
            statement.setString(3,employee.getMa_surname());
            statement.setString(4,employee.getEmail());
            statement.setFloat(5,employee.getSalary());
            statement.setString(6,employee.getCurp());

            if(employee.getId() != null && employee.getId() > 0)statement.setInt(7,employee.getId());

            int affected =statement.executeUpdate();

            if(affected <= 0)throw  new SQLException("Sorry, we were unable to save the new employee info.");
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM employees WHERE id = ?";
        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                )
        {
            statement.setInt(1,id);
            statement.executeUpdate();
        }
    }

    private Employee createEmployee(ResultSet result) throws SQLException {
        Employee employee = new Employee();
        employee.setId(result.getInt("id"));
        employee.setEmail(result.getString("email"));
        employee.setFirst_name(result.getString("first_name"));
        employee.setMa_surname(result.getString("ma_surname"));
        employee.setPa_surname(result.getString("pa_surname"));
        employee.setSalary(result.getFloat("salary"));
        employee.setCurp(result.getString("curp"));

        return  employee;
    }

}
