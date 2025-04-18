package com.psy7758.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import com.psy7758.context.ServletContextHolder;
import com.psy7758.dto.Notice;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public abstract class CommonModule implements Dao {
   private static final HikariConfig config = new HikariConfig();
   private static HikariDataSource dataSource;
   
   /*
    * 공지사항 페이지에서 기본적으로 페이징할 레코드 갯수(10)를 초기 파라미터를 통해 수신하여 정적 필드값으로 설정.
    * 개별 DBMS DAO 에서 쿼리시 페이징 사이즈를 알아야 하므로 CommonModule 에서 통합하여 얻도록 설정.
    */
   private static int pagingSizeValue = Integer.parseInt(
         ServletContextHolder.getServletContext().getInitParameter("pagingSizeValue")
   );

   public CommonModule(ServletContext context, String driver, String url, String user_name, String psw) {
      synchronized (CommonModule.class) {
         if (dataSource == null) {
            config.setDriverClassName(driver);
            config.setJdbcUrl(url);
            config.setUsername(user_name);
            config.setPassword(psw);
            dataSource = new HikariDataSource(config);

            context.setAttribute("dataSource", dataSource);
            context.setAttribute("closedJdbcUrl", url);
         }
      }
   }
   
   // 공지사항 페이지에서 기본적으로 페이징할 레코드 갯수(10)를 상속 계층에서 얻기 위한 public 메서드 설정.
   public static int getPagingSizeValue() {
      return pagingSizeValue;
   }

   public ArrayList<Notice> getNoticesDb(String selectSql, String searchWord) throws SQLException {
      try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
         preparedStatement.setString(1, "%" + searchWord + "%");

         ArrayList<Notice> notices = new ArrayList<Notice>();
         try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
               Notice notice = new Notice();

               notice.setId(resultSet.getInt("id"));
               notice.setTitle(resultSet.getString("title"));
               notice.setWriter_id(resultSet.getString("writer_id"));
               notice.setContent(resultSet.getString("content"));
               notice.setRegDate(resultSet.getTimestamp("regDate").toLocalDateTime());
               notice.setHit(resultSet.getInt("hit"));
               notice.setFiles(resultSet.getString("files"));

               notices.add(notice);
            }
         }

         return notices;
      }
   }
   
   public int getNoticeCntDb(String selectSql, String searchWord) throws SQLException {
      try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
         preparedStatement.setString(1, "%" + searchWord + "%");
         
         int selectCnt = 0;
         
         try (ResultSet resultSet = preparedStatement.executeQuery()) {
               resultSet.next();
               selectCnt = resultSet.getInt("cnt");
         }

         return selectCnt;
      }
   }
   
   
   public Notice getCurrentNoticeDb(int id) throws SQLException {         // 메서드명 변경.
      String selectSql = "SELECT * FROM notice WHERE id LIKE ?";
      
      try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
         preparedStatement.setInt(1, id);

         Notice notice = new Notice();
         try (ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            
            notice.setId(resultSet.getInt("id"));
            notice.setTitle(resultSet.getString("title"));
            notice.setWriter_id(resultSet.getString("writer_id"));
            notice.setContent(resultSet.getString("content"));
            notice.setRegDate(resultSet.getTimestamp("regDate").toLocalDateTime());
            notice.setHit(resultSet.getInt("hit"));
            notice.setFiles(resultSet.getString("files"));
         }

         return notice;
      }
   }

   public int setPubDb(String updateSql, String id) throws SQLException {
      try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
         preparedStatement.setString(1, id);
         
         int row = preparedStatement.executeUpdate();

         return row;
      }
   }
}