<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<c:set scope="page" value="${pageContext.request.contextPath}" var="path"/>
<html>
<head>
<title>操作成功!</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body>
<%int para=Integer.parseInt((String) request.getAttribute("para"));
switch(para){
	case 1:
	%>
		<script language="javascript">
		alert("图书信息添加成功!");
		window.location.href="${path}/book/bookQuery.html";
		</script>	
	<%	break;
	case 2:
	%>
		<script language="javascript">
		alert("图书信息修改成功!");
		window.location.href="${path}/book/bookQuery.html";
		</script>		
	<%	break;
	case 3:
	%>
		<script language="javascript">
		alert("图书信息删除成功!");
		window.location.href="${path}/book/bookQuery.html";
		</script>		
	<%	break;
}
%>
</body>
</html>