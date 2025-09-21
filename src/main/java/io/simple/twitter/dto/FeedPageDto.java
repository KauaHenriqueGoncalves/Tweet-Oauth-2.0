package io.simple.twitter.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class FeedPageDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<FeedItemDto> feedItems;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Integer totalItens;

    public FeedPageDto(List<FeedItemDto> feedItems, Integer page, Integer pageSize, Integer totalPages, Integer totalItens) {
        this.feedItems = feedItems;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalItens = totalItens;
    }

    public List<FeedItemDto> getFeedItems() {
        return feedItems;
    }

    public void setFeedItems(List<FeedItemDto> feedItems) {
        this.feedItems = feedItems;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(Integer totalItens) {
        this.totalItens = totalItens;
    }
}
