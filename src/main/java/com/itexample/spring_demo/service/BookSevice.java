package com.itexample.spring_demo.service;

import com.itexample.spring_demo.model.Author;
import com.itexample.spring_demo.model.Book;
import com.itexample.spring_demo.repository.AuthorRepository;
import com.itexample.spring_demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BookSevice {

    private final BookRepository bookRepository;

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book getOne(int id) {
        return bookRepository.getOne(id);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public void deleteById (int id){
        bookRepository.deleteById(id);
    }

}
