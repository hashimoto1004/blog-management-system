package com.example.blog.repository;

import com.example.blog.entity.Blog;
import java.util.List;

public interface BlogRepository {
    List<Blog> findAll();

    Blog findById(Integer id);

    void save(Blog blog);

    void update(Blog blog);

    void delete(Integer id);
}