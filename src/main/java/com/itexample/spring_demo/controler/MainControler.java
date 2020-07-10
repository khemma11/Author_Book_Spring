package com.itexample.spring_demo.controler;
import com.itexample.spring_demo.model.Author;
import com.itexample.spring_demo.model.Book;
import com.itexample.spring_demo.repository.AuthorRepository;
import com.itexample.spring_demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainControler {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;


    @GetMapping(value = "/")
    public String homePage(ModelMap modelMap) {
        List<Author> allAuthors = authorRepository.findAll();
        modelMap.addAttribute("authors", allAuthors);
        return "home";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);
        return ("redirect:/");
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return ("redirect:/");
    }

    @GetMapping(value = "/allAuthors")
    public String getAllAuthors(ModelMap modelMap) {
        List<Author> allAuthors = authorRepository.findAll();
        modelMap.addAttribute("authors", allAuthors);
        return "allAuthors";
    }

    @GetMapping(value = "/allBooks")
    public String getAllBooks(ModelMap modelMap) {
        List<Book> allBooks = bookRepository.findAll();
        modelMap.addAttribute("books", allBooks);
        ;
        return "allBooks";
    }

    @GetMapping(value = "/deleteAuthor")
    public String deleteAuthors(@RequestParam("id") int id) {
        authorRepository.deleteById(id);
        return ("redirect:/allAuthors");
    }


    @GetMapping(value = "/deleteBooks")
    public String deleteBooks(@RequestParam("id") int id) {
        bookRepository.deleteById(id);
        return ("redirect:/allBooks");
    }

    @RequestMapping("/updateAuthors")
    public String UpdateAuthors(ModelMap modelMap, int id) {
        Author one = authorRepository.getOne(id);
        modelMap.addAttribute("authors", one);
        return ("updateAuthors");
    }

    @RequestMapping("/updateBooks")
    public String UpdateBooks(ModelMap modelMap, int id) {
        Book one = bookRepository.getOne(id);
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        modelMap.addAttribute("books", one);
        return ("updateBooks");
    }

    @PostMapping("/upBooks")
    public String upBooks(@ModelAttribute Book book) {
        bookRepository.save(book);
        return ("redirect:/");
    }

    @PostMapping("/upAuthors")
    public String upAuthors(@ModelAttribute Author author) {
        authorRepository.save(author);
        return ("redirect:/");
    }

}
