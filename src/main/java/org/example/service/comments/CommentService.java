package org.example.service.comments;

import org.example.entity.Comment;

import java.util.List;

public interface CommentService {
    void createComment(Long postId, String text);
    void deleteComment(Long commentId);
    List<Comment> getCommentsForPost(Long postId);
}