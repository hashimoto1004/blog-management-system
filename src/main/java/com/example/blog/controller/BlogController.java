package com.example.blog.controller;

import com.example.blog.entity.Blog;
import com.example.blog.exception.ContentEmptyException;
import com.example.blog.exception.NotFoundException;
import com.example.blog.exception.TitleDuplicationException;
import com.example.blog.exception.TitleEmptyException;
import com.example.blog.form.BlogForm;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public String list(Model model) {
        List<Blog> blogs;

        try {
            blogs = blogService.findAll();
        } catch (Exception e) {
            return "500Error";
        }

        model.addAttribute("blogs", blogs);
        return "blog/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Blog blog;
        try {
            blog = blogService.detail(id);
        } catch (NotFoundException e) {
            return "404Error";
        } catch (Exception e) {
            return "500Error";
        }

        model.addAttribute("blog", blog);
        return "blog/detail";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("blog", new BlogForm());

        return "blog/form";
    }

    @PostMapping
    public String create(@ModelAttribute BlogForm blog, Model model) {
        try {
            blogService.createBlog(blog);
        } catch (TitleEmptyException e) {
            model.addAttribute("titleEmptyError", e.getMessage());
            model.addAttribute("blog", blog);
            return "blog/form";
        } catch (TitleDuplicationException e) {
            model.addAttribute("titleDuplicateError", e.getMessage());
            model.addAttribute("blog", blog);
            return "blog/form";
        } catch (ContentEmptyException e) {
            model.addAttribute("contentEmptyError", e.getMessage());
            model.addAttribute("blog", blog);
            return "blog/form";
        } catch (Exception e) {
            return "500Error";
        }

        return "redirect:/blogs";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        Blog blog;
        try {
            blog = blogService.detail(id);
        } catch (Exception e) {
            return "500Error";
        }

        model.addAttribute("blog", blog);

        return "blog/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Integer id, @ModelAttribute BlogForm blog, Model model) {
        try {
            blogService.updateBlog(id, blog);
        } catch (Exception e) {
            return "500Error";
        }

        blogService.updateBlog(id, blog);

        return "redirect:/blogs/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, Model model) {
        try {
            blogService.delete(id);
        } catch (Exception e) {
            return "500Error";
        }

        return "redirect:/blogs";
    }
}