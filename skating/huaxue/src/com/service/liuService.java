package com.service;

import java.sql.ResultSet;

import com.dao.DB;
import com.orm.TXueju;
import com.orm.TUser;

public class liuService
{
	public static TUser getUser(int id)
	{
		TUser user=new TUser();
		
		String sql="select * from t_user where id="+id;
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			rs.next();
			
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setSex(rs.getString("sex"));
			user.setAge(rs.getString("age"));
			
			user.setAddress(rs.getString("address"));
			user.setTel(rs.getString("tel"));
			user.setEmail(rs.getString("email"));
			user.setDel(rs.getString("del"));
			
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return user;
	}
	
	
	
	public static TXueju getXueju(int id)
	{
		TXueju xueju=new TXueju();
		
		String sql="select * from t_xueju where id="+id;
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			rs.next();
			
			xueju.setId(rs.getInt("id"));
			xueju.setBianhao(rs.getString("bianhao"));
			xueju.setMingcheng(rs.getString("mingcheng"));
			xueju.setJiage(rs.getFloat("jiage"));
			
			xueju.setBeizhu(rs.getString("beizhu"));
			xueju.setDel(rs.getString("del"));
			
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		System.out.println(xueju.getMingcheng()+"%%");
		return xueju;
	}
}
