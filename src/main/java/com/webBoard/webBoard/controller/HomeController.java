package com.webBoard.webBoard.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.webBoard.webBoard.service.BoardService;
import com.webBoard.webBoard.service.CommentService;
import com.webBoard.webBoard.vo.BoardVO;
import com.webBoard.webBoard.vo.CommentVO;
import com.webBoard.webBoard.vo.MemberVO;
import com.webBoard.webBoard.vo.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String home(Model model) {
        List<BoardVO> recentList = boardService.getRecentList();
        List<BoardVO> recoList = boardService.getRecommendList();

        model.addAttribute("recentList", recentList);
        model.addAttribute("recoList", recoList);
        return "main";
    }

    @GetMapping("/template")
    public String template() {
        return "template";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/testGet")
    public @ResponseBody MemberVO testGet(@RequestBody MemberVO mVo) {

        return mVo;
    }

    @PostMapping("/testGet2")
    public @ResponseBody String testGet2() {
        List<CommentVO> list = commentService.getList(17);
        String json = new Gson().toJson(list);
        System.out.println(json);
        return json;
    }
}
