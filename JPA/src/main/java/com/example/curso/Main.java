package com.example.curso;

import com.example.curso.entity.Employee;
import com.example.curso.util.UtilEntity;

import jakarta.persistence.EntityManager;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManager entityManager = UtilEntity.getEntityManager();

        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e",Employee.class).getResultList();

        employees.forEach(System.out::println);
    }
}