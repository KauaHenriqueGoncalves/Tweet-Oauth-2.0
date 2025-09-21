package io.simple.twitter.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public class FeedItemDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long idTweet;
    private String content;
    private String username;
    private Instant createdAt;

    public FeedItemDto(Long idTweet, String content, String username, Instant createdAt) {
        this.content = content;
        this.idTweet = idTweet;
        this.username = username;
        this.createdAt = createdAt;
    }

    public Long getIdTweet() {
        return idTweet;
    }

    public void setIdTweet(Long idTweet) {
        this.idTweet = idTweet;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
