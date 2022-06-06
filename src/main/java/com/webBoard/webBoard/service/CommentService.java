package com.webBoard.webBoard.service;

import com.webBoard.webBoard.vo.CommentVO;

import java.util.List;

public interface CommentService {
    public List<CommentVO> getList(int boardNum);

    public CommentVO get(String commentNum);

    public boolean insert(CommentVO cVo);

    public int delete(String commentNum);

}
