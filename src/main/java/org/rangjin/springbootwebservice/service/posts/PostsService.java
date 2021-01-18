package org.rangjin.springbootwebservice.service.posts;

import lombok.RequiredArgsConstructor;
import org.rangjin.springbootwebservice.domain.posts.Posts;
import org.rangjin.springbootwebservice.domain.posts.PostsRepository;
import org.rangjin.springbootwebservice.web.dto.PostsResponseDto;
import org.rangjin.springbootwebservice.web.dto.PostsSaveRequestDto;
import org.rangjin.springbootwebservice.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
// 서비스 메소드는 트랜잭션과 도메인 간의 순서만 보장함(비즈니스 로직 처리는 도메인 계층에서)
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

}
