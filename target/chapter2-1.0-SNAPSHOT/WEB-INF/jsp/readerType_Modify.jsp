<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<c:set scope="page" value="${pageContext.request.contextPath}" var="path"/>
<%@ page import="com.smart.domain.ReaderType" %>
<%@ page import="com.smart.core.ChStr"%>
<html>
<head>
<title>�޸Ķ���������Ϣ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="${path}/CSS/style.css">
</head>

<body>
<%
ReaderType readerTypeForm=(ReaderType)request.getAttribute("readerTypeQueryif");
ChStr chStr=new ChStr();
%>
<table width="292" height="175" border="0" cellpadding="0" cellspacing="0" background="${path}/Images/subBG.jpg">
  <tr>
    <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%" height="25">&nbsp;</td>
        <td width="94%">&nbsp;</td>
        <td width="3%">&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><table width="100%" height="131"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td align="center" valign="top"><form name="form1" method="post" action="${path}/readerType/readerTypeModify.html">
 <input name="id" type="hidden" size="50" value="<%=readerTypeForm.getId()%>"> 
  <table width="100%" height="111" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="28%" align="center">�������ƣ�      </td>
    <td width="72%"><input name="name" type="text" size="28" value="<%=chStr.toChinese(readerTypeForm.getName())%>">
      </td>
  </tr>
  <tr>
    <td align="center">�ɽ�������</td>
    <td><input name="number" type="text" size="25" value="<%=readerTypeForm.getNumber()%>">
      (��)</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input name="Submit" type="submit" class="btn_grey" value="����">
&nbsp;
<input name="Submit2" type="reset" class="btn_grey" value="����">
&nbsp;
<input name="Submit3" type="button" class="btn_grey" onClick="window.close()" value="�ر�"></td>
  </tr>
</table>
</form></td>
          </tr>
        </table></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
