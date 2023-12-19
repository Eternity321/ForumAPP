package org.example.service.utilites;
public class IdGenerator {
    private Long postIdCounter = 1L;
    private Long commentIdCounter = 1L;

    public Long getNextPostId() {
        return postIdCounter++;
    }

    public Long getNextCommentId() {
        return commentIdCounter++;
    }
}