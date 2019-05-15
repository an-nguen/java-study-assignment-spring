package web.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import domain.Comment;
import domain.User;
import domain.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.CommentService;

@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @JsonView(Views.fullComment.class)
    public Comment create(
            @RequestBody Comment comment,
            @AuthenticationPrincipal User author) {
        return commentService.create(comment, author);
    }
}
