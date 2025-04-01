package lab02.libraryhibernate.service;

import jakarta.persistence.Access;
import lab02.libraryhibernate.dao.AuthorDao;
import lab02.libraryhibernate.dtos.AuthorDto;
import lab02.libraryhibernate.entities.Author;
import lab02.libraryhibernate.mappers.AuthorMapper;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDto> getAuthors()
    {
        List<AuthorDto> authorDtos = new ArrayList<>();
        for(Author author : authorDao.getAuthors()){
            authorDtos.add(authorMapper.mapToDto(author));
        }
        return authorDtos;
    }

    public void createAuthor(AuthorDto authorDto){
        Author author = authorMapper.mapToEntity(authorDto);
        authorDao.createAuthor(author);
    }

    public AuthorDto getAuthorById(Long id){
            Author author = authorDao.getAuthor(id);
            return authorMapper.mapToDto(author);
    }
}
