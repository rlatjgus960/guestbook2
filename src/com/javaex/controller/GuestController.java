package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;


@WebServlet("/gbc")
public class GuestController extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("컨트롤러");
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if("list".equals(action)) {
			
			System.out.println("[리스트]");
			
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestList = guestbookDao.getGuestbookList();
			
			request.setAttribute("gList", guestList);
			
			WebUtil.forward(request, response, "/WEB-INF/addList.jsp");
			
			
		}else if("add".equals(action)) {
			System.out.println("[추가]");
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookVo guestbookVo = new GuestbookVo(name, password, content);
			
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.guestbookInsert(guestbookVo);
			
			WebUtil.redirect(request, response, "/guestbook2/gbc?action=list");
			
		}else if("dform".equals(action)) {
			System.out.println("[삭제폼]");
			
			
			WebUtil.forward(request, response, "/WEB-INF/deleteForm.jsp");
			
			
		}else if("delete".equals(action)) {
			System.out.println("[삭제]");
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestbookVo guestbookVo = new GuestbookVo(no, password);
			
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.guestbookDelete(guestbookVo);
			
			WebUtil.redirect(request, response, "/guestbook2/gbc?action=list");
			
			
			
			
		}
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
