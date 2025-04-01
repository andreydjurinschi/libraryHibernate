package lab02.libraryhibernate.dtos;

import jakarta.persistence.*;
import lab02.libraryhibernate.entities.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String title;
    private Long authorId;

}
