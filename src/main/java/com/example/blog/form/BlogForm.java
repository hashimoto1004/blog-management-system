package com.example.blog.form;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlogForm {
    private Integer id;

    private String title;

    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}