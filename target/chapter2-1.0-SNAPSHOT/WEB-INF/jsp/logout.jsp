<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gb2312" language="java"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body>
<%
session.invalidate();
out.println("<script language='javascript'>");
out.println("window.location.href='/chapter2/index.html'");
out.println("</script>");
%>	


</body>
</html>
