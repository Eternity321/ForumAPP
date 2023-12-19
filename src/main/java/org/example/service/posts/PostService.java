package org.example.service.posts;

import org.example.entity.Post;

import java.util.List;

public interface PostService {
    void createPost(String title, String content);
    void deletePost(Long postId);
    List<Post> getAllPosts();
    boolean postExists(Long postId);
}