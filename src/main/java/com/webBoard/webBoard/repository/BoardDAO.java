package com.webBoard.webBoard.repository;

import com.webBoard.webBoard.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardDAO {


    public Integer getLastNum(); //게시물의 마지막 번호를 리턴

    public void updateReadCount(@Param("boardNum") int boardNum); //조회수 갱신

    public Integer getCount(@Param("type") String type, @Param("searchWord") String searchWord); //개시물의 총 개수

    public List<BoardVO> getList(@Param("type") String type, @Param("searchWord") String searchWord,
                                 @Param("pageNum") int pageNum, @Param("status") int status); //검색시 게시글의 목록

    public List<BoardVO> getRecentList(); //최근 게시물 5개 가져오기

    public List<BoardVO> getRecommendList(); //오늘자 게시물 추천순으로 가져오기

    public BoardVO getBoard(int boardNum); //게시글 상세보기

    public int insert(BoardVO bVo); //글쓰기

    public int delete(int boardNum); //글지우기

    public int update(BoardVO bVo); //글수정하기
}
