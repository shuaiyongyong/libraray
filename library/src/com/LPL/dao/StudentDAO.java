package com.LPL.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StudentDAO {

	DBHelper db=new DBHelper();
	public Map<String, Object> login(String name,String pwd) throws SQLException, IOException{
		Map<String, Object> map=new HashMap<String,Object>();
		String sql="select * from tb_reader where reader_name=? and reader_pwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(name);
		params.add(pwd);
		map=db.findSingleObject(sql, params);
		return map;
		}
	
	public boolean registerStudent(List<Object> params) throws SQLException, IOException {
		String sql="insert into tb_reader values(seq_tb_reader_reader_id.nextval,?,?,?,to_date(?,'yyyy-MM-dd'),?,?,sysdate,null,?,default)";
		
		//String sql="insert into student values(seq_stu_id.nextval,?,?,default,?,?,?,sysdate)";
		int i=db.doUpdate(sql,params);
		
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}
	
}
