package com.psy7758.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psy7758.context.ServletContextHolder;

@WebFilter("/*")
public class CORS_ServletFilter implements Filter {
   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
         throws IOException, ServletException {
      
      /*
       * ServletRequest 과 ServletResponse 는 HTTP 요청과 관련된 getHeader 와 setHeader 등의 메서드가
       * 존재하지 않음에 따라, 아래와같이 HttpServletRequest 와 HttpServletResponse 로 하향 캐스팅.
       * ( 클래스 계층 보기 : Ctrl + t )
       */
      HttpServletRequest request_ = (HttpServletRequest)request;
      HttpServletResponse response_ = (HttpServletResponse)response;
      
      /*
       * HttpServlet 으로부터 상속된 서블릿이 아니므로, HttpServlet 의 getServletContext() 메서드 직접
       * 호출 불가.
       * 따라서 서블릿이 아닌 클래스에서 손쉽게 ServletContext 를 얻을 수 있도록 구현한 ServletContextHolder 활용.
       */
      if (ServletContextHolder.getServletContext().getInitParameter("react_env").equals("development")) {
         response_.setHeader("Access-Control-Allow-Origin", request_.getHeader("Origin"));
         response_.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS");
         response_.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

         System.out.println("개발 환경 CORS 활성화!!");
      }
      
      filterChain.doFilter(request, response);
   }
}