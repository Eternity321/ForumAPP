package org.example.service.commands;

import org.example.controllers.Command;
import org.example.entity.Comment;
import org.example.entity.Post;
import org.example.service.comments.CommentService;
import org.example.service.posts.PostService;

import java.util.List;

public class ViewPostsCommand implements Command {
    private PostService postService;
    private CommentService commentService;

    public ViewPostsCommand(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @Override
    public void execute() {
        List<Post> posts = postService.getAllPosts();
        for (Post post : posts) {
            System.out.println("Пост #" + post.getId() + ": " + post.getTitle() + ": " + post.getContent());
            List<Comment> comments = commentService.getCommentsForPost(post.getId());
            if (!comments.isEmpty()) {
                System.out.println("Комментарии:");
                for (Comment comment : comments) {
                    System.out.println("Комментарий #" + comment.getId() + ": " + comment.getText());
                }
            }
        }
    }
}