package com.psy7758.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class CharSetServletFilter implements Filter {
   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
         throws IOException, ServletException {
      request.setCharacterEncoding("utf-8");

      String path = ((HttpServletRequest) request).getServletPath();

      try {
         // 정적 리소스 (CSS, JS, 이미지 등)에 대해서만 컨텐트 지정이 되지 않도록 선제적 설정.
         if (path.startsWith("/static/")) {
            return;
         }
         
         response.setContentType("text/html;charset=utf-8");
         
      } finally {
         filterChain.doFilter(request, response);
      }
   }
}