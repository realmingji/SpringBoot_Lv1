package com.sparta.post;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class TransactioTest {

    @PersistenceContext
    EntityManager em;
}
