<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="com.orm.TJieyue"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
    String path = request.getContextPath();
    float j=0;
    
    List jieyueList=(List)request.getAttribute("jieyueList");
    for(int i=0;i<jieyueList.size();i++)
    {
        TJieyue jieyue=(TJieyue)jieyueList.get(i);
        j=j+jieyue.getFeiyong();
    }
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

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="30" background="<%=path %>/img/tbg.gif">&nbsp;收银信息统计&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="10%">雪具</td>
					<td width="10%">会员</td>
					<td width="10%">借阅数量</td>
					<td width="10%">借阅时间</td>
					<td width="10%">是否归还</td>
					<td width="10%">归还时间</td>
					<td width="10%">费用</td>
		        </tr>	
				<c:forEach items="${requestScope.jieyueList}" var="jieyue">
				<tr align='center' bgcolor="#FFFFFF" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${jieyue.xueju.mingcheng}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${jieyue.user.name}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${jieyue.jieyueshuliang}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${jieyue.jieyueshijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<c:if test="${jieyue.shifouguihuan=='是'}">
						    ${jieyue.shifouguihuan}
						</c:if>
						<c:if test="${jieyue.shifouguihuan=='否'}">
						    <font color="red">${jieyue.shifouguihuan}</font>
						</c:if>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${jieyue.guihuanshijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${jieyue.feiyong}
					</td>
				</tr>
				</c:forEach>
				<tr bgcolor="#E7E7E7" align="center">
					<td height="14" colspan="30" background="<%=path %>/img/tbg.gif">总计费用：<font style="color: red"><%=j %></font></td>
			    </tr>
			</table>
	</body>
</html>
