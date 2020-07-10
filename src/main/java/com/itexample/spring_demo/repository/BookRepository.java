package com.itexample.spring_demo.repository;
import com.itexample.spring_demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
