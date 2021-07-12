<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.dao.GuestbookDao" %>
<%@ page import="com.javaex.vo.GuestbookVo" %>    
    
<%

	GuestbookDao guestbookDao = new GuestbookDao();
	List<GuestbookVo> guestbookList = guestbookDao.getGuestbookList();
	
	System.out.println(guestbookList.toString());


%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<form action ="./add.jsp" method="post">
		<table border='1'>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td colspan ='4'><textarea name="content" style="width:470px;height:100px"></textarea></td>
			</tr>
			<tr>
				<td colspan ='4'><button type="submit">확인</button></td>
			</tr>
		
		</table>
	
	
	</form>
	<br>
	<!-- 리스트 출력 -->
	<%
		for(int i=0; i<guestbookList.size(); i++) {
	%>
	
			<table border='1' style="width:485px;">
			
				<tr>
					<td><%=guestbookList.get(i).getNo() %></td>
					<td><%=guestbookList.get(i).getName() %></td>
					<td><%=guestbookList.get(i).getRegDate() %></td>
					<td><a href="./deleteForm.jsp?id=<%=guestbookList.get(i).getNo() %>">삭제</a></td>
				</tr>
				<tr>
					<td colspan ='4'><%=guestbookList.get(i).getContent() %></td>
				</tr>
			</table>
			<br>
	
	<%
		}
	%>
	

</body>
</html>