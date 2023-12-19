package org.example.service.commands;

import org.example.controllers.Command;
import org.example.service.utilites.InputValidator;
import org.example.service.comments.CommentService;
import org.example.service.posts.PostService;

import java.util.Scanner;

public class CreateCommentCommand implements Command {
    private CommentService commentService;
    private PostService postService;
    private Scanner scanner;
    private InputValidator inputValidator;

    public CreateCommentCommand(CommentService commentService, PostService postService, Scanner scanner) {
        this.commentService = commentService;
        this.postService = postService;
        this.scanner = scanner;
        this.inputValidator = new InputValidator();
    }

    @Override
    public void execute() {
        System.out.print("Введите идентификатор поста: ");
        String postIdStr = scanner.nextLine();
        if (inputValidator.isNumeric(postIdStr)) {
            Long postId = Long.parseLong(postIdStr);
            if (postService.postExists(postId)) {
                System.out.print("Введите текст комментария: ");
                String text = scanner.nextLine();
                commentService.createComment(postId, text);
            } else {
                System.out.println("Пост с указанным идентификатором не существует.");
            }
        } else {
            System.out.println("Идентификатор поста должен быть числом.");
        }
    }
}
