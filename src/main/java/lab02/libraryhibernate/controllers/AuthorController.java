package lab02.libraryhibernate.controllers;

import lab02.libraryhibernate.dtos.AuthorDto;
import lab02.libraryhibernate.entities.Author;
import lab02.libraryhibernate.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<AuthorDto> getAllAuthors(){
        return authorService.getAuthors();
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto){
        AuthorDto updatedAuthorDto = authorService.getAuthorById(id);
        if(updatedAuthorDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        authorService.updateAuthor(id, authorDto);
        return ResponseEntity.ok().body("Author updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable Long id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Author deleted successfully");
    }

    @PostMapping
    public void createAuthor(@RequestBody AuthorDto authorDto){
        authorService.createAuthor(authorDto);
    }
}
