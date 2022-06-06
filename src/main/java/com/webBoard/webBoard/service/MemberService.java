package com.webBoard.webBoard.service;

import com.webBoard.webBoard.vo.MemberVO;

public interface MemberService {
    public MemberVO get(String memberId);
    public boolean insert(MemberVO mVo);
    public boolean check(MemberVO mVo);
}
