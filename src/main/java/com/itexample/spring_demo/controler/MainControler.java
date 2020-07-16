package com.itexample.spring_demo.controler;

import com.itexample.spring_demo.model.Author;
import com.itexample.spring_demo.model.Book;
import com.itexample.spring_demo.repository.AuthorRepository;
import com.itexample.spring_demo.repository.BookRepository;
import com.itexample.spring_demo.service.AuthorSevice;
import com.itexample.spring_demo.service.BookSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainControler {


    private final BookSevice bookService;
  private final AuthorSevice authorServise;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;



    @Value("${file.upload.dir}")
    private String uploadDir;


    @GetMapping(value = "/")
    public String homePage(@AuthenticationPrincipal Principal principal,ModelMap modelMap, @RequestParam(name = "msg", required = false) String msg) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        List<Author> allAuthors = authorServise.findAll();
        List<Book> books = bookService.findAll();
        modelMap.addAttribute("authors", allAuthors);
        modelMap.addAttribute("books", books);
        modelMap.addAttribute("msg", msg);
        modelMap.addAttribute("username", username);
        return "home";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute Author author, @RequestParam("image") MultipartFile file) throws IOException {
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File image = new File(uploadDir, name);
        file.transferTo(image);
        author.setProfilePic(name);
        authorServise.save(author);
        return "redirect:/?msg=User was added";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        String msg = book.getId()>0? "Book was update" : "Book was added";
       bookService.save(book);
        return ("redirect:/?msg=" + msg);
    }

    @GetMapping(value = "/allAuthors")
    public String getAllAuthors(ModelMap modelMap) {
        List<Author> allAuthors = authorServise.findAll();
        modelMap.addAttribute("authors", allAuthors);
        return "allAuthors";
    }

    @GetMapping(value = "/allBooks")
    public String getAllBooks(ModelMap modelMap) {
        List<Book> allBooks = bookService.findAll();
        modelMap.addAttribute("books", allBooks);
        ;
        return "allBooks";
    }

    @GetMapping(value = "/deleteAuthor")
    public String deleteAuthors(@RequestParam("id") int id) {
        authorServise.deleteById(id);
        String msg = "User was removed";
        return "redirect:/?msg=" + msg;
    }


    @GetMapping(value = "/deleteBooks")
    public String deleteBooks(@RequestParam("id") int id) {
        bookService.deleteById(id);
        return ("redirect:/allBooks");
    }

//    @RequestMapping("/updateAuthors")
//    public String UpdateAuthors(ModelMap modelMap, int id) {
//        Author one = authorRepository.getOne(id);
//        modelMap.addAttribute("authors", one);
//        return ("updateAuthors");
//    }
//
//    @RequestMapping("/updateBooks")
//    public String UpdateBooks(ModelMap modelMap, int id) {
//        Book one = bookRepository.getOne(id);
//        List<Author> all = authorRepository.findAll();
//        modelMap.addAttribute("authors", all);
//        modelMap.addAttribute("books", one);
//        return ("updateBooks");
//    }
//
//    @PostMapping("/upBooks")
//    public String upBooks(@ModelAttribute Book book) {
//        bookRepository.save(book);
//        return ("redirect:/");
//    }
//
//    @PostMapping("/upAuthors")
//    public String upAuthors(@ModelAttribute Author author) {
//        authorRepository.save(author);
//        return ("redirect:/");
//    }
//

    @GetMapping("/updateBooks")
    public String updateBook( @RequestParam("id") int id, Model model) {
        Book books = bookService.getOne(id);
        model.addAttribute("books", books);
        List<Author> authors = authorServise.findAll();
        model.addAttribute("authors", authors);
        return "/updateBooks";

    }
    @GetMapping(

            value = "/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImage(@RequestParam("name") String imageName) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }
}
