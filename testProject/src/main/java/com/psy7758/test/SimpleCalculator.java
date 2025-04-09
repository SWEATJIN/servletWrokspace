package com.psy7758.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/simpleCalculator")
public class SimpleCalculator extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   /*
    * 최초 접속시에는 getSession 메서드의 인수를 sessionCheck(true) 로 지정함으로써,
    * 세션이 존재하면 기존 세션을 반환받고, 존재하지 않으면 새로운 세션을 반환받도록 설정.
    * service 메서드의 지역변수로 선언시 항상 true 로만 초기화되는 문제점으로 인해 service
    * 메서드 호출 완료후에도 값이 유지되도록 하기 위해 필드로 선언.  
    */
   boolean sessionCheck = true;

   protected void service(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String op = request.getParameter("operator");
      
      /*
       * 클릭한 연산자가 "+" 인 경우에는 세션이 만료되어도 새로운 세션이 생성되도록 하지만, "=" 연산자를
       * 클릭한 경우에는 세션이 만료되어도 새로운 세션이 반환되지 않도록 설정.
       */
      if (op.equals("+")) {
         sessionCheck = true;
      } else {
         sessionCheck = false;
      }
      
      /*
       *       < getSession 메서드의 인수 >
       * 
       * - getSession(true) : 세션이 존재하면 기존 세션을 반환하고, 존재하지 않으면 새로운 세션을 생성.
       *                   로그인 후 세션을 반드시 유지해야하는 경우에 유용.
       *                    ( 인수 미지정시 디폴트 true )
       * 
       * - getSession(false) : 세션이 존재하면 기존 세션을 반환하지만, 존재하지 않으면 null 을 반환.
       *                    API 요청에서 불필요한 세션 생성 방지를 위해 활용.
       *                     - 세션이 null 된다고 해서 서버 접속이 다운되는 것은 아니지만, null
       *                         인 세션을 사용하려는 시점에서 예외 발생.
       *                         아래 예시에서는 세션이 null 이 된 상태에서 세션에 대한 null 체크를
       *                         하지 않고 getAttribute 메서드 호출시 예외 발생.
       * 
       */
      HttpSession session = request.getSession(sessionCheck);
      
      if( session == null ) {
         response.sendRedirect("sessionOut");
      } else {
         int num = 0;
         String num_ = request.getParameter("num");
         
         if (!num_.equals("")) {
            num = Integer.parseInt(num_);
            
            Object tot_ = session.getAttribute("tot");
            session.setAttribute("tot", tot_ == null ? num : (int) tot_ + num);
         }
         
         response.sendRedirect("redirectionPage");
      }
   }
}