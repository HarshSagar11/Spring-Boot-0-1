package com.example.week4.learningW4.services;

import com.example.week4.learningW4.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPost();
    PostDto createNewPost(PostDto postDto);

    PostDto getPostById(Long PostId);

    PostDto updatePost(Long postId, PostDto postDto);
}
