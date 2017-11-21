<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
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
           function xuejuDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/xueju?type=xuejuDel&id="+id;
               }
           }
           
           function xuejuAdd()
           {
                 var url="<%=path %>/admin/xueju/xuejuAdd.jsp";
				 window.location.href=url;
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/images/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="10" background="<%=path %>/images/tbg.gif">&nbsp;雪具信息管理&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="5%">序号</td>
					<td width="10%">编号</td>
					<td width="10%">名称</td>
					<td width="10%">租赁价格/小时</td>
					<td width="10%">备注信息</td>
					<td width="10%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.xuejuList}" var="xueju" varStatus="ss">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${ss.index+1 }
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${xueju.bianhao }
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${xueju.mingcheng }
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${xueju.jiage }
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${xueju.beizhu }
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a href="#" onclick="xuejuDel(${xueju.id })" class="pn-loperator">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="添加" style="width: 80px;" onclick="xuejuAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
