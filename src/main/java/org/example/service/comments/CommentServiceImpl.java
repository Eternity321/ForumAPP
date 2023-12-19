package org.example.service.comments;

import org.example.entity.Comment;
import org.example.repository.DatabaseHandler;
import org.example.service.utilites.IdGenerator;

import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    private DatabaseHandler databaseHandler;
    private IdGenerator idGenerator;

    public CommentServiceImpl(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
        this.idGenerator = new IdGenerator();
    }

    @Override
    public void createComment(Long postId, String text) {
        try {
            Long commentId = idGenerator.getNextCommentId();
            Comment comment = new Comment(commentId, postId, text);
            databaseHandler.saveComment(comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteComment(Long commentId) {
        try {
            databaseHandler.deleteComment(commentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comment> getCommentsForPost(Long postId) {
        try {
            return databaseHandler.getCommentsForPost(postId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
