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
import com.orm.TJieyue;
import com.service.liuService;

public class jieyue_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		if(type.endsWith("jieyueMana"))
		{
			jieyueMana(req, res);
		}
		if(type.endsWith("jieyueAdd"))
		{
			jieyueAdd(req, res);
		}
		if(type.endsWith("jieyueDel"))
		{
			jieyueDel(req, res);
		}
		if(type.endsWith("jieyue_huanshu_pre"))
		{
			jieyue_huanshu_pre(req, res);
		}
		if(type.endsWith("jieyue_huanshu"))
		{
			jieyue_huanshu(req, res);
		}
		
		if(type.endsWith("feiyong_tongji_riqi"))
		{
			feiyong_tongji_riqi(req, res);
		}
		
		if(type.endsWith("feiyong_tongji_yuefen"))
		{
			feiyong_tongji_yuefen(req, res);
		}
	}
	
	
	public void jieyueAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String user_id=req.getParameter("user_id");
		String xueju_id=req.getParameter("xueju_id");
		String jieyueshuliang=req.getParameter("jieyueshuliang");
		String jieyueShijian=req.getParameter("jieyueShijian");
		
		String shifouguihuan="否";
		String guihuanshijian="";
		float feiyong=0;
		
		
		String sql="insert into t_jieyue values(?,?,?,?,?,?,?)";
		Object[] params={Integer.parseInt(user_id),
						 Integer.parseInt(xueju_id),
						 Integer.parseInt(jieyueshuliang),
						 jieyueShijian,
						 shifouguihuan,
						 guihuanshijian,
                         feiyong};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "jieyue?type=jieyueMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void jieyueDel(HttpServletRequest req,HttpServletResponse res)
	{
		String sql="delete from t_jieyue where id="+Integer.parseInt(req.getParameter("id"));
		Object[] params={};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "jieyue?type=jieyueMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}

	public void jieyueMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List jieyueList=new ArrayList();
		String sql="select * from t_jieyue";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TJieyue jieyue=new TJieyue();
				
				jieyue.setId(rs.getInt("id"));
				jieyue.setUser_id(rs.getInt("user_id"));
				jieyue.setXueju_id(rs.getInt("xueju_id"));
				jieyue.setJieyueshuliang(rs.getInt("jieyueshuliang"));
				
				jieyue.setJieyueshijian(rs.getString("jieyueshijian"));
				jieyue.setShifouguihuan(rs.getString("shifouguihuan"));
				jieyue.setGuihuanshijian(rs.getString("guihuanshijian"));
				jieyue.setFeiyong(rs.getFloat("feiyong"));
				
				jieyue.setUser(liuService.getUser(rs.getInt("user_id")));
				jieyue.setXueju(liuService.getXueju(rs.getInt("xueju_id")));
				
				
				jieyueList.add(jieyue);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("jieyueList", jieyueList);
		req.getRequestDispatcher("admin/jieyue/jieyueMana.jsp").forward(req, res);
	}
	
	
	
	public void jieyue_huanshu_pre(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List jieyueList=new ArrayList();
		String sql="select * from t_jieyue where shifouguihuan='否'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TJieyue jieyue=new TJieyue();
				
				jieyue.setId(rs.getInt("id"));
				jieyue.setUser_id(rs.getInt("user_id"));
				jieyue.setXueju_id(rs.getInt("xueju_id"));
				jieyue.setJieyueshuliang(rs.getInt("jieyueshuliang"));
				
				jieyue.setJieyueshijian(rs.getString("jieyueshijian"));
				jieyue.setShifouguihuan(rs.getString("shifouguihuan"));
				jieyue.setGuihuanshijian(rs.getString("guihuanshijian"));
				jieyue.setFeiyong(rs.getFloat("feiyong"));
				
				jieyue.setUser(liuService.getUser(rs.getInt("user_id")));
				jieyue.setXueju(liuService.getXueju(rs.getInt("xueju_id")));
				
				
				jieyueList.add(jieyue);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("jieyueList", jieyueList);
		req.getRequestDispatcher("admin/jieyue/jieyue_huanshu_pre.jsp").forward(req, res);
	}
	
	
	public void jieyue_huanshu(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String sql="update t_jieyue set shifouguihuan='是',guihuanshijian=?,feiyong=? where id="+Integer.parseInt(req.getParameter("id"));
		Object[] params={req.getParameter("guihuanshijian"),Float.parseFloat(req.getParameter("feiyong"))};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "归还完毕");
		req.getRequestDispatcher("common/msg.jsp").forward(req, res);
	}
	
	
	public void feiyong_tongji_riqi(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List jieyueList=new ArrayList();
		String sql="select * from t_jieyue where shifouguihuan='是' and guihuanshijian like '%"+req.getParameter("shijian")+"%'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TJieyue jieyue=new TJieyue();
				
				jieyue.setId(rs.getInt("id"));
				jieyue.setUser_id(rs.getInt("user_id"));
				jieyue.setXueju_id(rs.getInt("xueju_id"));
				jieyue.setJieyueshuliang(rs.getInt("jieyueshuliang"));
				
				jieyue.setJieyueshijian(rs.getString("jieyueshijian"));
				jieyue.setShifouguihuan(rs.getString("shifouguihuan"));
				jieyue.setGuihuanshijian(rs.getString("guihuanshijian"));
				jieyue.setFeiyong(rs.getFloat("feiyong"));
				
				jieyue.setUser(liuService.getUser(rs.getInt("user_id")));
				jieyue.setXueju(liuService.getXueju(rs.getInt("xueju_id")));
				
				
				jieyueList.add(jieyue);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("jieyueList", jieyueList);
		req.getRequestDispatcher("admin/feiyong/feiyong_tongji_riqi.jsp").forward(req, res);
	}
	
	
	public void feiyong_tongji_yuefen(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List jieyueList=new ArrayList();
		String sql="select * from t_jieyue where shifouguihuan='是' and guihuanshijian like '%"+req.getParameter("shijian")+"%'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TJieyue jieyue=new TJieyue();
				
				jieyue.setId(rs.getInt("id"));
				jieyue.setUser_id(rs.getInt("user_id"));
				jieyue.setXueju_id(rs.getInt("xueju_id"));
				jieyue.setJieyueshuliang(rs.getInt("jieyueshuliang"));
				
				jieyue.setJieyueshijian(rs.getString("jieyueshijian"));
				jieyue.setShifouguihuan(rs.getString("shifouguihuan"));
				jieyue.setGuihuanshijian(rs.getString("guihuanshijian"));
				jieyue.setFeiyong(rs.getFloat("feiyong"));
				
				jieyue.setUser(liuService.getUser(rs.getInt("user_id")));
				jieyue.setXueju(liuService.getXueju(rs.getInt("xueju_id")));
				
				
				jieyueList.add(jieyue);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("jieyueList", jieyueList);
		req.getRequestDispatcher("admin/feiyong/feiyong_tongji_yuefen.jsp").forward(req, res);
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
