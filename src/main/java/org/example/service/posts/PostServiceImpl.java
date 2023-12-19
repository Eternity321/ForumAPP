package org.example.service.posts;

import org.example.entity.Post;
import org.example.repository.DatabaseHandler;
import org.example.service.utilites.IdGenerator;

import java.sql.SQLException;
import java.util.List;

public class PostServiceImpl implements PostService {
    private DatabaseHandler databaseHandler;
    private IdGenerator idGenerator;

    public PostServiceImpl(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
        this.idGenerator = new IdGenerator();
    }

    @Override
    public void createPost(String title, String content) {
        try {
            Long postId = idGenerator.getNextPostId();
            Post post = new Post(postId, title, content);
            databaseHandler.savePost(post);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePost(Long postId) {
        try {
            databaseHandler.deletePost(postId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAllPosts() {
        try {
            return databaseHandler.getAllPosts();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean postExists(Long postId) {
        try {
            return databaseHandler.postExists(postId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
