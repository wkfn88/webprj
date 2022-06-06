package com.webBoard.webBoard.service;

import com.webBoard.webBoard.repository.RecommendDAO;
import com.webBoard.webBoard.vo.RecommendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendServiceImp implements RecommendService {

    private RecommendDAO recommendDAO;

    @Autowired
    public RecommendServiceImp(RecommendDAO recommendDAO) {
        this.recommendDAO = recommendDAO;
    }

    @Override
    public boolean insert(RecommendVO rVo) {
        String recommendId = rVo.getBoardNum() + rVo.getMemberId();
        rVo.setRecommendId(recommendId);
        recommendDAO.insert(rVo);
        return false;
    }

    @Override
    public boolean check(RecommendVO rVo) {
        String recommendId = rVo.getBoardNum() + rVo.getMemberId();
        rVo.setRecommendId(recommendId);
        String result = recommendDAO.check(rVo);
        if( result == null ) return true;
        return false;
    }
}
