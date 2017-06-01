package com.LPL.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.LPL.dao.Myproperties;

 

public class DBHelper {

	static {
		//加载静态块
		try {
			Class.forName(Myproperties.getInstance().getProperty("driverClass"));
		}  catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//private List<Object> params;
	
	//获取数据库连接对象
	public Connection getConn() throws SQLException, IOException{
		Connection conn=null;
		conn=DriverManager.getConnection(Myproperties.getInstance().getProperty("url"),Myproperties.getInstance());//.getProperty("user"),MyProperties.getInstance().getProperty("password"));
		return conn;
	}
	
	//�关闭数据库连接
	public  void closeAll(PreparedStatement pstmt,Connection conn,ResultSet rs){
		//关闭结果集
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//关闭预处理对象
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//关闭数据库连接
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	/*
	 * ���ò����ķ���
	 * @param pstmt Ԥ�������
	 * @param params  ���������ֵ�����
	 * throws SQLException 
	 */
	private void setParams(PreparedStatement pstmt ,List<Object> params) throws SQLException, FileNotFoundException{
		if(null!=params&&params.size()>0 ){
			for(int i=0;i<params.size();i++) {
				if(params.get(i) instanceof File){
					File file =(File)params.get(i);
					InputStream in =new FileInputStream(file);
					pstmt.setBinaryStream(i+1, in,(int)file.length());
				}else{
					pstmt.setString(i+1, params.get(i).toString());
				}
				
			}
			
		}
	}
	
	public  int doUpdate(String sql,List<Object> params) throws SQLException, IOException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try{
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt,params);
			result = pstmt.executeUpdate();
		}finally{
			closeAll( pstmt, conn,null);
		}
		return result;
	}
		/**
		 * �������� ����sql��� ����sql���Ľ��Ҫôһ��ɹ���Ҫôһ��ʧ��
		 * �ֶ����������ύ
		 */
		public int doUpdate (List<String> sqls, List<List<Object> > params) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			int result = -1;
			try{
				conn = this.getConn();
				conn.setAutoCommit(false);
				if(null!=sqls &&sqls.size()>0){
					//ѭ��sql���
					for(int i= 0;i<sqls.size();i++){
						String sql = sqls.get(i);
						pstmt = conn.prepareStatement(sql);
						//����ǰsql�������
						this.setParams(pstmt, params.get(i));
						result = pstmt.executeUpdate();
					}
				}
				//�ύ����
				conn.commit();
			}catch(Exception e){
				conn.rollback();
				throw e;
			}finally{
				conn.setAutoCommit(true);
				this.closeAll(pstmt, conn,null);
			}
			return result;
		}
 
		
 
	/*
	 * ��  �� ɾ �� ��
	 * 
	 */

//	public List<Map<String, Object>> findSingleObject(String sql, List<Object> params) throws SQLException {
//		 List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		 Connection conn =null;
//		 PreparedStatement pstmt = null;
//		 ResultSet rs = null;
//		 Map<String, Object > map = null;
//		 try{
//			 conn=this.getConn();
//			 pstmt = conn.prepareStatement(sql);
//			 this.setParams(pstmt, params);
//			 rs = pstmt.executeQuery();
//			 List<String> columnNames = this.getAllColumnNames(rs);
//			while(rs.next()){
//				 map = new HashMap<String,Object>();
//				 for(String cn :columnNames){ //ѭ�����������б�������Map�ļ��������б��ȡÿһ���е�ֵ
//					 
//					 map.put(cn,rs.getObject(cn));
//				 }
//				 list.add(map);
//			 }
//		 } finally{
//				 this.closeAll(rs, pstmt, conn);
//			 }
//			 return list;
//		 
//		
//	}
		public Map<String, Object> findSingleObject(String sql,List<Object> params) throws SQLException, IOException{
			Map<String,Object> map=null;
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;	
			try{
				conn=this.getConn();
				pstmt=conn.prepareStatement(sql);
				this.setParams(pstmt, params);
				rs=pstmt.executeQuery();
				List<String> columnNames=this.getAllColumnNames(rs);
				if(rs.next()){
					map=new HashMap<String,Object>();
					for(String cm:columnNames){
						map.put(cm, rs.getObject(cm));//?
					}
				}
			}finally{
				closeAll( pstmt, conn,rs);
			}
			return map;
		}

		//�������뷽��
		public List<Map<String, Object>> findMultiObject(String sql , List<Object> params)throws SQLException, IOException {
			 List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			 Map<String,Object> map = null;
			 Connection conn = null;
			 PreparedStatement pstmt =null;
			 ResultSet rs =null;
			 
			 try{
				 conn= this.getConn();
				 pstmt =conn.prepareStatement(sql);
				 this.setParams(pstmt, params);
				 rs = pstmt.executeQuery();
				 List<String > columnNames = this.getAllColumnNames(rs);
				 while(rs.next()){
					 map=new HashMap<String,Object>();
					 for(String cn:columnNames){
						 map.put(cn, rs.getObject(cn));
					 }
					 list.add(map);
					 
				 }
			 }finally{
				 closeAll( pstmt, conn,rs);
			 }
			 return list;
			 }
			 
	private List<String> getAllColumnNames(ResultSet rs) throws SQLException {
		 List<String> columnNames = new ArrayList<String>();
		 if(null !=rs){
			 for(int i =0;i<rs.getMetaData().getColumnCount();i++){
				 columnNames.add(rs.getMetaData().getColumnName( i+1));
			 }
		 }
		return  columnNames;
	}

	public int UpdateImg(String sql, int id, File file) throws FileNotFoundException {
		 FileInputStream in = new FileInputStream(file);
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 int result=0;
		 try{
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setBinaryStream( 1, in,(int)file.length());  
			 pstmt.setInt( 2, id);
			 result = pstmt.executeUpdate();
			 
		 }catch(Exception e ){
			 
		 }finally{
			 closeAll( pstmt, conn,null);
		 }
		 return result;
		 
	}
	
	public double getCount (String sql,List<Object> params) throws SQLException, IOException {
		double result =0;
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		conn = this.getConn();
		try{
			pstmt =conn.prepareStatement(sql);
			setParams(pstmt,params);
			rs =pstmt.executeQuery();
			if(rs.next()){
				result =rs.getDouble(1);
			}
		}finally{
			 this.closeAll( pstmt, conn,rs);
		}
		
		
		return result;
		
	}

	}
 

