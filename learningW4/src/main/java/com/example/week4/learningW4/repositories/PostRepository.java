package com.example.week4.learningW4.repositories;

import com.example.week4.learningW4.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
