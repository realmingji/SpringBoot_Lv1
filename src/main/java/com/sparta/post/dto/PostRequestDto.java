package com.sparta.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {
    private String title;       // 제목
    private String contents;    // 내용
    private String author;      // 작성자명
    private String password;
}
