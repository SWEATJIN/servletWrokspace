package com.psy7758.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/simpleCalculator")
public class SimpleCalculator extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private static boolean firstInput = true;
   
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String num_ = request.getParameter("num");
      String addCookieStr_ = null;
      
      try {
         if( firstInput ) {
            String[] possiblePaths = { "/", "/simpleCalculator" };

            for (String path : possiblePaths) {
                Cookie deleteCookie = new Cookie("tot", "");
                deleteCookie.setPath(path);
                deleteCookie.setMaxAge(0);
                response.addCookie(deleteCookie);
            }
            
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
         
         cookie.setPath("/simpleCalculator");
         response.addCookie(cookie);
      } finally {
         response.sendRedirect("simpleCalculator/redirectionPage");
      }
   }
}