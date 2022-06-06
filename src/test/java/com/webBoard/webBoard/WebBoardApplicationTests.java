package com.webBoard.webBoard;

import com.webBoard.webBoard.repository.BoardDAO;
import com.webBoard.webBoard.service.BoardService;
import com.webBoard.webBoard.service.BoardServiceImp;
import com.webBoard.webBoard.vo.BoardVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class WebBoardApplicationTests {

	@Autowired
	BoardService boardService;

	@Autowired
	BoardDAO boardDAO;

	@Test
	void getCount() {
		int result = boardDAO.getCount("boardtitle", "");
		System.out.println("result = " + result);
	}

	@Test
	void getBoardView() {
		BoardVO bVo = boardDAO.getBoard(16);
	}

	@Test
	void insert() {
		String temp = "2022-02-20 16:35";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		Timestamp timestamp = Timestamp.valueOf(LocalDateTime.parse(temp, formatter));
		System.out.println("timestamp.toString() = " + timestamp.toString());
	}

}
