package Gachon.ComunityBoard.controller;


import Gachon.ComunityBoard.config.auth.LoginUser;
import Gachon.ComunityBoard.config.auth.dto.SessionUser;
import Gachon.ComunityBoard.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

//    private final PostsService postsService;
//    private final HttpSession httpSession;
//
//    @GetMapping("/")
//    public String index(Model model, @LoginUser SessionUser user){
//        model.addAttribute("posts", postsService.paging(Pageable.ofSize(5)));
//
//        // user세션 정보가 있을때는 값을 넘겨줌
//        if(user != null){
//            model.addAttribute("userName",user.getName());
//        }
//
//        return "index";
//    }

}
