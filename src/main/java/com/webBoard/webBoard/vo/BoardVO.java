package com.webBoard.webBoard.vo;

import java.sql.Timestamp;

public class BoardVO {
    private int boardNum;
    private String boardTitle;
    private String memberId;
    private String boardPwd;
    private String boardContent;
    private int anonymous;
    private int status;
    private Timestamp boardDate;
    private String convertDate;
    private int readCount;
    private int comment;
    private int recoCount;

    public String getConvertDate() {
        return convertDate;
    }

    public void setConvertDate(String convertDate) {
        this.convertDate = convertDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRecoCount() {
        return recoCount;
    }

    public void setRecoCount(int recoCount) {
        this.recoCount = recoCount;
    }

    public int getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBoardPwd() {
        return boardPwd;
    }

    public void setBoardPwd(String boardPwd) {
        this.boardPwd = boardPwd;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public Timestamp getBoardDate() {
        return boardDate;
    }

    public void setBoardDate(Timestamp boardDate) {
        this.boardDate = boardDate;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(int anonymous) {
        this.anonymous = anonymous;
    }
}
