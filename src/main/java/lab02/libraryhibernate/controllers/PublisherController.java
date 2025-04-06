package lab02.libraryhibernate.controllers;

import lab02.libraryhibernate.dao.PublisherDao;
import lab02.libraryhibernate.dtos.AuthorDto;
import lab02.libraryhibernate.dtos.PublisherDto;
import lab02.libraryhibernate.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public List<PublisherDto> getAllPublishers(){
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPublisherById(@PathVariable Long id){
        PublisherDto publisherDto = publisherService.getPublisherById(id);
        if(publisherDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher not found");
        }else{
            return ResponseEntity.ok(publisherDto);
        }
    }

    @PostMapping
    public void addPublisher(@RequestBody PublisherDto publisherDto){
        publisherService.createPublisher(publisherDto);
    }

    @PutMapping
    public ResponseEntity<?> updatePublisher(@RequestBody PublisherDto publisherDto){
        PublisherDto updatedPublisher = publisherService.updatePublisher(publisherDto);
        if(updatedPublisher == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher not found");
        }
        publisherService.updatePublisher(publisherDto);
        return ResponseEntity.ok(publisherDto);
    }
}
