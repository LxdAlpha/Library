<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" scope="page" value="${pageContext.request.contextPath}"/>
<script language="javascript">
	window.location.href="${path}/borrow/bookborrow.html?barcode=<%=request.getAttribute("bar")%>";
</script>
