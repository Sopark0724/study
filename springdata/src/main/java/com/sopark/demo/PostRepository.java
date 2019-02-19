package com.sopark.demo;

import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(idClass = Long.class, domainClass = Post.class)
public interface PostRepository {
    Post save(Post post);

    List<Post> findAll();
}
