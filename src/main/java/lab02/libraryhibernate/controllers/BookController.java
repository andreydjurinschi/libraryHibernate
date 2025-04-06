package lab02.libraryhibernate.controllers;

import lab02.libraryhibernate.dtos.BookDto;
import lab02.libraryhibernate.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public void createBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
    }

}
