<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<c:set scope="page" var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>�����ɹ�!</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body>
<script language="javascript">
alert("������ĳɹ�!");
window.location.href="${path}/manager/querypwd.html";
</script>		
</body>
</html>