package com.webBoard.webBoard.service;

import com.webBoard.webBoard.vo.RecommendVO;

public interface RecommendService {
    public boolean insert(RecommendVO rVo);
    public boolean check(RecommendVO rVo);
}
