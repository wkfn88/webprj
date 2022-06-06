package com.webBoard.webBoard;

import com.webBoard.webBoard.repository.BoardDAO;
import com.webBoard.webBoard.repository.CommentDAO;
import com.webBoard.webBoard.service.BoardService;
import com.webBoard.webBoard.service.CommentService;
import com.webBoard.webBoard.vo.BoardVO;
import com.webBoard.webBoard.vo.CommentVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
class CommentServiceTest {

	@Autowired
	CommentService commentService;
	
	@Autowired
	CommentDAO commentDAO;
	
	@Test
	void get() {
		List<CommentVO> list = commentDAO.getList(1);
		System.out.println("list.size() = " + list.size());
	}

	@Test
	void getNext() {
		System.out.println(commentService);
	}



	@Test
	void insert() {
		CommentVO cVo = new CommentVO();
		cVo.setBoardNum(17);
		cVo.setMemberId("ㅇㅇ");
		cVo.setCommentContent("content");
		cVo.setCommentPwd("1234");
		cVo.setAnonymous(0);

		System.out.println("commentService.insert() = " + commentService.insert(cVo));

	}

}
