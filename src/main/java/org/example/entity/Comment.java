package org.example.entity;

public class Comment {
    private Long id;
    private Long postId;
    private String text;

    public Comment(Long id, Long postId, String text) {
        this.id = id;
        this.postId = postId;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public String getText() {
        return text;
    }

}