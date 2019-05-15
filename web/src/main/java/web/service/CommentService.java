package web.service;

import domain.Comment;
import domain.User;
import domain.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.repositories.CommentRepository;
import web.dto.EventType;
import web.dto.ObjectType;
import web.util.WsSender;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepository commentRepo;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepository commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.fullComment.class);
    }

    public Comment create(Comment comment, User author) {
        comment.setAuthor(author);
        Comment commentDb = commentRepo.save(comment);

        wsSender.accept(EventType.CREATE, commentDb);

        return commentDb;
    }
}
