package com.psy7758.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import com.psy7758.dto.Notice;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public abstract class CommonModule implements Dao {
   private static final HikariConfig config = new HikariConfig();
   private static HikariDataSource dataSource;

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

   public ArrayList<Notice> getNoticesDb(String selectSql, String searchWord) throws SQLException {   // 메서드명 변경.
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
   
   /*
    * JDBC 에 모두 호환되는 SQL 이므로 각 JDBC 별 설정이 아닌, 공통 모듈(CommonModule)에서
    * 하나의 메서드로 통합 처리가 가능하지만, 서비스 계층( AdminService, UserService ) 에서
    * JDBC 별 통합 수신을 위한 Dao 인터페이스로 참조해야 하는 이유로, 어쩔수 없이 Dao 인터페이스에
    * 추상 메서드 추가로 인한 각 JDBC 별 오버라이딩 메서드를 추가하고, 해당 메서드에서 공통 모듈의
    * 당 메서드 호출하여 실행하도록 설정.
    * 
    * ※ 단, Notice DTO 객체의 id 필드를 String 으로 전환하면, 상기 getNoticesDb 메서드를
    *   그대로 재사용 가능하므로, Dao 인터페이스에 추가적인 추상 메서드나 하위 계층 및 서비스 계층에서의
    *   오버라이딩 메서드가 추가적으로 필요없지만, getNoticesDb 메서드에서 ArrayList<Notice>
    *   타입으로 반환하므로 실제 사용하는 서비스 계층에서 다시 인덱싱을 해야하는 단점 발생.
    */
   public Notice getNoticeDb(int id) throws SQLException {
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