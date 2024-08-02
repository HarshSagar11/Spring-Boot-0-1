package com.example.week4.learningW4.controllers;

import com.example.week4.learningW4.dto.PostDto;
import com.example.week4.learningW4.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPost(){
        return postService.getAllPost();
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto postDto){
        return postService.createNewPost(postDto);
    }

    @PutMapping("/{postId}")
    public PostDto updatePost(@PathVariable Long postId ,@RequestBody PostDto postDto){
        return postService.updatePost(postId,postDto);
    }

}
