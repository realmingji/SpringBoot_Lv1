package com.sparta.post.service;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 글 작성
    public PostResponseDto creatPost(PostRequestDto requestDto) {
        // RequestDto -> Entity
        Post post = new Post(requestDto);
        // DB 저장
        Post savePost = postRepository.save(post);
        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(savePost);
        return postResponseDto;
    }

    // 글 조회
    public List<PostResponseDto> getPosts() {
        // DB 조회
        return postRepository
                .findAll()
                .stream()
                .map(PostResponseDto::new)
                .toList();
    }

    // 선택 글 조회
    public PostResponseDto selectPost(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    @Transactional
    // 글 수정
    // 비밀번호가 맞는지 확인하는 코드 작성
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);

        if (post.getPassword().equals(requestDto.getPassword())) {
            post.update(requestDto);
            return new PostResponseDto("수정 완료~!~");
        } else {
            return new PostResponseDto("일치하지 않습니다~!");
        }
    }

    // 글 삭제
    // 비밀번호 맞는지 확인하고 삭제하기~!~ 비밀번호가 맞을 때, 틀렸을 때 -> 메시지 반환
    public PostResponseDto deletePost(Long id, String password) {
        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);

        // post 삭제
        if (post.getPassword().equals(password)) {
            postRepository.delete(post);
            return new PostResponseDto("삭제 완료 ㅎㅎ");
        } else {
            return new PostResponseDto("비밀번호 일치하지 않습니다~!");
        }
    }

    // 일치 여부 확인 코드 (중복 되는 코드)
    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 글은 존재하지 않습니다!!")
        );
    }
}
