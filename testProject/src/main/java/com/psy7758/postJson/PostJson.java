package com.psy7758.postJson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/postJson")
public class PostJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static class Client {
		private String name;
		private int clientNumber;
		private String address;
		private String phoneNumber;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getClientNumber() {
			return clientNumber;
		}

		public void setClientNumber(int clientNumber) {
			this.clientNumber = clientNumber;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * < getInputStream >
		 * 
		 * - HTTP 요청의 본문(body)을 ServletInputStream 형태로 반환하여, POST 요청 등의 body 데이터를 직접
		 * 읽어들일 수 있으며, JSON, XML, 바이너리 데이터(파일 업로드) 등 다양한 형식의 데이터를 처리 가능. readValue 메서드의
		 * 첫번째 인자는 String 뿐만 아니라, InputStream 타입도 오버로딩되어 있음.
		 */
		request.setAttribute("client", new ObjectMapper().readValue(request.getInputStream(), Client.class));
		request.getRequestDispatcher("/WEB-INF/insertPage/insertMain.jsp").forward(request, response);
	}
}