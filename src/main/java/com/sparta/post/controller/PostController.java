package com.sparta.post.controller;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 글 작성
    @PostMapping
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.creatPost(requestDto);
    }

    // 전체 글 조회
    @GetMapping
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    // 선택 글 조회
    @GetMapping("/{id}")
    public PostResponseDto selectPost(@PathVariable Long id) {
        return postService.selectPost(id);
    }

    // 글 수정
    @PutMapping("/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    // 글 삭제
    @DeleteMapping("/{id}")
    public PostResponseDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.deletePost(id, requestDto.getPassword());
    }
}