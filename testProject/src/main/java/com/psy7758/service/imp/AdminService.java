package com.psy7758.service.imp;

import java.sql.SQLException;
import java.util.ArrayList;

import com.psy7758.dao.Dao;
import com.psy7758.dao.imp.MariaDao;
import com.psy7758.dao.imp.MysqlDao;
import com.psy7758.dto.Notice;
import com.psy7758.service.Service;

public class AdminService implements Service{
//   private Dao dao = new MysqlDao();
   private Dao dao = new MariaDao();

   @Override
   public ArrayList<Notice> getNotices() throws SQLException {         // 메서드명 변경.
      return getNotices("id", "");
   }
   
   @Override
   public ArrayList<Notice> getNotices(String searchField, String searchWord) throws SQLException {      // 메서드명 변경.
      return dao.getNotices(searchField, searchWord, true);
   }
   
   /*
    * list.jsp 페이지에서 목록을 클릭했을 때, 해당 목록에 대응되는 ID 를 통해 검색된 객체를 얻기 위한 메서드 추가.
    */
   @Override
   public Notice getNotice(int id) throws SQLException {
      return dao.getNotice(id);
   }
   
   // 관리자에게만 제공되는 기능 - 관리자에만 적용되는 확장 메서드. 
   public int setPub(String id) throws SQLException {
      return dao.setPub(id);
   }
}