package com.example.blog.service;

import com.example.blog.entity.Blog;
import com.example.blog.exception.ContentEmptyException;
import com.example.blog.exception.DatabaseException;
import com.example.blog.exception.TitleDuplicationException;
import com.example.blog.exception.TitleEmptyException;
import com.example.blog.form.BlogForm;
import com.example.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    public Blog findById(Integer id) {
        return blogRepository.findById(id);
    }

    public void createBlog(BlogForm form) {
        if (isTitleEmpty(form.getTitle())) {
            throw new TitleEmptyException("タイトルは必須です。");
        }

        if (isTitleDuplicate(form.getTitle())) {
            throw new TitleDuplicationException("タイトルが重複しています。");
        }

        if (isContentEmpty(form.getContent())) {
            throw new ContentEmptyException("コンテンツは必須です。");
        }

        Blog blog = new Blog();
        blog.setTitle(form.getTitle());
        blog.setContent(form.getContent());

        blogRepository.save(blog);
    }

    public void updateBlog(Integer id, BlogForm form) {
        Blog blog = new Blog();
        blog.setId(id);
        blog.setTitle(form.getTitle());
        blog.setContent(form.getContent());

        blogRepository.update(blog);

    }

    public void delete(Integer id) {
        blogRepository.delete(id);
    }

    public boolean isTitleEmpty(String title) {
        return title.isEmpty();
    }

    public boolean isContentEmpty(String content) {
        return content.isEmpty();
    }

    public boolean isTitleDuplicate(String title) {
        return blogRepository.countByTitle(title) > 0;
    }

}