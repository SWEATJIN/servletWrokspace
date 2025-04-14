package com.psy7758.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/simpleCalculator2")
public class SimpleCalculator2 extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private static boolean firstInput = true;

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String num_ = request.getParameter("num");
      String addCookieStr_ = null;
      
      try {
         String[] possiblePaths = { "/", "/simpleCalculator2" };

         for (String path : possiblePaths) {
             Cookie deleteCookie = new Cookie("tot", "");
             deleteCookie.setPath(path);
             deleteCookie.setMaxAge(0);
             response.addCookie(deleteCookie);
         }
         
         if( firstInput ) {
            if( num_.equals("") ) {
               addCookieStr_ = "0";
            } else {
               addCookieStr_ = num_;
            }
            
            firstInput = false;
         } else {
            if( num_.equals("") ) {
               
               return;
            } else {
               Cookie[] cookies = request.getCookies();
               
               for(Cookie c: cookies) {
                  if(c.getName().equals("tot")) {
                     addCookieStr_ = Integer.parseInt(num_) + Integer.parseInt(c.getValue()) + ""; 
                     
                     break;
                  }
               }
            }
         }
         
         Cookie cookie = new Cookie("tot", addCookieStr_);
         
         cookie.setPath("/simpleCalculator2");
         response.addCookie(cookie);
      } finally {
         response.sendRedirect("simpleCalculator2/redirectionPage2");
      }
   }
}