package web.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import domain.Message;
import domain.Views;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import persistence.repositories.MessageRepository;
import web.dto.EventType;
import web.dto.ObjectType;
import web.util.WsSender;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("messages")
public class MessageController {

    private final MessageRepository repository;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageController(MessageRepository repository, WsSender wsSender) {
        this.repository = repository;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.idName.class);
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
    public Message create(@RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        Message updatedMsg = repository.save(message);

        wsSender.accept(EventType.CREATE, updatedMsg);

        return updatedMsg;
    }

    @PutMapping("{id}")
    public Message updateMessage(@PathVariable("id") Message messageFromDb,
                                       @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        Message updatedMsg = repository.save(message);
        wsSender.accept(EventType.UPDATE, updatedMsg);

        return updatedMsg;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        repository.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }
}
