package com.LPL.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookInfoDAO {

	DBHelper db =new DBHelper();
	public boolean registerBook(List<Object> params) throws SQLException, IOException {
		String sql="insert into bookType values(?,?,?,?)";
		
		int i=db.doUpdate(sql,params);
		
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}
	public boolean registerBooka(List<Object> params) throws SQLException, IOException {
		String sql="insert into tb_bookInfo values (?,?,?,?,?,?,to_date(?,'yyyy-MM-dd'),?,?,?)";
		
		int i=db.doUpdate(sql,params);
		
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}
//	public boolean registerInventory(List<Object> params1) throws SQLException, IOException {
//		String sql="insert into tb_inventory values (seq_tb_inventory_inv_id.nextval,?,?,null,null)";
//		
//		int i=db.doUpdate(sql,params1);
//		
//		if(i>0){
//			return true;
//		
//		}else{
//			return false;
//		}
//	}


	public boolean registerUpdateBooka(List<Object> params) throws SQLException, IOException {
		String sql="insert into tb_book values ( seq_tb_book_book_id.nextval,?)";
		int i=db.doUpdate(sql,params);
		
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}
	
	
	public List<Map<String, Object>> showBooksort() throws SQLException, IOException {
		String sql ="select  * from bookType";
		List<Map<String, Object>> list  =db.findMultiObject(sql, null);
		return list;
		
				
	}

}
