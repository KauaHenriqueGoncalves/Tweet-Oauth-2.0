package io.simple.twitter.controller;

import io.simple.twitter.dto.TweetDto;
import io.simple.twitter.entities.Tweet;
import io.simple.twitter.entities.User;
import io.simple.twitter.repository.TweetRepository;
import io.simple.twitter.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping("/tweet")
    public ResponseEntity<Void> create(@RequestBody TweetDto tweetDto, JwtAuthenticationToken jwtToken) {
        Optional<User> user = userRepository.findById(UUID.fromString(jwtToken.getName()));

        if (user.isEmpty()) {
            throw new RequestRejectedException("User not found, token invalid!");
        }

        Tweet tweet = new Tweet();
        tweet.setUser(user.get());
        tweet.setContent(tweetDto.getContent());

        tweet = tweetRepository.save(tweet);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tweet.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/tweet/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id, JwtAuthenticationToken jwtToken) {
        User user = userRepository.findById(UUID.fromString(jwtToken.getName())).orElseThrow(
                () -> new RequestRejectedException("User not found! Token invalid!")
        );

        Tweet tweet = tweetRepository.findById(id).orElseThrow(
                () -> new RequestRejectedException("Tweet not found!")
        );

        boolean isEqualsId = user.getId().equals(tweet.getUser().getId());

        if (!isEqualsId) {
            throw new RequestRejectedException("Don't delete this tweet! This tweet belongs another user!");
        }

        tweetRepository.deleteById(tweet.getId());

        return ResponseEntity.noContent().build();
    }

}
