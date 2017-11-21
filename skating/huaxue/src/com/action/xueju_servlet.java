package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.orm.TAdmin;
import com.orm.TXueju;

public class xueju_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		if(type.endsWith("xuejuMana"))
		{
			xuejuMana(req, res);
		}
		if(type.endsWith("xuejuAdd"))
		{
			xuejuAdd(req, res);
		}
		if(type.endsWith("xuejuDel"))
		{
			xuejuDel(req, res);
		}
		
		if(type.endsWith("xuejuAll"))
		{
			xuejuAll(req, res);
		}
	}
	
	public void xuejuAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String bianhao=req.getParameter("bianhao");
		String mingcheng=req.getParameter("mingcheng");
		float jiage=Float.parseFloat(req.getParameter("jiage"));
		String beizhu=req.getParameter("beizhu");
		String del="no";
		
		
		String sql="insert into t_xueju values(?,?,?,?,?)";
		Object[] params={bianhao,mingcheng,jiage,beizhu,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "xueju?type=xuejuMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void xuejuMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List xuejuList=new ArrayList();
		String sql="select * from t_xueju where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TXueju xueju=new TXueju();
				
				xueju.setId(rs.getInt("id"));
				xueju.setBianhao(rs.getString("bianhao"));
				xueju.setMingcheng(rs.getString("mingcheng"));
				xueju.setJiage(rs.getFloat("jiage"));
				
				xueju.setBeizhu(rs.getString("beizhu"));
				xueju.setDel(rs.getString("del"));
				
				xuejuList.add(xueju);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("xuejuList", xuejuList);
		req.getRequestDispatcher("admin/xueju/xuejuMana.jsp").forward(req, res);
	}
	
	public void xuejuDel(HttpServletRequest req,HttpServletResponse res)
	{
		String sql="update t_xueju set del='yes' where id="+Integer.parseInt(req.getParameter("id"));
		Object[] params={};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "xueju?type=xuejuMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}

	public void xuejuAll(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List xuejuList=new ArrayList();
		String sql="select * from t_xueju where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TXueju xueju=new TXueju();
				
				xueju.setId(rs.getInt("id"));
				xueju.setBianhao(rs.getString("bianhao"));
				xueju.setMingcheng(rs.getString("mingcheng"));
				xueju.setJiage(rs.getFloat("jiage"));
				
				xueju.setBeizhu(rs.getString("beizhu"));
				xueju.setDel(rs.getString("del"));
				
				xuejuList.add(xueju);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("xuejuList", xuejuList);
		req.getRequestDispatcher("admin/xueju/xuejuAll.jsp").forward(req, res);
	}
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
