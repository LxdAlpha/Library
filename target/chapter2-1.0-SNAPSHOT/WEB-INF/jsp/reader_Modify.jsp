<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<c:set scope="page" value="${pageContext.request.contextPath}" var="path"/>
<%@ page import="com.smart.dao.ReaderTypeDao" %>
<%@ page import="com.smart.domain.ReaderType" %>
<%@ page import="com.smart.domain.Reader" %>
<%@ page import="java.util.*"%>
<html>
<%
ApplicationContext ctx1 = new ClassPathXmlApplicationContext("classpath:smart-context.xml");
String str=null;
ReaderTypeDao readerTypeDAO= ctx1.getBean(ReaderTypeDao.class);
Collection coll=(Collection)readerTypeDAO.query(str);
Iterator it=coll.iterator();
Reader readerForm=(Reader)request.getAttribute("readerQueryif");
%>
<script language="jscript">
function check(form){
	if(form.name.value==""){
		alert("�������������!");form.name.focus();return false;
	}
	if(form.paperNO.value==""){
		alert("������֤������!");form.paperNO.focus();return false;
	}
}
</script>
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
    <td height="510" valign="top" style="padding:5px;"><table width="98%" height="487"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="22" valign="top" class="word_orange">��ǰλ�ã����߹��� &gt; ���ߵ������� &gt; �޸Ķ�����Ϣ &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><table width="100%" height="493"  border="0" cellpadding="0" cellspacing="0">
  <tr>
<%  int ID=readerForm.getId().intValue();
  String name=chStr.toChinese(readerForm.getName());
  String sex=chStr.toChinese(readerForm.getSex());  
  String barcode=readerForm.getBarcode();
  String vocation=chStr.toChinese(readerForm.getVocation());
  String birthday=readerForm.getBirthday();
  String paperType=chStr.toChinese(readerForm.getPaperType());
  String paperNO=readerForm.getPaperNO();
  String tel=readerForm.getTel();
  String email=readerForm.getEmail();
  String createDate=readerForm.getCreateDate();
  String remark=chStr.toChinese(readerForm.getRemark());
  String operator=chStr.toChinese(readerForm.getOperator());
  int typeid=readerForm.getTypeid();
  String typename=chStr.toChinese(readerForm.getTypename());
  String typeName="";
  int typeID=0;
  %>
    <td align="center" valign="top">
	<form name="form1" method="post" action="${path}/reader/readerModify.html">
	<table width="600" height="432"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td width="173" align="center">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
        <td width="427" height="39">
          <input name="name" type="text" value="<%=name%>" readonly="yes"> 
          *         
          <input name="id" type="hidden" id="id" value="<%=ID%>"></td>
      </tr>
      <tr>
        <td width="173" align="center">��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
        <td height="35"><input name="sex" type="radio" class="noborder" id="radiobutton" value="��" <%if("��".equals(sex)) out.println("checked");%>>
          <label for="radiobutton">�� </label>
          <label>
          <input name="sex" type="radio" class="noborder" value="Ů" <%if("Ů".equals(sex)) out.println("checked");%>>
          Ů</label></td>
      </tr>
      <tr>
        <td align="center">��&nbsp;��&nbsp;�룺</td>
        <td><input name="barcode" type="text" id="barcode" value="<%=barcode%>" readonly="yes"></td>
      </tr>
      <tr>
        <td align="center">�������ͣ�</td>
        <td>
		<select name="typeid" class="wenbenkuang" id="typeid">
<%
  while(it.hasNext()){
    ReaderType readerTypeForm=(ReaderType)it.next();
	typeID=readerTypeForm.getId().intValue();
	typeName=chStr.toChinese(readerTypeForm.getName());
	%> 		
				
          <option value="<%=typeID%>" <%if(typeid==typeID) out.println("selected");%>><%=typeName%></option>
<%}%> 
        </select>        </td>
      </tr>
      <tr>
        <td align="center">ְ&nbsp;&nbsp;&nbsp;&nbsp;ҵ��</td>
        <td><input name="vocation" type="text" id="vocation" value="<%=vocation%>"></td>
      </tr>
      <tr>
        <td align="center">�������ڣ�</td>
        <td><input name="birthday" type="text" id="birthday" value="<%=birthday%>"></td>
      </tr>
      <tr>
        <td align="center">��Ч֤����</td>
        <td><select name="paperType" class="wenbenkuang" id="paperType">
          <option value="����֤"<%if("����֤".equals(paperType)) out.println(" selected");%>>����֤</option>
          <option value="ѧ��֤"<%if("ѧ��֤".equals(paperType)) out.println(" selected");%>>ѧ��֤</option>
          <option value="����֤"<%if("����֤".equals(paperType)) out.println(" selected");%>>����֤</option>
          <option value="����֤"<%if("����֤".equals(paperType)) out.println(" selected");%>>����֤</option>
                        </select></td>
      </tr>
      <tr>
        <td align="center">֤�����룺</td>
        <td><input name="paperNO" type="text" id="paperNO" value="<%=paperNO%>"> 
          * </td>
      </tr>
      <tr>
        <td align="center">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
        <td><input name="tel" type="text" id="tel" value="<%=tel%>"></td>
      </tr>
      <tr>
        <td align="center">Email��</td>
        <td><input name="email" type="text" id="email" value="<%=email%>" size="50">
          <input name="operator" type="hidden" id="operator" value="<%=operator%>"></td>
      </tr>
      <tr>
        <td height="30" align="center">��&nbsp;��&nbsp;Ա��</td>
        <td><input name="operator" type="text" id="operator" value="<%=operator%>" readonly="yes">
        </td>
      </tr>
      <tr>
        <td align="center">��&nbsp;&nbsp;&nbsp;&nbsp;ע��</td>
        <td><textarea name="remark" cols="50" rows="5" class="wenbenkuang" id="remark"><%=remark%></textarea></td>
      </tr>
      <tr>
        <td align="center">&nbsp;</td>
        <td><input name="Submit" type="submit" class="btn_grey" value="����" onClick="return check(form1)">
&nbsp;
<input name="Submit2" type="button" class="btn_grey" value="����" onClick="history.back()"></td>
      </tr>
    </table>
	</form>
	</td>
  </tr>
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