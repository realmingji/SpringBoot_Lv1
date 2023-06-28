package com.sparta.post.dto;

import com.sparta.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;          // id
    private String title;       // 제목
    private String contents;    // 내용
    private String author;      // 작성자명
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private String msg;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.author = post.getAuthor();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    //  성공 메시지
    public PostResponseDto(String msg) {
        this.msg = msg;
    }
}
