package com.psy7758.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.psy7758.dto.Notice;

public interface Service {
   ArrayList<Notice> getNotices() throws SQLException;                                 // 메서드명 변경.
   ArrayList<Notice> getNotices(String searchField, String searchWord) throws SQLException;   // 메서드명 변경.
   /*
    * list.jsp 페이지에서 목록을 클릭했을 때, 해당 목록에 대응되는 ID 를 통해 검색된 객체를 얻기 위한 추상 메서드 추가.
    */
   Notice getNotice(int id) throws SQLException;
}