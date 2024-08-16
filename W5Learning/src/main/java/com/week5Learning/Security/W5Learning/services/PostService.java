package com.week5Learning.Security.W5Learning.services;

import com.week5Learning.Security.W5Learning.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
}
