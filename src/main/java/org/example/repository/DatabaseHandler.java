package org.example.repository;

import org.example.entity.Comment;
import org.example.entity.Post;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseHandler {
    void savePost(Post post) throws SQLException;
    void saveComment(Comment comment) throws SQLException;
    void deletePost(Long postId) throws SQLException;
    void deleteComment(Long commentId) throws SQLException;
    List<Post> getAllPosts() throws SQLException;
    List<Comment> getCommentsForPost(Long postId) throws SQLException;
    boolean postExists(Long postId) throws SQLException;
}
