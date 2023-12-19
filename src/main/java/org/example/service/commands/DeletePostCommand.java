package org.example.service.commands;

import org.example.controllers.Command;
import org.example.service.utilites.InputValidator;
import org.example.service.posts.PostService;

import java.util.Scanner;

public class DeletePostCommand implements Command {
    private PostService postService;
    private Scanner scanner;
    private InputValidator inputValidator;

    public DeletePostCommand(PostService postService, Scanner scanner) {
        this.postService = postService;
        this.scanner = scanner;
        this.inputValidator = new InputValidator();
    }

    @Override
    public void execute() {
        System.out.print("Введите идентификатор поста для удаления: ");
        String postIdStr = scanner.nextLine();
        if (inputValidator.isNumeric(postIdStr)) {
            Long postId = Long.parseLong(postIdStr);
            if (postService.postExists(postId)) {
                postService.deletePost(postId);
                System.out.println("Пост удален.");
            } else {
                System.out.println("Пост с указанным идентификатором не существует.");
            }
        } else {
            System.out.println("Идентификатор поста должен быть числом.");
        }
    }
}