<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<html>
<head>
<title>操作成功!</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body>
<script language="javascript">
alert("图书馆信息修改成功!");
window.location.href="${path}/library/libraryQuery.html";
</script>		
</body>
</html>