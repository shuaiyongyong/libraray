package com.LPL.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.LPL.dao.DBHelper;

public class OperatorDAO {

	DBHelper db=new DBHelper();
	public Map<String, Object> login(String operatorName,String pwd) throws SQLException, IOException{
		Map<String, Object> map=new HashMap<String,Object>();
		String sql="select * from tb_operator where operator_name=? and operator_pwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(operatorName);
		params.add(pwd);
		map=db.findSingleObject(sql, params);
		return map;
	}
	
	//管理员修改密码
	public boolean UpdatePwdOperator(List<Object> params) throws SQLException, IOException{//管理员密码修改
		
		String sql="update tb_operator set operator_pwd = ? where operator_name= ?";
		//--将学号为10001 学生 的性别 改成 女
		//update tb_operator set sex = '女' where sid =10001;
		//--修改学生信息表 学号4号改为5号
		//update stuInfo set sid = 5 where sid =4;
		//update tb_operator set operator_pwd = ? where operator_pwd = ?
		int i=db.doUpdate(sql,params);
		
		if(i>0){
			return true;
		
		}else{
			return false;
		}
		
		
	}

	
	
	//全部查询
	public static List<Map<String, Object>> findAllBook() throws SQLException, IOException {
		DBHelper db=new DBHelper();
		//Map<String, Object> map=new HashMap<String,Object>();
		String sql="select  ISBN,book_name,type_name,book_writer,book_cost,book_publisher,book_num "+
							" from  bookType inner join tb_bookInfo on tb_bookInfo.type_id =bookType.type_id";					
		//List<Object> params=new ArrayList<Object>();
		return db.findMultiObject(sql, null);
	}

	//根据书籍名称查找
	public static List<Map<String, Object>> findBookByBookName(String bookName) throws SQLException, IOException {
		DBHelper db=new DBHelper();
		String sql="select  ISBN,book_name,type_name,book_writer,book_cost,book_publisher,book_num "+
				" from  bookType inner join tb_bookInfo on tb_bookInfo.type_id =bookType.type_id  where book_name =? ";	
		List<Object> params=new ArrayList<Object>();
		params.add(bookName);
		return db.findMultiObject(sql, params);
	}
	
	//根据书籍类别查找
	public static  List<Map<String, Object>> findBookByTypeName(String typeName) throws SQLException, IOException  {
		DBHelper db=new DBHelper();
		String sql="select  ISBN,book_name,type_name,book_writer,book_cost,book_publisher,book_num "+
				" from  bookType inner join tb_bookInfo on tb_bookInfo.type_id =bookType.type_id  where type_name=? ";	
		List<Object> params=new ArrayList<Object>();
		params.add(typeName);
		return db.findMultiObject(sql, params);
	}
	
	//根据书籍编号查找
	public static  List<Map<String, Object>> findBookByISBN(String ISBN) throws SQLException, IOException  {
		DBHelper db=new DBHelper();
		String sql="select  ISBN,book_name,type_name,book_writer,book_cost,book_publisher,book_num "+
				" from  bookType inner join tb_bookInfo on tb_bookInfo.type_id =bookType.type_id   where ISBN=? ";	
		List<Object> params=new ArrayList<Object>();
		params.add(ISBN);
		return db.findMultiObject(sql, params);
	}
	
	//根据书籍作者查找
	public static  List<Map<String, Object>> findBookByBookWriter(String bookWriter) throws SQLException, IOException  {
		DBHelper db=new DBHelper();
		String sql="select  ISBN,book_name,type_name,book_writer,book_cost,book_publisher,book_num "+
				" from  bookType inner join tb_bookInfo on tb_bookInfo.type_id =bookType.type_id  where book_writer = ?";	
		List<Object> params=new ArrayList<Object>();
		params.add(bookWriter);
		return db.findMultiObject(sql, params);
	}

	//查看所借阅的记录信息
	public List<Map<String, Object>> findBorrowBook(String bookID) throws SQLException, IOException {
		DBHelper db=new DBHelper();
		String sql="select  book_name,ISBN,type_name,reader_name,reader_id,borrowDate,backDate "+
				" from  bookType inner join tb_bookInfo on tb_bookInfo.type_id =bookType.type_id"+
				"  inner join Borrow on tb_bookInfo.ISBN =Borrow. ISBN  inner join   tb_reader"+
				" on Borrow.reader_id = tb_reader.reader_id  where reader_id = ?";

		List<Object> params=new ArrayList<Object>();
		params.add(bookID);
		return db.findMultiObject(sql, params);
	}

	
	public List<Map<String, Object>> findBorrowReaderNew(String info) throws SQLException, IOException {
		DBHelper db=new DBHelper();
		String sql="select  reader_name,maxNum,reader_tel  from tb_reader where reader_id=?";

		List<Object> params=new ArrayList<Object>();
		params.add(info);
		return db.findMultiObject(sql, params);
	}
	
		
	public List<Map<String, Object>> findBorrowBookNew(String info) throws SQLException, IOException {
		DBHelper db=new DBHelper();
		String sql="select  book_name,type_name,book_num,keepMoney,book_borrowDay  from bookType inner join  tb_bookInfo  on "+
							"  tb_bookInfo.type_id = bookType.type_id    where ISBN=?";

		List<Object> params=new ArrayList<Object>();
		params.add(info);
		return db.findMultiObject(sql, params);
	}



	/*
	 *  params.add(Reader_id);
					    params.add(Book_id);		
					    params.add(book_borrowDay);
					    params.add(sysdate);
					    params.add(operator_id);
	 */
	//借阅操作
	public boolean BorrowBook(List<Object> params) throws SQLException, IOException {
		String sql="insert into Borrow values(seq_Borrow_borrow_id.nextval,?,?,?,to_date(?,'yyyy-MM-dd'),null,?)";
		int i=db.doUpdate(sql,params);
		
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}

	//借阅后的修改
	
	
	public boolean BorrowUpdate(int Reader_id) throws SQLException,IOException {
		List<Object> params=new ArrayList<Object>();
		params.add(Reader_id);
		String sql="update tb_reader set maxNum = maxNum-1 where reader_id = ?";
		//String sql2="update tb_inventory set inv_num = inv_num-1 where ISBN =?";		
		int i=db.doUpdate(sql,params);
		if(i>0){
			return true;
		
		}else{
			return false;
		}
		//db.doUpdrnate(sql2, params);
		
	}
		
	public boolean BorrowUpdate1(int Book_id) throws SQLException,IOException {
		List<Object> params=new ArrayList<Object>();
		params.add(Book_id);
		String sql="update tb_bookInfo set book_num = book_num-1 where ISBN =?";		
		int i=db.doUpdate(sql,params);
		if(i>0){
			return true;
		
		}else{
			return false;
		}	
	}
	
	//图书借阅显示
	public List<Map<String, Object>> findBookBorrow(String info) throws SQLException, IOException {
		/*
		 select 
borrow.ISBN,borrowdate,(borrowdate+book_borrowDay)returndate,reader_id  
from borrow  
inner join tb_bookInfo  
on borrow.ISBN = tb_bookInfo.ISBN 
inner join bookType  on 
tb_bookInfo.type_id = bookType.type_id  
where reader_id =10220;
		 */
		DBHelper db=new DBHelper();
		String sql="select borrow.ISBN,borrowdate,(borrowdate+book_borrowDay)returndate,reader_id  from borrow  inner join tb_bookInfo  "+
				"on borrow.ISBN = tb_bookInfo.ISBN inner join bookType  on tb_bookInfo.type_id = bookType.type_id  where reader_id =?";
		List<Object> params=new ArrayList<Object>();
		params.add(info);
		return db.findMultiObject(sql, params);
	}



	//图书归还显示
	public List<Map<String, Object>> findReturnBookNew(String info) throws SQLException, IOException {
		DBHelper db=new DBHelper();	
		String sql="select borrowDate, isback,(floor(sysdate-borrowDate))ANOD,(abs(floor(((sysdate-borrowDate)-isback))))overlap_days,(abs((floor((sysdate-borrowDate)-isback))*book_overduMoney))overduMoney "+//
						"from Borrow b inner join tb_bookInfo bf on b.isbn = bf.isbn "+
						"inner join bookType bt on bf.type_id = bt.type_id  where reader_id =?";
		List<Object> params=new ArrayList<Object>();
		params.add(info);
		return db.findMultiObject(sql, params);
	}


	public boolean ReturnUpdateReader(int Reader_id) throws SQLException, IOException {
		List<Object> params=new ArrayList<Object>();
		params.add(Reader_id);
		String sql="update tb_reader set maxNum = maxNum+1 where reader_id = ?";
		//String sql2="update tb_inventory set inv_num = inv_num-1 where ISBN =?";		
		int i=db.doUpdate(sql,params);
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}


	public boolean ReturnUpdateBook(int ISBN) throws SQLException, IOException {
		List<Object> params=new ArrayList<Object>();
		params.add(ISBN);
		String sql="update tb_bookInfo set book_num = book_num+1 where ISBN = ?";
	
		int i=db.doUpdate(sql,params);
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}


	public boolean ReturnUpdateBorrow(int ISBN) throws SQLException, IOException {
		List<Object> params=new ArrayList<Object>();
		params.add(ISBN);
		String sql="delete Borrow where ISBN =  ?";
		int i=db.doUpdate(sql,params);
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}


	public boolean OrderBook(List<Object> params) throws SQLException, IOException {
		String sql="insert into tb_order values(seq_tb_order_order_id.nextval,?,?,?,?,to_date(?,'yyyy-MM-dd'),?)";
		int i=db.doUpdate(sql,params);
		
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}


	public List<Map<String, Object>> RenewBookNew(int ISBN) throws SQLException, IOException {
		DBHelper db=new DBHelper();	
		String sql="select book_name,borrowDate from Borrow inner join tb_bookInfo on Borrow.ISBN = tb_bookInfo.ISBN where Borrow.ISBN = ?";
		List<Object> params=new ArrayList<Object>();
		params.add(ISBN);
		return db.findMultiObject(sql, params);
	}


	public boolean RenewBorrowUpdate(String book_name) throws SQLException, IOException {
		List<Object> params=new ArrayList<Object>();
		params.add(book_name);
		String sql="update Borrow set isback = isback+15 where ISBN = (select ISBN from tb_bookInfo where book_name =?)";
		int i=db.doUpdate(sql,params);
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}


	public List<Map<String, Object>> showAllOrder() throws SQLException, IOException{
		DBHelper db=new DBHelper();
		
		String sql="select order_bookType,order_bookName, order_bookNum,order_bookMoney,order_date, operator_id from  tb_order";
		
		return db.findMultiObject(sql, null);
	}


	//图书 验收信息表
	public List<Map<String, Object>> ReceiveBookNew(String book_name) throws SQLException, IOException {
		DBHelper db=new DBHelper();	
		String sql="select order_bookType,order_bookName,order_bookNum,order_bookMoney, order_date,operator_id from tb_order  where order_bookName = ?";
		List<Object> params=new ArrayList<Object>();
		params.add(book_name);
		return db.findMultiObject(sql, params);
	}


	//图书验收 插入库存
	public boolean ReceiveBook(List<Object> params) throws SQLException, IOException {
		String sql="insert into tb_receive values(?,?,?,?,to_date(?,'yyyy-MM-dd'),?)";
		int i=db.doUpdate(sql,params);
		
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}
	/*
	 *     rece_typeName  varchar2(20) not null,--图书类别
       rece_bookName varchar2(20) not null ,--图书名称
       rece_bookCost number(20) not null,--图书价格
       rece_bookNum number not null ,--图书数量
       rece_orderDate date ,--订购日期
       operator_id int references tb_operator(operator_id)--操作员
)
	 */

	public boolean ReceiveBook(String book_name) throws SQLException, IOException {
		List<Object> params=new ArrayList<Object>();
		params.add(book_name);
		String sql="delete tb_order where order_bookName =  ?";
		int i=db.doUpdate(sql,params);
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}

	public List<Map<String, Object>> findUpdateBookNew(String ISBN) throws SQLException, IOException {
		DBHelper db=new DBHelper();	
		String sql="select book_num from tb_bookInfo where ISBN=?";
		List<Object> params=new ArrayList<Object>();
		params.add(ISBN);
		
		return db.findMultiObject(sql, params);
	}




	public boolean UpdateBookNew(List<Object> params)  throws SQLException, IOException {
//		List<Object> params=new ArrayList<Object>();
//		params.add(ISBN);
		String sql="update tb_bookInfo set book_num = ? where ISBN = ?";
		int i=db.doUpdate(sql,params);
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}

	public boolean UpdatePwdReader(List<Object> params) throws SQLException, IOException {
		String sql="update tb_reader set reader_pwd = ? where reader_name= ?";
		//--将学号为10001 学生 的性别 改成 女
		//update tb_operator set sex = '女' where sid =10001;
		//--修改学生信息表 学号4号改为5号
		//update stuInfo set sid = 5 where sid =4;
		//update tb_operator set operator_pwd = ? where operator_pwd = ?
		int i=db.doUpdate(sql,params);
		
		if(i>0){
			return true;
		
		}else{
			return false;
		}
	}



	public List<Map<String, Object>> countBorrowByColn() throws SQLException, IOException {
		DBHelper db=new DBHelper();	
		String sql="select tb.book_name b,count(borrow_id) c  from Borrow b inner join tb_bookInfo tb on b.isbn = tb.isbn group by tb.book_name";
		List<Object> params=new ArrayList<Object>();	
		return db.findMultiObject(sql, params);
	}

	public List<Map<String, Object>> countBorrowByColn1() throws SQLException, IOException {
		DBHelper db=new DBHelper();	
		String sql="select extract(DAY from borrowDate ) b,count(borrow_id) c  from Borrow b inner join tb_bookInfo tb on b.isbn = tb.isbn group by borrowDate";
		List<Object> params=new ArrayList<Object>();	
		return db.findMultiObject(sql, params);
	}

	public List<Map<String, Object>> countBorrowByColn2() throws SQLException, IOException {
		DBHelper db=new DBHelper();	
		String sql="select extract(MONTH from borrowDate ) b,count(borrow_id) c  from Borrow b inner join tb_bookInfo tb on b.isbn = tb.isbn group by borrowDate";
		List<Object> params=new ArrayList<Object>();	
		return db.findMultiObject(sql, params);
	}

	public List<Map<String, Object>> countBorrowByColn3() throws SQLException, IOException {
		DBHelper db=new DBHelper();	
		String sql="select extract(YEAR from borrowDate ) b,count(borrow_id) c  from Borrow b inner join tb_bookInfo tb on b.isbn = tb.isbn group by borrowDate";
		List<Object> params=new ArrayList<Object>();	
		return db.findMultiObject(sql, params);
	}

}
