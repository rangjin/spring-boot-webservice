package org.rangjin.springbootwebservice.web;

import lombok.RequiredArgsConstructor;
import org.rangjin.springbootwebservice.service.posts.PostsService;
import org.rangjin.springbootwebservice.web.dto.PostsResponseDto;
import org.rangjin.springbootwebservice.web.dto.PostsSaveRequestDto;
import org.rangjin.springbootwebservice.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    // bean 을 주입받는 3가지 방법(@Autowired, setter, 생성자 -> 가장 권장됨)
    // @RequiredArgsConstructor 를 통해 final 로 선언된 모든 필드를 인자값으로 받는 생성자 생성
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

}
