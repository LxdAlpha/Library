<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
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
		alert("书架信息添加成功!");
		//parent.window.location.href="bookCase.do?action=bookCaseQuery";
		opener.location.reload();
		window.close();
		</script>	
	<%	break;
	case 2:
	%>
		<script language="javascript">
		alert("书架信息修改成功!");
		opener.location.reload();
		window.close();
		</script>		
	<%	break;
	case 3:
	%>
		<script language="javascript">
		alert("书架信息删除成功!");
		window.location.href="${path}/bookCase/bookCaseQuery.html";
		</script>		
	<%	break;
}
%>
</body>
</html>