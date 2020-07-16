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

public class AuthorSevice {

    private final AuthorRepository authorRepository;

    public void save(Author author) {
        authorRepository.save(author);
    }



    public Author getOne(int id) {
        return authorRepository.getOne(id);
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }
    public void deleteById (int id){
       authorRepository.deleteById(id);
    }
}
