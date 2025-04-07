package lab02.libraryhibernate.controllers;

import lab02.libraryhibernate.dtos.BookDto;
import lab02.libraryhibernate.entities.Book;
import lab02.libraryhibernate.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    /*@PostMapping
    public void createBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
    }*/
    /*@PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        BookDto dto = bookService.getBookById(id);
        if(bookDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        bookService.updateBook(id, bookDto);
        return ResponseEntity.ok().body(dto);

    }*/

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        BookDto bookDto = bookService.getBookById(id);
        if(bookDto == null){
            return ResponseEntity.status(404).body("Book not found");
        }
        return ResponseEntity.ok(bookDto);
    }
}
