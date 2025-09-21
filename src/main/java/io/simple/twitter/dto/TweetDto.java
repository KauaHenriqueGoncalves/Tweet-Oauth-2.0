package io.simple.twitter.dto;

import java.io.Serial;
import java.io.Serializable;

public class TweetDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
