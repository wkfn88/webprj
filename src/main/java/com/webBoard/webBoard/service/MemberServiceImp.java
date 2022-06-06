package com.webBoard.webBoard.service;

import com.webBoard.webBoard.repository.MemberDAO;
import com.webBoard.webBoard.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImp implements MemberService{

    MemberDAO memberDAO;

    @Autowired
    public MemberServiceImp(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public MemberVO get(String memberId) {
        MemberVO mVo = memberDAO.get(memberId);
        if( mVo == null ) return null;
        return mVo;
    }

    @Override
    public boolean insert(MemberVO mVo) {
        try{
            memberDAO.insert(mVo);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean check(MemberVO mVo) {
        MemberVO temp = memberDAO.get(mVo.getMemberId());
        if( temp == null ) return false;
        if(temp.getMemberPwd().equals(mVo.getMemberPwd())) return true;

        return false;
    }


}
