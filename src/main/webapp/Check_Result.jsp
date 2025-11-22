<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="Model.Bean.Result" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%Result rs= (Result)request.getAttribute("result"); %>
	<h4><%=rs.getResultId() %></h4>
	<h4><%=rs.getTaskId() %></h4>
	<h4><%=rs.getSimilarityPercent()%></h4>
	<h4><%=rs.getCheckedAt()%></h4>
	<h4><%=rs.getMatchedTemplate() %></h4>
</body>
</html>