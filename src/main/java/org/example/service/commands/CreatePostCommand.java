package org.example.service.commands;

import org.example.controllers.Command;
import org.example.service.utilites.InputValidator;
import org.example.service.posts.PostService;

import java.util.Scanner;

public class CreatePostCommand implements Command {
    private PostService postService;
    private Scanner scanner;
    private InputValidator inputValidator;

    public CreatePostCommand(PostService postService, Scanner scanner) {
        this.postService = postService;
        this.scanner = scanner;
        this.inputValidator = new InputValidator();
    }

    @Override
    public void execute() {
        System.out.print("Введите заголовок поста: ");
        String title = scanner.nextLine();
        System.out.print("Введите содержание поста: ");
        String content = scanner.nextLine();
        postService.createPost(title, content);
    }
}