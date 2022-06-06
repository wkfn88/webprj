package com.webBoard.webBoard.controller;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webBoard.webBoard.service.MemberService;
import com.webBoard.webBoard.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/regist")
    public String regist() {

        return "regist";
    }

    @PostMapping("/id")
    public @ResponseBody String id(@RequestBody String memberId) {
        JsonObject obj =  JsonParser.parseString(memberId).getAsJsonObject();
        String id = obj.get("memberId").getAsString();
        if( memberService.get(id) == null ) {
            return "사용 가능한 아이디 입니다.";
        } else {
            return "중복된 아이디가 있습니다.";
        }
    }

    @PostMapping("/registEvent")
    public String registEvent(Model model, MemberVO mVo) {

            String registResult = "";

            if( memberService.insert(mVo) ) {
                registResult = "registSuccess";
            }else {
                registResult = "registFail";
            }

            model.addAttribute("result", registResult);

        return "result";
    }

    @PostMapping("/login")
    public @ResponseBody String login(@RequestBody MemberVO mVo, HttpSession session) {

        if( memberService.check(mVo) ) {
            MemberVO member = memberService.get(mVo.getMemberId());

            if(member.getStatus() == 1) session.setAttribute("loginType", "admin");
            session.setAttribute("loginUser", mVo.getMemberId());

            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
