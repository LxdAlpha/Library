<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<c:set scope="page" var="path" value="${pageContext.request.contextPath}"/>
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
		alert("ͼ��������Ϣ��ӳɹ�!");
		opener.location.reload();
		window.close();
		</script>	
	<%	break;
	case 2:
	%>
		<script language="javascript">
		alert("ͼ��������Ϣ�޸ĳɹ�!");
		opener.location.reload();
		window.close();
		</script>		
	<%	break;
	case 3:
	%>
		<script language="javascript">
		alert("ͼ��������Ϣɾ���ɹ�!");
		window.location.href="${path}/bookType/bookTypeQuery.html";
		</script>		
	<%	break;
}
%>
</body>
</html>