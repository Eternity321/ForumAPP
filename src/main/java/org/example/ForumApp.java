package org.example;

import org.example.controllers.ForumController;
import org.example.service.comments.CommentServiceImpl;
import org.example.service.posts.PostServiceImpl;
import org.example.repository.DatabaseHandlerImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class ForumApp {
    private ForumController controller;

    public ForumApp(Scanner scanner) {
        this.controller = new ForumController(new PostServiceImpl(new DatabaseHandlerImpl()), new CommentServiceImpl(new DatabaseHandlerImpl()), scanner);
    }

    public void run() {
        controller.run();
    }

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ForumApp forumApp = new ForumApp(scanner);
        forumApp.run();
        scanner.close();
    }
}
