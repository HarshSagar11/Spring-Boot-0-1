package com.week5Learning.Security.W5Learning.repositories;

import com.week5Learning.Security.W5Learning.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
