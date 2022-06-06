package com.webBoard.webBoard.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webBoard.webBoard.service.BoardService;
import com.webBoard.webBoard.service.BoardServiceImp;
import com.webBoard.webBoard.service.CommentService;
import com.webBoard.webBoard.service.RecommendService;
import com.webBoard.webBoard.vo.BoardVO;
import com.webBoard.webBoard.vo.CommentVO;
import com.webBoard.webBoard.vo.RecommendVO;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    private BoardService boardService;
    private CommentService commentService;
    private RecommendService recommendService;

    @Autowired
    public BoardController(BoardService boardService,
                           CommentService commentService,
                           RecommendService recommendService) {
        this.boardService = boardService;
        this.commentService = commentService;
        this.recommendService = recommendService;
    }

    @GetMapping("/")
    public String board(Model model,
                        @RequestParam(value = "type", required = false) String type,
                        @RequestParam(value = "searchWord", required = false) String searchWord,
                        @RequestParam(value = "pageNum", required = false) Integer pageNum) {

        if(type == null) type = "boardtitle";
        if(searchWord == null) searchWord = "";
        if(pageNum == null) pageNum = 0;

        List<BoardVO> list = boardService.getList(type, searchWord, pageNum, 1);

        //getCount() : 페이지 수, getStart() : 페이징 단위가 바뀔때 시작점
        int pageCount = boardService.getCount(type, searchWord);
        int pageStart = boardService.getStart(pageNum);

        //최근 공지사항
        BoardVO notice = boardService.getNotice();

        model.addAttribute("pageCount", pageCount);
        model.addAttribute("pageStart", pageStart);
        model.addAttribute("list", list);
        model.addAttribute("type", type);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("notice", notice);
        return "board";
    }

    @GetMapping("/view")
    public String View(Model model,
                       @RequestParam("boardNum") Integer boardNum) {
        BoardVO board = boardService.get(boardNum);
        List<CommentVO> comList = commentService.getList(boardNum);
        boardService.readCountUpdate(boardNum);

        model.addAttribute("comList", comList);
        model.addAttribute("board", board);

        return "boardView";
    }

    @GetMapping("/writeForm")
    public String writeFrom() {
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(BoardVO bVo, HttpSession session) {
        String loginUser = (String)session.getAttribute("loginUser");
        boardService.insert(bVo);
        return "redirect:/board/";
    }

    @PostMapping("/comment")
    public String comment(CommentVO cVo) {
        commentService.insert(cVo);
        return "redirect:/board/view?boardNum=" + cVo.getBoardNum();
    }

    @PostMapping("/delete")
    public @ResponseBody String delete(@RequestBody String request,
                                       HttpSession session) {
        String loginUser = (String)session.getAttribute("loginUser");
        JsonObject json = JsonParser.parseString(request).getAsJsonObject();

        String memberId = json.get("memberId").getAsString();
        int boardNum = json.get("boardNum").getAsInt();

        if( memberId.equals(loginUser) ) {
            boardService.delete(boardNum);
            return "success";
        }else {
            return "fail";
        }
    }

    @PostMapping("/modal")
    public @ResponseBody String modal(@RequestBody String json) {
        String result = boardService.modal(json);
        return result;
    }

    @PostMapping("/recommend")
    public @ResponseBody String recommend(@RequestBody RecommendVO rVo) {
        Map<String, Object> map = new HashMap<>();
        String json = "";

        if( recommendService.check(rVo) ) {
            recommendService.insert(rVo);
            int recoCount = boardService.get(rVo.getBoardNum()).getRecoCount();
            map.put("result", "success");
            map.put("recoCount", recoCount);
            json = new Gson().toJson(map);
        }else {
            map.put("result", "fail");
            json = new Gson().toJson(map);
        }
        return json;
    }

    @GetMapping("/update")
    public String update(@RequestParam int boardNum,
                         Model model,
                         HttpSession session) {
        String loginUser = (String)session.getAttribute("loginUser");
        BoardVO bVo = boardService.get(boardNum);

        if( bVo.getAnonymous() == 1 ) {
            if( !bVo.getMemberId().equals(loginUser) )
                return "error";

            model.addAttribute("board", bVo);
        }else {
            model.addAttribute("board", bVo);
        }
        return "update";
    }

    @PostMapping("/updateDo")
    public String updateDo(BoardVO bVo) {

        boardService.update(bVo);

        return "redirect:/board/view?boardNum="+bVo.getBoardNum();
    }

    @GetMapping("/deleteComment")
    public String deleteComment(@RequestParam("commentNum") String commentNum,
                                HttpSession session) {
        System.out.println("commentNum = " + commentNum);
        String loginUser = (String)session.getAttribute("loginUser");
        CommentVO cVo = commentService.get(commentNum);

        if(cVo.getAnonymous() == 1) {
            if( !cVo.getMemberId().equals(loginUser) ) {
                return "error";
            }
            commentService.delete(commentNum);
        }else {
            commentService.delete(commentNum);
        }
        return "redirect:/board/view?boardNum="+cVo.getBoardNum();
    }

    @GetMapping("/notice")
    public String notice(Model model) {
        List<BoardVO> list = boardService.getNoticeList();
        model.addAttribute("list", list);
        return "notice";
    }

    @GetMapping("/noticeWrite")
    public String noticeWrite() {
        return "noticeWrite";
    }

}
