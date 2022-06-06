package com.webBoard.webBoard.service;

import com.webBoard.webBoard.vo.BoardVO;

import java.util.List;

public interface BoardService {
    //게시물 목록 조회
    public List<BoardVO> getList(String type, String searchWord, int pageNum, int status);
    public List<BoardVO> getNoticeList();
    public List<BoardVO> getRecentList();
    public List<BoardVO> getRecommendList();

    //페이지네이션
    public int getCount(String type, String searchWord);
    public int getStart(int pageNum);
    public BoardVO getNotice();

    //게시글 crud
    public BoardVO get(int boardNum);
    public int insert(BoardVO bVo);
    public int delete(int boardNum);
    public int update(BoardVO bVo);
    public String modal(String json);
    public void readCountUpdate(int boardNum);

}
