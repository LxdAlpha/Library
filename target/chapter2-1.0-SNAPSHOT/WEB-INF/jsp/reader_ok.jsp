<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<c:set var="path" value="${pageContext.request.contextPath}" scope="page"/>
<html>
<head>
<title>�����ɹ�!</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body>
<%int para=Integer.parseInt((String) request.getAttribute("para"));
switch(para){
	case 1:
	%>
		<script language="javascript">
		alert("������Ϣ��ӳɹ�!");
		window.location.href="${path}/reader/readerQuery.html";
		</script>	
	<%	break;
	case 2:
	%>
		<script language="javascript">
		alert("������Ϣ�޸ĳɹ�!");
		window.location.href="${path}/reader/readerQuery.html";
		</script>		
	<%	break;
	case 3:
	%>
		<script language="javascript">
		alert("������Ϣɾ���ɹ�!");
		window.location.href="${path}/reader/readerQuery.html";
		</script>		
	<%	break;
}
%>
</body>
</html>