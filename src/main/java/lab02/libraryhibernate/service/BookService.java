package lab02.libraryhibernate.service;

import lab02.libraryhibernate.dao.BookDao;
import lab02.libraryhibernate.dtos.BookDto;
import lab02.libraryhibernate.entities.Book;
import lab02.libraryhibernate.mappers.BookMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookDao bookDao;
    private final BookMapper bookMapper;

    public BookService(BookDao bookDao, BookMapper bookMapper) {
        this.bookDao = bookDao;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks(){
        List<Book> books = bookDao.getAllBooks();
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books){
            bookDtos.add(bookMapper.mapToDto(book));
        }
        return bookDtos;
    }

    public void addBook(BookDto bookDto){
        Book book = bookMapper.mapToEntity(bookDto);
        bookDao.createBook(book);
    }

    public BookDto getBookById(Long id){
        Book book = bookDao.getBookById(id);
        if(book == null){
            return null;
        }
        return bookMapper.mapToDto(book);
    }
}
