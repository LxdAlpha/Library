<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<%@ page import="com.smart.domain.Book" %>
<%@ page import="java.util.*"%>
<html>
<%
Collection coll=(Collection)request.getAttribute("book");
%>
<head>
<title>ͼ��ݹ���ϵͳ</title>
<link href="${path}/CSS/style.css" rel="stylesheet">
</head>
<body onLoad="clockon(bgclock)">
<%@include file="banner.jsp"%>
<%@include file="navigation.jsp"%>
<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="510" valign="top" style="padding:5px;"><table width="98%"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="22" valign="top" class="word_orange">��ǰλ�ã�ͼ����� &gt; ͼ�鵵������ &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><%
if(coll==null || coll.isEmpty()){
%>
          <table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="36" align="center">����ͼ����Ϣ��</td>
            </tr>
          </table>
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
      <a href="${path}/book/getbook_addjsp.html">���ͼ����Ϣ</a> </td>
  </tr>
</table>
 <%
}else{
  //ͨ��������ʽ��ʾ����
  Iterator it=coll.iterator();
  int ID=0;
  String bookname="";
  String barcode="";
  String typename="";
  String publishing="";
  String bookcase="";
  int storage=0;
  %>
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="87%">&nbsp;      </td>
<td width="13%">
      <a href="${path}/book/getbook_addjsp.html">���ͼ����Ϣ</a></td>
  </tr>
</table>  
  <table width="98%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
  <tr align="center" bgcolor="#e3F4F7">
    <td width="13%" bgcolor="#F9D16B">������</td>  
    <td width="26%" bgcolor="#F9D16B">ͼ������</td>
    <td width="15%" bgcolor="#F9D16B">ͼ������</td>
    <td width="14%" bgcolor="#F9D16B">������</td>
    <td width="12%" bgcolor="#F9D16B">���</td>
    <td width="9%" bgcolor="#F9D16B">�޸�</td>
    <td width="5%" bgcolor="#F9D16B">ɾ��</td>
  </tr>
<%
  while(it.hasNext()){
    Book bookForm=(Book)it.next();
	ID=bookForm.getId().intValue();
	bookname=chStr.toChinese(bookForm.getBookName());
	barcode=chStr.nullToString(bookForm.getBarcode(),"&nbsp;");
	typename=chStr.toChinese(bookForm.getTypeName());
	publishing=chStr.toChinese(bookForm.getPublishing());
	bookcase=chStr.nullToString(chStr.toChinese(bookForm.getBookcaseName()),"&nbsp;");
	%> 
  <tr>
    <td style="padding:5px;">&nbsp;<%=barcode%></td>  
    <td style="padding:5px;"><a href="${path}/book/bookDetail.html?id=<%=ID%>"><%=bookname%></a></td>
    <td style="padding:5px;">&nbsp;<%=typename%></td>  
    <td style="padding:5px;">&nbsp;<%=publishing%></td>  
    <td style="padding:5px;">&nbsp;<%=bookcase%></td>  
	
    <td align="center"><a href="${path}/book/bookModifyQuery.html?id=<%=ID%>">�޸�</a></td>
    <td align="center"><a href="${path}/book/bookDel.html?id=<%=ID%>">ɾ��</a></td>
  </tr>
<%
  }
}
%>  
</table></td>
      </tr>
    </table>
</td>
  </tr>
</table><%@ include file="copyright.jsp"%></td>
  </tr>
</table>
</body>
</html>
