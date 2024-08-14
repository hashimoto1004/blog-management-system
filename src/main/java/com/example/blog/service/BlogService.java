package com.example.blog.service;

import com.example.blog.entity.Blog;
import com.example.blog.form.BlogForm;
import com.example.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> list() {
        return blogRepository.findAll();
    }

    public Blog detail(Integer id) {
        return blogRepository.findById(id);
    }

    public void create(BlogForm form) {
        Blog blog = new Blog();
        blog.setTitle(form.getTitle());
        blog.setContent(form.getContent());

        blogRepository.save(blog);
    }

    public void update(Integer id, BlogForm form) {
        Blog blog = new Blog();
        blog.setId(id);
        blog.setTitle(form.getTitle());
        blog.setContent(form.getContent());

        blogRepository.update(blog);

    }

    public void delete(Integer id) {
        blogRepository.delete(id);
    }

}