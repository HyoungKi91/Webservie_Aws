package com.hkkim.webservice.web;

import com.hkkim.webservice.service.posts.PostsService;
import com.hkkim.webservice.web.dto.PostsResponseDto;
import com.hkkim.webservice.web.dto.PostsSaveRequestDto;
import com.hkkim.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto postsSaveRequestDto){
        return postsService.save(postsSaveRequestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable("id") Long id , @RequestBody PostsUpdateRequestDto postsUpdateRequestDto){
        return postsService.update(id , postsUpdateRequestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable("id") Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable("id") Long id){
        postsService.delete(id);
        return id;
    }
}
