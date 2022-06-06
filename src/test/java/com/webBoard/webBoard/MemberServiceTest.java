package com.webBoard.webBoard;

import com.webBoard.webBoard.repository.CommentDAO;
import com.webBoard.webBoard.repository.MemberDAO;
import com.webBoard.webBoard.service.CommentService;
import com.webBoard.webBoard.service.MemberService;
import com.webBoard.webBoard.vo.CommentVO;
import com.webBoard.webBoard.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Member;
import java.util.List;

@SpringBootTest
class MemberServiceTest {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberDAO memberDAO;

	@Test
	void insert() {
		MemberVO mVo = new MemberVO();
		mVo.setMemberId("운영자2");
		mVo.setMemberPwd("1234");
		mVo.setMemberEmail("asdd");
		mVo.setStatus(0);

	}

	@Test
	void check() {
		MemberVO mVo = new MemberVO();
		mVo.setMemberId("wkfn888");
		mVo.setMemberPwd("1234");


	}

}
