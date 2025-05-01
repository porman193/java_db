package com.example.curso.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;

public class UtilEntity {
    private final static EntityManagerFactory entityManager = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("persistence-unit");
    }

    public static EntityManager getEntityManager() {
        return entityManager.createEntityManager();
    }
}
