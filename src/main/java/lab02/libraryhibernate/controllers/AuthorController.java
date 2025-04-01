package lab02.libraryhibernate.controllers;

import lab02.libraryhibernate.dtos.AuthorDto;
import lab02.libraryhibernate.entities.Author;
import lab02.libraryhibernate.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public void createAuthor(@RequestBody AuthorDto authorDto){
        authorService.createAuthor(authorDto);
    }
}
