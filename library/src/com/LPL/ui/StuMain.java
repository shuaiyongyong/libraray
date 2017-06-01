package com.LPL.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.LPL.commons.LogUtil;
import com.LPL.commons.MessageUtil;
import com.LPL.commons.PwdCommon;
import com.LPL.dao.OperatorDAO;
import com.LPL.dao.StudentDAO;

import org.eclipse.swt.widgets.Composite;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class StuMain {

	protected Shell shell;
	StackLayout stackLayout;
	OperatorDAO operatorDAO =new OperatorDAO();
	StudentDAO studentDAO =new StudentDAO();
	Composite com_select;
	Composite composite;
	Composite com_renew;
	Composite com_selectBorrow;
	Composite com_mimaxg;
	private Text text;
	private Table table;
	private Text text_1;
	private Table table_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Table table_2;
	private Text text_mimaxg1;
	private Text text_mimaxg2;
	private Text text_mimaxg3;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StuMain window = new StuMain();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(StuMain.class, "/images/logo.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);//窗体上的控件背景透明
		shell.setSize(1000,650);
		shell.setText("读者管理系统");
		//主窗体居中
		//Dimension：标出尺寸，Toolkit:工具包
		Dimension dem=Toolkit.getDefaultToolkit().getScreenSize();
		//窗体居中显示
		shell.setLocation((dem.width-shell.getSize().x)/2,(dem.height-shell.getSize().y)/2);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("读者信息管理");
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
		MenuItem mntmNewItem_2 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl =com_mimaxg;
				composite.layout();
			}
		});
		mntmNewItem_2.setText("更改口令");
		
		MenuItem mntmNewSubmenu_1 = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu_1.setText("图书借阅查询");
		
		Menu menu_2 = new Menu(mntmNewSubmenu_1);
		mntmNewSubmenu_1.setMenu(menu_2);
		
		MenuItem mntmNewItem = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_select;
				composite.layout();
			}
		});
		mntmNewItem.setText("图书查询");
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmNewItem_3 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_selectBorrow;
				composite.layout();
			}
		});
		mntmNewItem_3.setText("查看已借阅信息");
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmNewItem_4 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_renew;
				composite.layout();
			}
		});
		mntmNewItem_4.setText("续借办理");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
					shell.dispose();
					// t.stop();
					Login logina = new Login();
					logina.open();
				
			}
		});
		mntmNewItem_1.setText("退出系统");
		
		 composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(StuMain.class, "/images/主界面背景图.jpg"));
		stackLayout = new StackLayout();
		composite.setLayout(stackLayout);
		
		com_select = new Composite(composite, SWT.NONE);
		com_select.setBackgroundImage(SWTResourceManager.getImage(StuMain.class, "/images/931294_105434583197_2[1]_副本.jpg"));
		
		final Combo combo = new Combo(com_select, SWT.NONE);
		combo.add("");
		combo.add("书籍名称");
		combo.add("书籍类别");
		combo.add("书籍编号");
		combo.add("书籍作者");
		combo.select(0);
		combo.setBounds(137, 64, 144, 25);
		combo.select(0);
		
		text = new Text(com_select, SWT.BORDER);
		text.setBounds(414, 64, 264, 21);
		
		table = new Table(com_select, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(129, 176, 705, 268);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("书籍编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("书籍名称");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("书籍类别");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("书籍作者");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("书籍价格");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("出版社");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("当前库存量");
		
		Label label = new Label(com_select, SWT.NONE);
		label.setText("请选择查询项目");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		label.setFont(SWTResourceManager.getFont("黑体", 12, SWT.NORMAL));
		label.setBounds(10, 10, 122, 23);
		
		Label label_1 = new Label(com_select, SWT.NONE);
		label_1.setText("查询结果显示");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_1.setFont(SWTResourceManager.getFont("黑体", 12, SWT.NORMAL));
		label_1.setBounds(10, 129, 122, 23);
		
		Button button = new Button(com_select, SWT.NONE);
		//查询
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text.getText().trim();

				// 表示文本框没有输入查询条件
				if (null == info || "".equals(info)) {
					// 第一种方法：提示错误信息
					// MessageUtil.promt(shell, "错误提示", "必须输入班级或学号信息");
					// 第二种方法：查看所有信息
					try {
						java.util.List<Map<String, Object>> list = operatorDAO.findAllBook();
						table.removeAll();
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {
								// System.out.println(list.size());
								TableItem tableItem_2 = new TableItem(table, SWT.NONE);
								tableItem_2.setText(new String[] { map.get("ISBN").toString(),
										map.get("BOOK_NAME").toString(), map.get("TYPE_NAME").toString(),
										map.get("BOOK_WRITER").toString(), map.get("BOOK_COST").toString(),
										map.get("BOOK_PUBLISHER").toString(), map.get("BOOK_NUM").toString() });
							}
						} else {
							MessageUtil.promt(shell, "温馨提示", "无书籍信息");
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					} catch (IOException e1) {

						e1.printStackTrace();
					}
					return;
				}

				String type = combo.getText();
				if ("书籍名称".equals(type.trim())) {
					// 根据书籍名称查询

					try {
						List<Map<String, Object>> list = operatorDAO.findBookByBookName(info);
						table.removeAll();
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {
								// System.out.println(list.size());

								TableItem tableItem_2 = new TableItem(table, SWT.NONE);
								tableItem_2.setText(new String[] { map.get("ISBN").toString(),
										map.get("BOOK_NAME").toString(), map.get("TYPE_NAME").toString(),
										map.get("BOOK_WRITER").toString(), map.get("BOOK_COST").toString(),
										map.get("BOOK_PUBLISHER").toString(), map.get("BOOK_NUM").toString() });
							}

						} else {
							MessageUtil.promt(shell, "温馨提示", "查无此书籍名称信息");
						}

					} catch (SQLException e1) {
						LogUtil.logger.info(e1.getMessage() + new Date());
					} catch (IOException e1) {
						LogUtil.logger.info(e1.getMessage() + new Date());
					}
				} else if ("书籍类别".equals(type.trim())) {
					// 根据书籍类别进行查询

					try {
						List<Map<String, Object>> list = operatorDAO.findBookByTypeName(info);
						table.removeAll();
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {
								TableItem tableItem_2 = new TableItem(table, SWT.NONE);
								tableItem_2.setText(new String[] { map.get("ISBN").toString(),
										map.get("BOOK_NAME").toString(), map.get("TYPE_NAME").toString(),
										map.get("BOOK_WRITER").toString(), map.get("BOOK_COST").toString(),
										map.get("BOOK_PUBLISHER").toString(), map.get("BOOK_NUM").toString() });

							}
						} else {
							MessageUtil.promt(shell, "温馨提示", "查无此书籍类别信息");
						}

					} catch (SQLException e1) {
						LogUtil.logger.info(e1.getMessage() + new Date());
					} catch (IOException e1) {
						LogUtil.logger.info(e1.getMessage() + new Date());
					}

				} else if ("书籍编号".equals(type.trim())) {
					// 根据书籍编号进行查询

					try {
						List<Map<String, Object>> list = operatorDAO.findBookByISBN(info);
						table.removeAll();
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {
								TableItem tableItem_2 = new TableItem(table, SWT.NONE);
								tableItem_2.setText(new String[] { map.get("ISBN").toString(),
										map.get("BOOK_NAME").toString(), map.get("TYPE_NAME").toString(),
										map.get("BOOK_WRITER").toString(), map.get("BOOK_COST").toString(),
										map.get("BOOK_PUBLISHER").toString(), map.get("BOOK_NUM").toString() });

							}
						} else {
							MessageUtil.promt(shell, "温馨提示", "查无此书籍编号信息");
						}

					} catch (SQLException e1) {
						LogUtil.logger.info(e1.getMessage() + new Date());
					} catch (IOException e1) {
						LogUtil.logger.info(e1.getMessage() + new Date());
					}
				} else if ("书籍作者".equals(type.trim())) {
					// 根据书籍作者进行查询

					try {
						List<Map<String, Object>> list = operatorDAO.findBookByBookWriter(info);
						table.removeAll();
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {
								TableItem tableItem_2 = new TableItem(table, SWT.NONE);
								tableItem_2.setText(new String[] { map.get("ISBN").toString(),
										map.get("BOOK_NAME").toString(), map.get("TYPE_NAME").toString(),
										map.get("BOOK_WRITER").toString(), map.get("BOOK_COST").toString(),
										map.get("BOOK_PUBLISHER").toString(), map.get("BOOK_NUM").toString() });

							}
						} else {
							MessageUtil.promt(shell, "温馨提示", "查无此书籍作者信息");
						}

					} catch (SQLException e1) {
						LogUtil.logger.info(e1.getMessage() + new Date());
					} catch (IOException e1) {
						LogUtil.logger.info(e1.getMessage() + new Date());
					}

				}

				
			}
		});
		button.setText("查询");
		button.setBounds(568, 522, 75, 25);
		
		Button button_1 = new Button(com_select, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean flag = MessageDialog.openConfirm(shell, "温馨提示", "您确定要退出吗？");
				if (flag) {
					Display.getDefault().close();
					System.exit(0);
				}
			}
		});
		button_1.setText("退出");
		button_1.setBounds(715, 522, 75, 25);
		
		 com_renew = new Composite(composite, SWT.NONE);
		 com_renew.setBackgroundImage(SWTResourceManager.getImage(StuMain.class, "/images/11141412_083856958875_2.png"));
		 
		 Label label_2 = new Label(com_renew, SWT.NONE);
		 label_2.setText("读者编号：");
		 label_2.setBounds(236, 87, 61, 17);
		 
		 text_1 = new Text(com_renew, SWT.BORDER);
		 text_1.setBounds(385, 84, 151, 23);
		 
		 Button button_2 = new Button(com_renew, SWT.NONE);
		 button_2.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		String info = text_1.getText().trim();
				List<Map<String, Object>> list;
				try {
					list = operatorDAO.findBookBorrow(info);

					table_1.removeAll();
					if (null != list && list.size() > 0) {
						for (Map<String, Object> map : list) {
							TableItem tableItem_3 = new TableItem(table_1, SWT.NONE);
							tableItem_3.setText(new String[] { map.get("ISBN").toString(),
									map.get("BORROWDATE").toString(), map.get("RETURNDATE").toString(),
									map.get("READER_ID").toString() });
						}
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
		 	}
		 });
		 button_2.setText("查询");
		 button_2.setBounds(690, 82, 80, 27);
		 
		 table_1 = new Table(com_renew, SWT.BORDER | SWT.FULL_SELECTION);
		 table_1.setLinesVisible(true);
		 table_1.setHeaderVisible(true);
		 table_1.setBounds(195, 156, 605, 218);
		 
		 TableColumn tableColumn_7 = new TableColumn(table_1, SWT.NONE);
		 tableColumn_7.setWidth(150);
		 tableColumn_7.setText("书籍编号");
		 
		 TableColumn tableColumn_8 = new TableColumn(table_1, SWT.NONE);
		 tableColumn_8.setWidth(150);
		 tableColumn_8.setText("借书日期");
		 
		 TableColumn tableColumn_9 = new TableColumn(table_1, SWT.NONE);
		 tableColumn_9.setWidth(151);
		 tableColumn_9.setText("应还日期");
		 
		 TableColumn tableColumn_10 = new TableColumn(table_1, SWT.NONE);
		 tableColumn_10.setWidth(150);
		 tableColumn_10.setText("读者编号");
		 
		 Menu menu_3 = new Menu(table_1);
		 table_1.setMenu(menu_3);
		 
		 MenuItem menuItem = new MenuItem(menu_3, SWT.NONE);
		 menuItem.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		TableItem[] item=table_1.getSelection();
				for(TableItem t: item){
					//获取到被选中的	ISBN
					int ISBN=Integer.parseInt(t.getText(0));
					try {
					List<Map<String, Object>> list = operatorDAO.RenewBookNew(ISBN);
					if (null != list && list.size() > 0) {
						for (Map<String, Object> map : list) {
							text_2.setText(map.get("BOOK_NAME").toString());
							text_3.setText(map.get("BORROWDATE").toString());
						}
					} else {
						MessageUtil.promt(shell, "温馨提示", "续借失败！");
					}
					return;
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
		 	}
		 });
		 menuItem.setText("续借");
		 
		 Label label_3 = new Label(com_renew, SWT.NONE);
		 label_3.setText("图书名称：");
		 label_3.setBounds(195, 425, 61, 17);
		 
		 Label label_4 = new Label(com_renew, SWT.NONE);
		 label_4.setText("借阅日期：");
		 label_4.setBounds(195, 488, 61, 17);
		 
		 text_2 = new Text(com_renew, SWT.BORDER);
		 text_2.setBounds(296, 422, 151, 23);
		 
		 text_3 = new Text(com_renew, SWT.BORDER);
		 text_3.setBounds(296, 485, 151, 23);
		 
		 Label label_5 = new Label(com_renew, SWT.NONE);
		 label_5.setText("管 理 员：");
		 label_5.setBounds(536, 425, 61, 17);
		 
		 text_4 = new Text(com_renew, SWT.BORDER);
		 text_4.setBounds(649, 422, 151, 23);
		 
		 Button button_3 = new Button(com_renew, SWT.NONE);
		 button_3.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		String book_name = text_2.getText();
		 		
				boolean type5;
				try {
					type5 = operatorDAO.RenewBorrowUpdate(book_name);
				
					if(type5){
					MessageUtil.promt(shell, "温馨提示", "续借成功！");
					text_2.setText("");
					text_3.setText("");
					text_4.setText("");
				}else{
					MessageUtil.promt(shell, "温馨提示", "续借失败！");
				}
		
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 	}
		 });
		 button_3.setText("确定续借");
		 button_3.setBounds(536, 483, 80, 27);
		 
		 Button button_4 = new Button(com_renew, SWT.NONE);
		 button_4.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		boolean flag = MessageDialog.openConfirm(shell, "温馨提示", "您确定要退出吗？");
				if(flag){
					Display.getDefault().close();
					System.exit(0);
				}	
		 	}
		 });
		 button_4.setText("退出");
		 button_4.setBounds(720, 483, 80, 27);
		
		com_selectBorrow = new Composite(composite, SWT.NONE);
		com_selectBorrow.setBackgroundImage(SWTResourceManager.getImage(StuMain.class, "/images/69b1OOOPIC91[1]_副本.jpg"));
		
		Label lblNewLabel = new Label(com_selectBorrow, SWT.NONE);
		lblNewLabel.setBounds(257, 76, 61, 17);
		lblNewLabel.setText("读者编号：");
		
		text_5 = new Text(com_selectBorrow, SWT.BORDER);
		text_5.setBounds(405, 73, 137, 23);
		
		Button btnNewButton = new Button(com_selectBorrow, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text_5.getText().trim();
				try {
					List<Map<String, Object>> list;
					list = operatorDAO.findBorrowBook(info);
					table_2.removeAll();
					if (null != list && list.size() > 0) {
						for (Map<String, Object> map : list) {
							// System.out.println(list.size());
							TableItem tableItem_1 = new TableItem(table_2, SWT.NONE);
							// System.out.println(map.toString());
							tableItem_1
									.setText(new String[] { map.get("BOOK_NAME").toString(), map.get("ISBN").toString(),
											map.get("TYPE_NAME").toString(), map.get("READER_NAME").toString(),
											map.get("READER_ID").toString(), map.get("BORROWDATE").toString(),null});// 
						}
						
					} else {
						MessageUtil.promt(shell, "温馨提示", "该读者没有借阅书籍信息！");
					}
					return;
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(667, 71, 80, 27);
		btnNewButton.setText("查询");
		
		table_2 = new Table(com_selectBorrow, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setLinesVisible(true);
		table_2.setHeaderVisible(true);
		table_2.setBounds(140, 133, 704, 230);
		
		TableColumn tableColumn_11 = new TableColumn(table_2, SWT.NONE);
		tableColumn_11.setWidth(100);
		tableColumn_11.setText("图书名称");
		
		TableColumn tableColumn_12 = new TableColumn(table_2, SWT.NONE);
		tableColumn_12.setWidth(100);
		tableColumn_12.setText("图书编号");
		
		TableColumn tableColumn_13 = new TableColumn(table_2, SWT.NONE);
		tableColumn_13.setWidth(100);
		tableColumn_13.setText("图书类别");
		
		TableColumn tableColumn_14 = new TableColumn(table_2, SWT.NONE);
		tableColumn_14.setWidth(100);
		tableColumn_14.setText("读者姓名");
		
		TableColumn tableColumn_15 = new TableColumn(table_2, SWT.NONE);
		tableColumn_15.setWidth(100);
		tableColumn_15.setText("读者编号");
		
		TableColumn tableColumn_16 = new TableColumn(table_2, SWT.NONE);
		tableColumn_16.setWidth(100);
		tableColumn_16.setText("借书时间");
		
		TableColumn tableColumn_17 = new TableColumn(table_2, SWT.NONE);
		tableColumn_17.setWidth(100);
		tableColumn_17.setText("归还时间");
		
		com_mimaxg = new Composite(composite, SWT.NONE);
		com_mimaxg.setBackgroundImage(SWTResourceManager.getImage(StuMain.class, "/images/11141412_083856958875_2.png"));
		
		Label label_6 = new Label(com_mimaxg, SWT.NONE);
		label_6.setText("密码修改");
		label_6.setFont(SWTResourceManager.getFont("楷体", 25, SWT.BOLD));
		label_6.setBounds(457, 93, 154, 33);
		
		Label label_7 = new Label(com_mimaxg, SWT.NONE);
		label_7.setText("请输入原密码：");
		label_7.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD | SWT.ITALIC));
		label_7.setBounds(360, 170, 130, 24);
		
		Label label_8 = new Label(com_mimaxg, SWT.NONE);
		label_8.setText("请输入新密码：");
		label_8.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD | SWT.ITALIC));
		label_8.setBounds(360, 249, 130, 24);
		
		Label label_9 = new Label(com_mimaxg, SWT.NONE);
		label_9.setText("确认新密码：");
		label_9.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD | SWT.ITALIC));
		label_9.setBounds(360, 331, 130, 24);
		
		text_mimaxg1 = new Text(com_mimaxg, SWT.BORDER);
		text_mimaxg1.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		text_mimaxg1.setBounds(541, 167, 141, 23);
		
		text_mimaxg2 = new Text(com_mimaxg, SWT.BORDER | SWT.PASSWORD);
		text_mimaxg2.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		text_mimaxg2.setBounds(541, 246, 141, 23);
		
		text_mimaxg3 = new Text(com_mimaxg, SWT.BORDER | SWT.PASSWORD);
		text_mimaxg3.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		text_mimaxg3.setBounds(541, 328, 141, 23);
		
		Button Button_mimaxgfinish = new Button(com_mimaxg, SWT.NONE);
		Button_mimaxgfinish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//获取旧密码 新密码1 新密码2
	    		String oldpwd=text_mimaxg1.getText();
	    		String newpwd=text_mimaxg2.getText();
	    		String newpwd1=text_mimaxg3.getText();
	    		
	    		
	    		List<Object> params=new ArrayList<Object>();
	    		params.add(newpwd1);
	    		params.add(PwdCommon.username);
	    		if(newpwd1.equals(newpwd)){
	    			try {
						boolean flag=operatorDAO.UpdatePwdReader(params);
						if(flag){
							MessageUtil.promt(shell,"提示", "密码修改成功");
							//清空页面数据
							//text_mimaxg1.setText("");
				    		//text_mimaxg2.setText("");
				    		//text_mimaxg3.setText("");
				    		shell.dispose();
				    	//	t.stop();
				    		Login logina = new Login();
				    		logina.open();
						}else{
							MessageUtil.promt(shell,"提示", "密码修改失败");
						}
						
					} catch (SQLException e1) {
						//  Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						//  Auto-generated catch block
						e1.printStackTrace();
					}
	    		}else{
	    			MessageUtil.promt(shell,"提示", "新密码需输入一致");
	    		}
			}
		});
		Button_mimaxgfinish.setText("确认");
		Button_mimaxgfinish.setFont(SWTResourceManager.getFont("宋体", 14, SWT.BOLD));
		Button_mimaxgfinish.setBounds(360, 432, 80, 27);
		
		Button Button_mimaxgreset = new Button(com_mimaxg, SWT.NONE);
		Button_mimaxgreset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//清空页面数据
	    		text_mimaxg1.setText("");
	    		text_mimaxg2.setText("");
	    		text_mimaxg3.setText(""); 
	    		
			}
		});
		Button_mimaxgreset.setText("重置");
		Button_mimaxgreset.setFont(SWTResourceManager.getFont("宋体", 14, SWT.BOLD));
		Button_mimaxgreset.setBounds(602, 432, 80, 27);
		
		Label label_10 = new Label(com_mimaxg, SWT.NONE);
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_10.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD | SWT.ITALIC));
		label_10.setBounds(728, 331, 154, 24);
		
		

	}
}
