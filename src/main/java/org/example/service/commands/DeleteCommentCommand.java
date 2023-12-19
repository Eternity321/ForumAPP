package org.example.service.commands;

import org.example.controllers.Command;
import org.example.service.utilites.InputValidator;
import org.example.service.comments.CommentService;

import java.util.Scanner;

public class DeleteCommentCommand implements Command {
    private CommentService commentService;
    private Scanner scanner;
    private InputValidator inputValidator;

    public DeleteCommentCommand(CommentService commentService, Scanner scanner) {
        this.commentService = commentService;
        this.scanner = scanner;
        this.inputValidator = new InputValidator();
    }

    @Override
    public void execute() {
        System.out.print("Введите идентификатор комментария для удаления: ");
        String commentIdStr = scanner.nextLine();
        if (inputValidator.isNumeric(commentIdStr)) {
            Long commentId = Long.parseLong(commentIdStr);
            commentService.deleteComment(commentId);
            System.out.println("Комментарий удален.");
        } else {
            System.out.println("Идентификатор комментария должен быть числом.");
        }
    }
}