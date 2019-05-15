package web.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import domain.Message;
import domain.User;
import domain.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import web.dto.MessagePageDto;
import web.service.MessageService;

import java.io.IOException;

@RestController
@RequestMapping("messages")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService service) {
        this.messageService = service;
    }

    @GetMapping
    @JsonView(Views.fullMessage.class)
    public MessagePageDto getMessages(
            @PageableDefault(size = MessageService.MESSAGES_PER_PAGE,
                    sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return this.messageService.findAll(pageable);
    }

    @GetMapping("{id}")
    @JsonView(Views.fullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message create(
            @RequestBody Message message,
            @AuthenticationPrincipal User author
    ) throws IOException {
        return messageService.create(message, author);
    }

    @PutMapping("{id}")
    public Message updateMessage(@PathVariable("id") Message messageFromDb,
                                       @RequestBody Message message) throws IOException {
        return messageService.update(messageFromDb, message);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageService.delete(message);
    }
}
