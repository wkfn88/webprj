package com.webBoard.webBoard.repository;

import com.webBoard.webBoard.vo.RecommendVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecommendDAO {

    public void insert(RecommendVO rVo); //추천 테이블에 행추가
    public String check(RecommendVO rVo); //해당 게시물에 유저가 추천을 했는가

}
