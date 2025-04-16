package com.psy7758.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import com.psy7758.context.ServletContextHolder;
import com.psy7758.dao.CommonModule;
import com.psy7758.dto.Notice;

public class MariaDao extends CommonModule {
   private static ServletContext context = ServletContextHolder.getServletContext();
   
   public MariaDao() {
      super(
            context,
            context.getInitParameter("maria_driver"),
            context.getInitParameter("maria_url"),
            context.getInitParameter("maria_userName"),
            context.getInitParameter("maria_psw")
      );
   }
   
   @Override
   public ArrayList<Notice> getNotices(String searchField, String searchWord, boolean pub) throws SQLException {   // 메서드명 변경.
      String selectSql = String.format("SELECT @rowNum := @rowNum + 1 num, notice.* "
            + "FROM notice, ( SELECT @rowNUm := 0 ) rn "
            + "WHERE %s LIKE ? %s "
            + "ORDER BY regDate DESC", searchField, pub ? "" : "AND pub = 1");                  // DESC 추가.
      
      return getNoticesDb(selectSql, searchWord);
   }
   
   // 오버라이딩 메서드 추가.
   @Override
   public Notice getNotice(int id) throws SQLException {
      return getNoticeDb(id);
   }
   
   @Override
   public int setPub(String id) throws SQLException {
      String updateSql = String.format("UPDATE client set pub = 1 WHERE id = ?");
      
      return setPubDb(updateSql, id);
   }
}