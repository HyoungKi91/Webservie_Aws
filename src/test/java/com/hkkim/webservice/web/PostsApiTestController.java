package com.hkkim.webservice.web;

import com.hkkim.webservice.domain.posts.Posts;
import com.hkkim.webservice.domain.posts.PostsRepository;
import com.hkkim.webservice.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiTestController {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void posts_등록된다() throws Exception{
        String title = "title";
        String content = "content";
        PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("test@naver.com")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url , postsSaveRequestDto , Long.class);

        Assertions.assertEquals(responseEntity.getStatusCode() , HttpStatus.OK);

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);

        Assertions.assertEquals(posts.getContent() , content);
        Assertions.assertEquals(posts.getTitle() , title);
    }
}
