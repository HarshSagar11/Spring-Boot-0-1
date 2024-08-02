package com.example.week4.learningW4.services;

import com.example.week4.learningW4.dto.PostDto;
import com.example.week4.learningW4.entities.PostEntity;
import com.example.week4.learningW4.exceptions.ResourceNotFoundException;
import com.example.week4.learningW4.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPost() {
        return postRepository.findAll().stream().map(postEntity -> modelMapper.map(postEntity,PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto postDto) {
        PostEntity postEntity  = modelMapper.map(postDto,PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity),PostDto.class);
    }

    @Override
    public PostDto getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found with id : "+ id));
        return modelMapper.map(postEntity, PostDto.class);
    }

    @Override
    public PostDto updatePost(Long postId, PostDto postDto) {
        PostEntity olderPost = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found with id : "+ postId));
        postDto.setId(postId);
        modelMapper.map(postDto,olderPost);
        PostEntity savedPost = postRepository.save(olderPost);
        return modelMapper.map(savedPost,PostDto.class);
    }
}
