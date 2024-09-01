package com.week5Learning.Security.W5Learning.utils;

import com.week5Learning.Security.W5Learning.dto.PostDTO;
import com.week5Learning.Security.W5Learning.entities.UserEntity;
import com.week5Learning.Security.W5Learning.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity {

    private final PostService postService;

    boolean isOwnerOfPost(Long postId){
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication();
        PostDTO post = postService.getPostById(postId);
        return post.getUserDto().getId().equals(user.getId());
    }

}
