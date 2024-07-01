package com.hkkim.webservice.web;

import com.hkkim.webservice.config.auth.LoginUser;
import com.hkkim.webservice.config.dto.SessionUser;
import com.hkkim.webservice.service.posts.PostsService;
import com.hkkim.webservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model , @LoginUser SessionUser user){
        model.addAttribute("posts" , postsService.findAllDesc());

        if(user != null) {
            model.addAttribute("userName" , user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable("id") Long id , Model model){
        PostsResponseDto postsResponseDto = postsService.findById(id);
        model.addAttribute("post", postsResponseDto);
        return "posts-update";
    }
}
