package com.week5Learning.Security.W5Learning.controllers;

import com.week5Learning.Security.W5Learning.dto.PostDTO;
import com.week5Learning.Security.W5Learning.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    // this @Secured is used to define role-based access control at the method level
    @Secured("ROLE_USER")
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
//    @PreAuthorize("hasRole('USER')")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN') OR hasAuthority(POST_VIEW)")
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)") // #is used to pass the variable
    public PostDTO getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }

}
