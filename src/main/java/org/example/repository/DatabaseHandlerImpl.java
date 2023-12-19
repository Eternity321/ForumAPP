package org.example.repository;

import org.example.entity.Comment;
import org.example.entity.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandlerImpl implements DatabaseHandler {
    @Override
    public void savePost(Post post) {
        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO post (title, text) VALUES (?, ?)")) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveComment(Comment comment) {
        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO comments (text, post_id) VALUES (?, ?)")) {
            statement.setString(1, comment.getText());
            statement.setLong(2, comment.getPostId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePost(Long postId) {
        try (Connection connection = DataManager.getConnection();
             PreparedStatement deleteCommentsStatement = connection.prepareStatement("DELETE FROM comments WHERE post_id = ?");
             PreparedStatement deletePostStatement = connection.prepareStatement("DELETE FROM post WHERE id = ?")) {

            deleteCommentsStatement.setLong(1, postId);
            deleteCommentsStatement.executeUpdate();
            deletePostStatement.setLong(1, postId);
            deletePostStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteComment(Long commentId) {
        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM comments WHERE id = ?")) {
            statement.setLong(1, commentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (Connection connection = DataManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM post")) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("text");
                posts.add(new Post(id, title, content));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public List<Comment> getCommentsForPost(Long postId) {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM comments WHERE post_id = ?")) {
            statement.setLong(1, postId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String text = resultSet.getString("text");
                    comments.add(new Comment(id, postId, text));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    @Override
    public boolean postExists(Long postId) throws SQLException {
        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM post WHERE id = ?")) {
            statement.setLong(1, postId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}
