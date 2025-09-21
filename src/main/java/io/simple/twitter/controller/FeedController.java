package io.simple.twitter.controller;

import io.simple.twitter.dto.FeedItemDto;
import io.simple.twitter.dto.FeedPageDto;
import io.simple.twitter.entities.Tweet;
import io.simple.twitter.repository.TweetRepository;
import io.simple.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping
    public ResponseEntity<FeedPageDto> pageFeed(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "5") int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").ascending());
        Page<Tweet> tweets = tweetRepository.findAll(pageRequest);

        List<FeedItemDto> feedItemDtos = tweets.stream()
                .map(tweet -> new FeedItemDto(
                        tweet.getId(),
                        tweet.getContent(),
                        tweet.getUser().getName(),
                        tweet.getCreatedAt()))
                .toList();

        return ResponseEntity.ok().body(new FeedPageDto(feedItemDtos, page, size, tweets.getTotalPages(), tweets.getTotalPages()));
    }

}
