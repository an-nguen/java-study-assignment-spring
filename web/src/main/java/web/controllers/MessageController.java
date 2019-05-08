package web.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import domain.Message;
import domain.Views;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import persistence.repositories.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("messages")
public class MessageController {

    private final MessageRepository repository;

    @Autowired
    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @JsonView(Views.fullMessage.class)
    public List<Message> getMessages() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.fullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message addMessage(@RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        return repository.save(message);
    }

    @PutMapping("{id}")
    public Message updateMessage(@PathVariable("id") Message messageFromDb,
                                       @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return repository.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        repository.delete(message);
    }
}
