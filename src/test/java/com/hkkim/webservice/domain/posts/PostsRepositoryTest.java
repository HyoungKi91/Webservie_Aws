package com.hkkim.webservice.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        String title = "테스트 게시글";
        String content = "테스트 본문";
        postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("test@naver.com")
                        .build());

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);

        Assertions.assertEquals(posts.getTitle() , title);
        Assertions.assertEquals(posts.getContent() , content);

    }
}
