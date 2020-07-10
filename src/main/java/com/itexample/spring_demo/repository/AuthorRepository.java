package com.itexample.spring_demo.repository;


import com.itexample.spring_demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
