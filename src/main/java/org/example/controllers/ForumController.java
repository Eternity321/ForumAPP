package org.example.controllers;

import org.example.service.commands.*;
import org.example.service.comments.CommentService;
import org.example.service.posts.PostService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ForumController {
    private PostService postService;
    private CommentService commentService;
    private Scanner scanner;
    private CommandInvoker commandInvoker;

    public ForumController(PostService postService, CommentService commentService, Scanner scanner) {
        this.postService = postService;
        this.commentService = commentService;
        this.scanner = scanner;
        this.commandInvoker = new CommandInvoker();
        initializeCommands();
    }

    private void initializeCommands() {
        commandInvoker.setCommand(new CreatePostCommand(postService, scanner));
        commandInvoker.setCommand(new CreateCommentCommand(commentService, postService, scanner));
        commandInvoker.setCommand(new DeletePostCommand(postService, scanner));
        commandInvoker.setCommand(new DeleteCommentCommand(commentService, scanner));
        commandInvoker.setCommand(new ViewPostsCommand(postService, commentService));
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Создать пост");
            System.out.println("2. Создать комментарий");
            System.out.println("3. Удалить пост");
            System.out.println("4. Удалить комментарий");
            System.out.println("5. Просмотреть посты");
            System.out.println("6. Выйти");
            System.out.println();

            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Вы ввели некорректное значение. Пожалуйста, введите номер действия.");
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    commandInvoker.setCommand(new CreatePostCommand(postService, scanner));
                    commandInvoker.executeCommand();
                    break;
                case 2:
                    commandInvoker.setCommand(new CreateCommentCommand(commentService, postService, scanner));
                    commandInvoker.executeCommand();
                    break;
                case 3:
                    commandInvoker.setCommand(new DeletePostCommand(postService, scanner));
                    commandInvoker.executeCommand();
                    break;
                case 4:
                    commandInvoker.setCommand(new DeleteCommentCommand(commentService, scanner));
                    commandInvoker.executeCommand();
                    break;
                case 5:
                    commandInvoker.setCommand(new ViewPostsCommand(postService, commentService));
                    commandInvoker.executeCommand();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
        scanner.close();
    }
}
