package com.webBoard.webBoard.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webBoard.webBoard.repository.BoardDAO;
import com.webBoard.webBoard.vo.BoardVO;
import com.webBoard.webBoard.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BoardServiceImp implements BoardService{

    private BoardDAO boardDAO;
    private CommentService commentService;

    @Autowired
    public BoardServiceImp(BoardDAO boardDAO, CommentService commentService) {
        this.boardDAO = boardDAO;
        this.commentService = commentService;
    }

    @Override
    public List<BoardVO> getList(String type, String searchWord, int pageNum, int status) {
        List<BoardVO> list = null;

        if(pageNum == 0) {
            list = boardDAO.getList(type, searchWord, pageNum, status);
        }else {
            list = boardDAO.getList(type, searchWord, (pageNum - 1) * 11, status);
        }

        if( list.isEmpty() ) return null;

        for( int i = 0; i < list.size(); i++ ) {
            list.get(i).setConvertDate(list.get(i).getBoardDate().toString().substring(0, 16));
        }

        return list;
    }

    @Override
    public List<BoardVO> getNoticeList() {
        List<BoardVO> list = getList("boardTitle", "", 0, 3);
        return list;
    }

    @Override
    public List<BoardVO> getRecentList() {
        List<BoardVO> list = boardDAO.getRecentList();
        if(list.isEmpty()) return null;
        return list;
    }

    @Override
    public List<BoardVO> getRecommendList() {
        List<BoardVO> list = boardDAO.getRecommendList();
        if(list.isEmpty()) return null;
        return list;
    }

    @Override
    public int getCount(String type, String searchWord) {
        int pageCount = boardDAO.getCount(type, searchWord);

        if( pageCount == 0 ) {
            pageCount = 1;
        }else if( pageCount % 11 == 0 ) {
            pageCount = pageCount / 11;
        }else {
            pageCount = (pageCount / 11) + 1;
        }

        return pageCount;
    }

    @Override
    public int getStart(int pageNum){
        int Start = 1;

        if( pageNum == 0 ) {
            return Start;
        } else if( pageNum % 5 == 0 ) {
            Start = pageNum - 4;
        } else {
            Start = pageNum - (pageNum % 5 - 1);
        }

        return Start;
    }

    @Override
    public BoardVO getNotice() {
        List<BoardVO> list = boardDAO.getList("boardtitle", "", 0, 3);
        if( list.isEmpty() ) return null;
        return list.get(0);
    }

    @Override
    public BoardVO get(int boardNum) {
        BoardVO bVo = boardDAO.getBoard(boardNum);
        return bVo;
    }

    @Override
    public int insert(BoardVO bVo) {
        Integer boardNum = boardDAO.getLastNum();

        if( boardNum == null ) {
            boardNum = 1;
        }else {
            boardNum = boardNum + 1;
        }

        bVo.setBoardNum(boardNum);

        int result = boardDAO.insert(bVo);
        return result;
    }

    @Override
    public int delete(int boardNum) {
        int result = boardDAO.delete(boardNum);
        return result;
    }

    @Override
    public String modal(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        String modalType = obj.get("modalType").getAsString();
        int boardNum = obj.get("boardNum").getAsInt();
        String pwd = obj.get("pwd").getAsString();

        BoardVO bVo = get(boardNum);

        if( modalType.equals("comment") ) {
            String commentNum = obj.get("commentNum").getAsString();
            CommentVO cVo = commentService.get(commentNum);
            if( cVo.getCommentPwd().equals(pwd) ) {
                commentService.delete(commentNum);
                return "comment";
            }
        } else if( bVo.getBoardPwd().equals(pwd) ) {
            if(modalType.equals("delete")) {
                delete(boardNum);
                return "board";
            }else if(modalType.equals("update")) {
                return "update";
            }
        } else {
            return "fail";
        }
        return "fail";
    }


    @Override
    public int update(BoardVO bVo) {
        int result = boardDAO.update(bVo);
        return result;
    }

    @Override
    public void readCountUpdate(int boardNum) {
        boardDAO.updateReadCount(boardNum);
    }
}
