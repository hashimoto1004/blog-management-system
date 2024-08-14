package com.example.blog.controller;

import com.example.blog.entity.Blog;
import com.example.blog.form.BlogForm;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public String list(Model model) {
        List<Blog> blogs = blogService.list();
        model.addAttribute("blogs", blogs);
        return "blog/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Blog blog = blogService.detail(id);
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
        blogService.create(blog);

        return "redirect:/blogs";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        Blog blog = blogService.detail(id);
        model.addAttribute("blog", blog);

        return "blog/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Integer id, @ModelAttribute BlogForm blog) {
        blogService.update(id, blog);

        return "redirect:/blogs/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        blogService.delete(id);
        return "redirect:/blogs";
    }
}