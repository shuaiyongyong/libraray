package com.LPL.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReaderDao {
	DBHelper db = new DBHelper();
	
	//录入注册读者信息
	public boolean registerReader(List<Object> params) throws SQLException, IOException{//信息注册
		
		String sql="insert into tb_reader values(seq_tb_reader_reader_id.Nextval,?,?,?,to_date(?,'yyyy-MM-dd'),?,?,sysdate,?,?)";
		
		int i=db.doUpdate(sql,params);
		
		if(i>0){
			return true;
		
		}else{
			return false;
		}
		
					
	}
	
	//查询显示读者信息
	//根据表格信息要求查询显示,map.get("READER_RD").toString() reader_rd
		public List<Map<String, Object>> showAllReader() throws SQLException, IOException{
			String sql="select reader_name,reader_id,reader_sex,to_char(reader_rd,'yyyy-MM-dd') reader_rd,maxNum,reader_tel from tb_reader";

		return db.findMultiObject(sql, null);
		}
		
		
	//根据读者编号ID查询读者信息	
		public List<Map<String, Object>> showIdReader( int reader_id) throws SQLException, IOException{
			String sql="select reader_name,reader_id,reader_sex,to_char(reader_rd,'yyyy-MM-dd') reader_rd,maxNum,reader_tel from tb_reader where reader_id =?";
			List<Object> params=new ArrayList<Object>();
			params.add(reader_id);
			return db.findMultiObject(sql, params);
		}
		
		
	//根据读者姓名查询读者信息
		public List<Map<String, Object>> showNameReader( String reader_name) throws SQLException, IOException{
			String sql="select reader_name,reader_id,reader_sex,to_char(reader_rd,'yyyy-MM-dd') reader_rd,maxNum,reader_tel from tb_reader where reader_name =?";
			List<Object> params=new ArrayList<Object>();
			params.add(reader_name);
			return db.findMultiObject(sql, params);
		}

		//显示读者信息根据读者编号
		//根据表格信息要求查询显示,map.get("READER_RD").toString() reader_rd
			public List<Map<String, Object>> showIdReadera(int reader_ida) throws SQLException, IOException{
					String sql="select reader_id,reader_name,reader_sex,reader_pwd,to_char(reader_bri,'yyyy-MM-dd') reader_bri,maxNum,reader_tel,identityCard from tb_reader where reader_id =?";
					List<Object> params=new ArrayList<Object>();
					params.add(reader_ida);
					return db.findMultiObject(sql, params);
					
					}
			
		//显示读者信息根据读者姓名
			public List<Map<String, Object>> showNameReadera(String reader_namea) throws SQLException, IOException{
				String sql="select reader_id,reader_name,reader_sex,reader_pwd,to_char(reader_bri,'yyyy-MM-dd') reader_bri,maxNum,reader_tel,identityCard from tb_reader where reader_name =?";
				List<Object> params=new ArrayList<Object>();
				params.add(reader_namea);
				return db.findMultiObject(sql, params);
				
				}
			
			
			
			//删除读者信息
			public boolean deleteReader(List<Object> params) throws SQLException, IOException{
				String sql="delete tb_reader where reader_id =?";
				int i=db.doUpdate(sql, params);
				
				if(i>0){
					return true;
				
				}else{
					return false;
				}
				
				}
			
			//修改读者信息
			public boolean alterReader(List<Object> params) throws SQLException, IOException{
				String sql="update tb_reader set reader_id=?,reader_name=?,reader_pwd=?,maxNum=?,reader_tel=?,identityCard=? where reader_id =?";
				int i=db.doUpdate(sql, params);
				
				if(i>0){
					return true;
				
				}else{
					return false;
				}
				
				}

			public List<Map<String, Object>> findAllReader() throws SQLException, IOException {
				String sql="select reader_name,reader_id,reader_sex,to_char(reader_bri,'yyyy-MM-dd') reader_bri,maxNum,reader_tel from tb_reader ";

				return db.findMultiObject(sql, null);
			}

}
/*
 *  map.get("READER_NAME").toString(),
										map.get("READER_ID").toString(), map.get("READER_SEX").toString(),
										map.get("READER_RD").toString(), map.get("MAXNUM").toString(),
										map.get("READER_TEL").toString() });
 */





