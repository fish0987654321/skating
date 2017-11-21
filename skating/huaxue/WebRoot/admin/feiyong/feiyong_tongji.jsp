<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.text.SimpleDateFormat"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%> 

<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
        
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
        
        
        <script language="javascript">
        </script>
	</head>

	<body leftmargin="2" topmargin="9" background='<%=path %>/images/allbg.gif'>
	     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
			<tr bgcolor="#EEF4EA">
		        <td colspan="3" background="<%=path %>/images/wbg.gif" class='title'><span>收银信息管理</span></td>
		    </tr>
			<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
			    <td width="10%" bgcolor="#FFFFFF" align="right">
			         日期统计：
			    </td>
			    <td width="90%" bgcolor="#FFFFFF" align="left">
			        <form action="<%=path %>/jieyue?type=feiyong_tongji_riqi" name="formAdd" method="post">
				        <input type="text" name="shijian" size="40" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>"/>
				        <input type="submit" value="提交"/>&nbsp; 
			        </form>
			    </td>
			</tr>
			<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
			    <td width="10%" bgcolor="#FFFFFF" align="right">
			         月份统计：
			    </td>
			    <td width="90%" bgcolor="#FFFFFF" align="left">
			        <form action="<%=path %>/jieyue?type=feiyong_tongji_yuefen" name="formAdd" method="post">
				        <input type="text" name="shijian" size="40" value="<%=new SimpleDateFormat("yyyy-MM").format(new Date())%>"/>
				        <input type="submit" value="提交"/>&nbsp; 
			        </form>
			    </td>
			</tr>
		 </table>
   </body>
</html>
