package com.webBoard.webBoard.repository;

import com.webBoard.webBoard.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDAO {

    public int insert(MemberVO mVo); // 회원가입
    public MemberVO get(@Param("memberId") String memberId); // 사용자 정보 가져오기
}
