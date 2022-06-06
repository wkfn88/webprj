package com.webBoard.webBoard.service;

import com.webBoard.webBoard.repository.CommentDAO;
import com.webBoard.webBoard.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImp implements CommentService{

    private CommentDAO commentDAO;

    @Autowired
    public CommentServiceImp(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public List<CommentVO> getList(int boardNum) {
        List<CommentVO> list = commentDAO.getList(boardNum);
        if(list.isEmpty()) return null;
        return list;
    }

    @Override
    public CommentVO get(String commentNum) {
        CommentVO cVo = commentDAO.get(commentNum);
        return cVo;
    }

    @Override
    public boolean insert(CommentVO cVo) {
        try {
            Integer commentNext = commentDAO.getNext(cVo.getBoardNum());

            if(commentNext == null) {
                commentNext = 1;
            }else {
                commentNext = commentNext + 1;
            }

            String commentNum = cVo.getBoardNum() + "" + commentNext;
            cVo.setCommentNext(commentNext);
            cVo.setCommentNum(commentNum);

            commentDAO.insert(cVo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int delete(String commentNum) {
        int result = commentDAO.delete(commentNum);
        return result;
    }
}
