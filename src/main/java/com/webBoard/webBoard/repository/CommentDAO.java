package com.webBoard.webBoard.repository;

import com.webBoard.webBoard.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDAO {
    public List<CommentVO> getList(@Param("boardNum") int boardNum); //댓글 리스트

    public Integer getNext(@Param("boardNum") int boardNum); //특정 게시물에 달린 댓글의 마지막 순번

    public int insert(CommentVO cVo); //댓글입력

    public int delete(@Param("commentNum") String commentNum); //댓글 삭제

    public CommentVO get(@Param("commentNum") String commentNum); //댓글내용
}
