package com.psy7758.test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/oddEven_counting")
public class OddEven_counting extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   /*
    *       < 리디렉션(Redirection) >
    * 
    * - 클라이언트에게 새로운 URL 로 이동토록 알려주는 것을 의미하는 것으로써, 클라이언트는
    *   이동해야 할 새로운 URL 을 서버로부터 받아 새로운 요청을 해당 URL 로 전송.
    *   리디렉션은 주로 다른 도메인 또는 다른 애플리케이션으로의 이동에 사용.
    *   따라서 리다이렉션은 클라이언트에게 새로운 요청을 보내도록 유도하는 것이으로
    *   리디렉션의 대상은 항상 응답객체(response)가 됨.
    *   
    * ======================================================================
    * 
    *       < 포워딩(Forwarding) >
    * 
    * -  서버 내에서의 페이지 이동을 의미하는 것으로써, 클라이언트는 이러한 동작을 인지하지 못하고
    *    모든 처리가 서버 측에서 이루어지는 특성.
    *    주로 동일한 웹 애플리케이션 내에서 서버에서 서버로의 내부 이동에 사용됨으로써, 클라이언트는
    *    최초의 요청 URL 을 유지한 채로 새로운 페이지 결과를 수신.
    *    따라서 리디렉션의 대상은 항상 요청객체(request)가 됨.
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String repeatNum_ = request.getParameter("repeatNum");
      
      int repeatNum = 0;
      String result;
      
      if( !repeatNum_.isEmpty() ) repeatNum = Integer.parseInt(repeatNum_);
      
      if( repeatNum % 2 == 0 ) {
         result = "짝수번";
      } else {
         result = "홀수번";
      }
      
      /*
       * 요청객체로부터 호출된 getRequestDispatcher 메서드를 통해 지정한 JSP 페이지
       * (실제는 변환된 서블릿 자바 페이지)로 요청과 응답을 전달하기 위한 RequestDispatcher
       * 인터페이스 객체를 반환받고, 반환된 RequestDispatcher 객체를 통해 forward 메서드를
       * 호출함으로써, 실제로 지정한 JSP 페이지로 요청과 응답을 전달.
       */
      RequestDispatcher dispatcher = request.getRequestDispatcher("oddEven_counting.jsp");
      
      /*
       * 해당 JSP 페이지로 요청과 응답을 전달하기 위한 RequestDispatcher 객체를
       * 얻되, 요청객체의 setAttribute 메서드를 통해 요청객체에 실제 전송할 데이터를 저장.
       * setAttribute 메서드를 통한 데이터 저장은 실제 forward 를 통해 전이 및 전송되므로
        * getRequestDispatcher 메서드 호출 이전에 미리 setAttribute 메서드를 통해 데이터
         * 저장은 무방.
       */
      request.setAttribute("result", result);
      request.setAttribute("repeatNum", repeatNum);
      
      /*
       * RequestDispatcher 객체의 forward 메서드를 호출함으로써, 요청과 응답 객체를
       * 포함하여 해당 JSP 페이지로 이동.
       */
      dispatcher.forward(request, response);
//      request.getRequestDispatcher("oddEven_counting.jsp").forward(request, response);
   }
}