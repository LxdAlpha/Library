<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="path" value="${pageContext.request.contextPath}"/>
<script language="javascript">
	window.location.href="${path}/borrow/bookrenew.html?barcode=<%=request.getAttribute("bar")%>";
</script>
