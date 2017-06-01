package com.LPL.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

import com.LPL.commons.LogUtil;
import com.LPL.commons.MessageUtil;
import com.LPL.commons.PwdCommon;
import com.LPL.dao.BookInfoDAO;
import com.LPL.dao.OperatorDAO;
import com.LPL.dao.ReaderDao;
import com.LPL.dao.StudentDAO;
import com.ibm.icu.util.BytesTrie.Entry;
import com.LPL.ui.Main;

import org.eclipse.swt.widgets.Menu;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.custom.TableTree;

public class Main {

	protected Shell shell;
	private MenuItem 基础数据维护;
	Date date;
	DateFormat format;
	StackLayout stackLayout;
	StackLayout sLayout = new StackLayout();
	Composite composite;
	Composite com_borrow;
	Composite com_return;
	Composite com_select;
	Composite com_bookOrder;
	Composite com_bookReceive;
	Composite com_renew;
	Composite com_mimaxg;
	Composite com_readerselect;
	Composite com_readerenroll;
	Composite com_readeralter;
	Composite com_booksort;
	Composite com_bookenroll;
	Composite com_bookupdate;
	Composite com_BarChart;
	ChartComposite compositechart;
	Composite composite_1;
	Combo combo;
	Combo combo_srotid;
	Label label_Pwd;
	Label label_ID ;
	Label label_TEL;
	OperatorDAO operatorDAO = new OperatorDAO();
	StudentDAO studentDAO = new StudentDAO();
	ReaderDao readerDao = new ReaderDao();
	BookInfoDAO bookInfoDAO = new BookInfoDAO();

	private Text reader_id;
	private Text reader_name;
	private Text maxNum;
	private Text reader_tel;
	private Text ISBN;
	private Text book_name;
	private Text type_name;
	private Text inv_num;
	private Table table;
	private Text text_8;
	private Text text_9;
	private Text text_10;
	private Table table_1;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_14;
	private Text text_15;
	private Text text_16;
	private Text text_17;
	private Text text_18;
	private Table table_2;
	private Text keepMoney;
	private Text book_borrowDay;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Table table_3;
	private Text text_6;
	private Text text_7;
	private Text text_19;
	private Text text_20;
	private Text text_21;
	private Text text_22;
	private Text text_23;
	private Text text_24;
	private Table table_4;
	private Text text_25;
	private Text text_26;
	private Text text_mimaxg1;
	private Text text_mimaxg2;
	private Text text_mimaxg3;
	private Text text_readerselect;
	private Table table_readerselect;
	private Text text_name;
	private Text text_pwd;
	private Text text_idcard;
	private Text text_phone;
	private Text text_num;
	private Text text_rselect;
	private Table table_update;
	private Text text_rreaderid;
	private Text text_Name;
	private Text text_rpwd1;
	private Text text_rmax;
	private Text text_rphone1;
	private Text text_rsfz1;
	private Text text_sortID;
	private Text text_sortname;
	private Text text_dateborrow;
	private Text text_overduemoney;
	private Text text_bookida;
	private Text text_bookname;
	private Text text_bookzz;
	private Text text_bookcbs;
	private Text text_bookyz;
	private Text text_bookmoney;
	private Text text_keepmoney;
	private Text text_booknum;
	private Text text_27;
	private Table table_5;
	private Text text_28;
	private Text text_booksortid;
	private Text text_Pwd;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		try {
			Main window = new Main();
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
		shell.setImage(SWTResourceManager.getImage(Main.class, "/images/logo.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);// 窗体上的控件背景透明
		shell.setSize(1000, 640);
		shell.setText("图书管理系统");
		// 主窗体居中
		// Dimension：标出尺寸，Toolkit:工具包
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗体居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2, (dem.height - shell.getSize().y) / 2);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		基础数据维护 = new MenuItem(menu, SWT.CASCADE);
		基础数据维护.setImage(SWTResourceManager.getImage(Main.class, "/images/基础数据维护.jpg"));

		Menu menu_1 = new Menu(基础数据维护);
		基础数据维护.setMenu(menu_1);

		MenuItem mntmNewSubmenu_4 = new MenuItem(menu_1, SWT.CASCADE);
		mntmNewSubmenu_4.setText("读者信息管理");

		Menu menu_5 = new Menu(mntmNewSubmenu_4);
		mntmNewSubmenu_4.setMenu(menu_5);

		MenuItem mntmNewItem = new MenuItem(menu_5, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_readerenroll;
				composite.layout();
			}
		});
		mntmNewItem.setText("读者信息添加");

		new MenuItem(menu_5, SWT.SEPARATOR);

		MenuItem mntmNewItem_3 = new MenuItem(menu_5, SWT.NONE);
		mntmNewItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_readeralter;
				composite.layout();
			}
		});
		mntmNewItem_3.setText("读者信息修改/删除");

		new MenuItem(menu_5, SWT.SEPARATOR);

		MenuItem mntmNewItem_14 = new MenuItem(menu_5, SWT.NONE);
		mntmNewItem_14.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_readerselect;
				composite.layout();
				table_readerselect.removeAll();
			}
		});
		mntmNewItem_14.setText("读者信息查询");

		new MenuItem(menu_1, SWT.SEPARATOR);

		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.CASCADE);
		menuItem_1.setText("图书信息管理");

		Menu menu_7 = new Menu(menuItem_1);
		menuItem_1.setMenu(menu_7);

		MenuItem mntmNewItem_5 = new MenuItem(menu_7, SWT.NONE);
		mntmNewItem_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_bookenroll;
				composite.layout();
				try {// 自动加载显示图书类别信息
					List<Map<String, Object>> list = bookInfoDAO.showBooksort();
					combo_srotid.removeAll();
					for (Map<String, Object> map : list) {
						// 将类别名称加载到下拉列表
						combo_srotid.add(map.get("TYPE_ID") + "-" + map.get("TYPE_NAME").toString());

					}
					combo_srotid.select(0);// 设置下拉列表里面的默认值
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		mntmNewItem_5.setText("图书信息添加");

		new MenuItem(menu_7, SWT.SEPARATOR);

		MenuItem mntmNewItem_6 = new MenuItem(menu_7, SWT.NONE);
		mntmNewItem_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_bookupdate;
				composite.layout();
			}
		});

		mntmNewItem_6.setText("图书信息修改");

		new MenuItem(menu_1, SWT.SEPARATOR);

		MenuItem menuItem = new MenuItem(menu_1, SWT.CASCADE);
		menuItem.setText("图书类别管理");

		Menu menu_6 = new Menu(menuItem);
		menuItem.setMenu(menu_6);

		MenuItem menuItem_2 = new MenuItem(menu_6, SWT.NONE);
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_booksort;
				composite.layout();
			}
		});
		menuItem_2.setText("图书类别添加");

		new MenuItem(menu_1, SWT.SEPARATOR);

		MenuItem mntmNewItem_2 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_select;
				composite.layout();
			}
		});
		mntmNewItem_2.setText("图书查询");

		MenuItem 借阅管理 = new MenuItem(menu, SWT.CASCADE);
		借阅管理.setImage(SWTResourceManager.getImage(Main.class, "/images/借阅管理.jpg"));

		Menu menu_2 = new Menu(借阅管理);
		借阅管理.setMenu(menu_2);

		MenuItem mntmNewItem_7 = new MenuItem(menu_2, SWT.NONE);
		// 图书借阅
		mntmNewItem_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_borrow;
				composite.layout();
			}
		});
		mntmNewItem_7.setText("图书借阅");

		new MenuItem(menu_2, SWT.SEPARATOR);

		MenuItem mntmNewItem_8 = new MenuItem(menu_2, SWT.NONE);
		// 图书归还
		mntmNewItem_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_return;
				composite.layout();
			}
		});
		mntmNewItem_8.setText("图书归还");

		new MenuItem(menu_2, SWT.SEPARATOR);

		MenuItem menuItem_6 = new MenuItem(menu_2, SWT.CASCADE);
		// 图书续借
		menuItem_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_renew;
				composite.layout();
			}
		});
		menuItem_6.setText("图书续借");

		new MenuItem(menu_2, SWT.SEPARATOR);

		MenuItem mntmNewItem_15 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_15.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_BarChart;
				composite.layout();
			}
		});
		mntmNewItem_15.setText("借阅统计");

		MenuItem 订购操作 = new MenuItem(menu, SWT.CASCADE);
		// 快捷搜索
		订购操作.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// System.out.println("图书查询与搜索");
			}
		});
		订购操作.setImage(SWTResourceManager.getImage(Main.class, "/images/xsdgcd.jpg"));

		Menu menu_4 = new Menu(订购操作);
		订购操作.setMenu(menu_4);

		MenuItem mntmNewItem_1 = new MenuItem(menu_4, SWT.NONE);
		// 新书订购
		mntmNewItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {// com_bookOrder
				stackLayout.topControl = com_bookOrder;
				composite.layout();
			}
		});
		mntmNewItem_1.setText("新书订购");

		new MenuItem(menu_4, SWT.SEPARATOR);

		MenuItem mntmNewItem_4 = new MenuItem(menu_4, SWT.NONE);
		mntmNewItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_bookReceive;
				composite.layout();
				table_3.removeAll();
				// 登录界面加载
				try {
					// 根据页面显示要求查看读者信息
					List<Map<String, Object>> list = operatorDAO.showAllOrder();
					// System.out.println(list.toString());//打印数据

					for (Map<String, Object> map : list) {

						TableItem tableItem5 = new TableItem(table_3, SWT.NONE);
						tableItem5.setText(new String[] { map.get("ORDER_BOOKTYPE").toString(),
								map.get("ORDER_BOOKNAME").toString(), map.get("ORDER_BOOKNUM").toString(),
								map.get("ORDER_BOOKMONEY").toString(), map.get("ORDER_DATE").toString(),
								map.get("OPERATOR_ID").toString() });
					}

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		mntmNewItem_4.setText("验收新书");

		MenuItem 系统维护 = new MenuItem(menu, SWT.CASCADE);
		系统维护.setImage(SWTResourceManager.getImage(Main.class, "/images/系统维护.jpg"));

		Menu menu_3 = new Menu(系统维护);
		系统维护.setMenu(menu_3);

		MenuItem mntmNewItem_10 = new MenuItem(menu_3, SWT.NONE);
		mntmNewItem_10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = com_mimaxg;
				composite.layout();
			}
		});
		mntmNewItem_10.setText("更改口令");

		composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/主界面背景图.jpg"));
		stackLayout = new StackLayout();
		composite.setLayout(stackLayout);

		com_borrow = new Composite(composite, SWT.NONE);
		com_borrow.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/ntk-1339-4840[1].jpg"));

		Label lblNewLabel = new Label(com_borrow, SWT.NONE);
		lblNewLabel.setBounds(68, 28, 105, 24);
		lblNewLabel.setText("读者编号：");

		Label label = new Label(com_borrow, SWT.NONE);
		label.setText("读者姓名：");
		label.setBounds(68, 81, 105, 24);

		Label label_1 = new Label(com_borrow, SWT.NONE);
		label_1.setText("可借阅量：");
		label_1.setBounds(68, 138, 105, 24);

		Label lblVip = new Label(com_borrow, SWT.NONE);
		lblVip.setText("手机号码：");
		lblVip.setBounds(68, 190, 105, 24);

		reader_id = new Text(com_borrow, SWT.BORDER);
		reader_id.setBounds(199, 25, 170, 21);

		reader_name = new Text(com_borrow, SWT.BORDER);
		reader_name.setBounds(199, 78, 170, 21);

		maxNum = new Text(com_borrow, SWT.BORDER);
		maxNum.setBounds(199, 135, 170, 21);

		reader_tel = new Text(com_borrow, SWT.BORDER);
		reader_tel.setBounds(199, 187, 170, 21);

		Label label_3 = new Label(com_borrow, SWT.NONE);
		label_3.setText("书籍编号：");
		label_3.setBounds(560, 28, 61, 18);

		Label label_4 = new Label(com_borrow, SWT.NONE);
		label_4.setText("书籍名称：");
		label_4.setBounds(560, 58, 61, 18);

		Label label_5 = new Label(com_borrow, SWT.NONE);
		label_5.setText("书籍类别：");
		label_5.setBounds(560, 95, 61, 18);

		Label label_6 = new Label(com_borrow, SWT.NONE);
		label_6.setText("库 存 量 ：");
		label_6.setBounds(560, 128, 61, 18);

		ISBN = new Text(com_borrow, SWT.BORDER);
		ISBN.setBounds(684, 25, 170, 21);

		book_name = new Text(com_borrow, SWT.BORDER);
		book_name.setBounds(684, 55, 170, 21);

		type_name = new Text(com_borrow, SWT.BORDER);
		type_name.setBounds(684, 92, 170, 21);

		inv_num = new Text(com_borrow, SWT.BORDER);
		inv_num.setBounds(684, 125, 170, 21);

		table = new Table(com_borrow, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(184, 231, 605, 218);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(150);
		tblclmnNewColumn.setText("书籍编号");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(150);
		tblclmnNewColumn_1.setText("借书日期");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(151);
		tableColumn.setText("应还日期");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(150);
		tableColumn_1.setText("读者编号");

		Label lblNewLabel_1 = new Label(com_borrow, SWT.NONE);
		lblNewLabel_1.setBounds(184, 489, 152, 15);
		lblNewLabel_1.setText("当前日期：");

		Label label_7 = new Label(com_borrow, SWT.NONE);
		label_7.setText("操  作  员：");
		label_7.setBounds(184, 538, 152, 15);

		final CalendarCombo borrow_date = new CalendarCombo(com_borrow, SWT.BORDER);
		borrow_date.setBounds(384, 486, 210, 21);
		// text_8 = new Text(com_borrow, SWT.BORDER);
		// text_8.setBounds(384, 486, 210, 21);

		text_9 = new Text(com_borrow, SWT.BORDER);
		text_9.setBounds(384, 535, 210, 21);

		Button btnNewButton = new Button(com_borrow, SWT.NONE);

		btnNewButton.setBounds(674, 484, 114, 25);
		btnNewButton.setText("借出当前图书");

		Button button = new Button(com_borrow, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reader_id.setText("");
				reader_name.setText("");
				maxNum.setText("");
				reader_tel.setText("");
				ISBN.setText("");
				book_name.setText("");
				type_name.setText("");
				inv_num.setText("");
				keepMoney.setText("");
				borrow_date.setText("");
				text_9.setText("");
				book_borrowDay.setText("");
				table.removeAll();
			}
		});
		button.setText("清除所有记录");
		button.setBounds(674, 533, 115, 25);

		Button btnNewButton_7 = new Button(com_borrow, SWT.NONE);

		btnNewButton_7.setBounds(375, 23, 80, 27);
		btnNewButton_7.setText("查询");

		Button button_1 = new Button(com_borrow, SWT.NONE);

		button_1.setText("查询");
		button_1.setBounds(860, 19, 80, 27);

		keepMoney = new Text(com_borrow, SWT.BORDER);
		keepMoney.setBounds(684, 159, 170, 21);

		Label lblNewLabel_6 = new Label(com_borrow, SWT.NONE);
		lblNewLabel_6.setText("押      金：");
		lblNewLabel_6.setBounds(560, 162, 61, 17);

		Label label_2 = new Label(com_borrow, SWT.NONE);
		label_2.setText("可借天数：");
		label_2.setBounds(560, 197, 61, 17);

		book_borrowDay = new Text(com_borrow, SWT.BORDER);
		book_borrowDay.setBounds(684, 194, 170, 21);

		com_return = new Composite(composite, SWT.NONE);
		com_return.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/69b1OOOPIC91[1]_副本.jpg"));

		Label lblNewLabel_2 = new Label(com_return, SWT.NONE);
		lblNewLabel_2.setBounds(10, 10, 73, 26);
		lblNewLabel_2.setText("基本信息");

		Label lblNewLabel_3 = new Label(com_return, SWT.NONE);
		lblNewLabel_3.setBounds(132, 37, 110, 26);
		lblNewLabel_3.setText("读者编号：");

		text_10 = new Text(com_return, SWT.BORDER);
		text_10.setBounds(248, 34, 263, 21);

		table_1 = new Table(com_return, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(132, 81, 704, 230);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		TableColumn tblclmnNewColumn_2 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("图书名称");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("图书编号");

		TableColumn tableColumn_2 = new TableColumn(table_1, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("图书类别");

		TableColumn tableColumn_3 = new TableColumn(table_1, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("读者姓名");

		TableColumn tableColumn_4 = new TableColumn(table_1, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("读者编号");

		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("借书时间");

		TableColumn tableColumn_6 = new TableColumn(table_1, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("归还时间");

		Menu menu_8 = new Menu(table_1);
		table_1.setMenu(menu_8);

		MenuItem mntmNewItem_11 = new MenuItem(menu_8, SWT.NONE);
		mntmNewItem_11.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String info = text_10.getText().trim();
				int reader_id = Integer.parseInt(info);
				try {
					List<Map<String, Object>> list = operatorDAO.findReturnBookNew(info);
					if (null != list && list.size() > 0) {
						for (Map<String, Object> map : list) {
							text_11.setText(map.get("BORROWDATE").toString());
							text_12.setText(map.get("ISBACK").toString());
							text_13.setText(map.get("ANOD").toString());
							int i = Integer.parseInt(text_12.getText());
							int j = Integer.parseInt(text_13.getText());
							if (j <= i) {
								text_14.setText("");
								text_15.setText("");
							} else {
								text_14.setText(map.get("OVERLAP_DAYS").toString());
								text_15.setText(map.get("OVERDUMONEY").toString());
							}
						}
					} else {
						MessageUtil.promt(shell, "温馨提示", "无该读者借阅信息！");
					}
					return;
				} catch (SQLException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmNewItem_11.setText("图书归还");

		Label lblNewLabel_4 = new Label(com_return, SWT.NONE);
		lblNewLabel_4.setBounds(10, 332, 73, 15);
		lblNewLabel_4.setText("罚款信息");

		Label label_8 = new Label(com_return, SWT.NONE);
		label_8.setText("借书日期：");
		label_8.setBounds(87, 379, 93, 15);

		Label label_9 = new Label(com_return, SWT.NONE);
		label_9.setText("规定天数：");
		label_9.setBounds(85, 420, 95, 15);

		Label label_10 = new Label(com_return, SWT.NONE);
		label_10.setText("实际天数：");
		label_10.setBounds(84, 460, 96, 15);

		Label label_11 = new Label(com_return, SWT.NONE);
		label_11.setText("超出天数：");
		label_11.setBounds(84, 503, 96, 15);

		Label label_12 = new Label(com_return, SWT.NONE);
		label_12.setText("罚款金额：");
		label_12.setBounds(84, 544, 96, 15);

		text_11 = new Text(com_return, SWT.BORDER);
		text_11.setBounds(233, 376, 135, 21);

		text_12 = new Text(com_return, SWT.BORDER);
		text_12.setBounds(233, 417, 135, 21);

		text_13 = new Text(com_return, SWT.BORDER);
		text_13.setBounds(233, 457, 135, 21);

		text_14 = new Text(com_return, SWT.BORDER);
		text_14.setBounds(233, 500, 135, 21);

		text_15 = new Text(com_return, SWT.BORDER);
		text_15.setBounds(233, 541, 135, 21);

		Label label_13 = new Label(com_return, SWT.NONE);
		label_13.setText("当前时间：");
		label_13.setBounds(540, 379, 95, 15);

		Label label_14 = new Label(com_return, SWT.NONE);
		label_14.setText("操  作  员：");
		label_14.setBounds(540, 481, 95, 15);

		Button btnNewButton_1 = new Button(com_return, SWT.NONE);
		// 归还方法
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] item = table_1.getSelection();
				for (TableItem t : item) {
					// 获取到被选中的stuID
					int ISBN = Integer.parseInt(t.getText(1).toString());
					int Reader_id = Integer.parseInt(text_10.getText().toString());
					int operator_id = Integer.parseInt(text_17.getText().toString());

					List<Object> params = new ArrayList<Object>();
					params.add(Reader_id);
					params.add(ISBN);
					params.add(operator_id);

					try {
						boolean type1 = operatorDAO.ReturnUpdateReader(Reader_id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						boolean type2 = operatorDAO.ReturnUpdateBook(ISBN);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						boolean type3 = operatorDAO.ReturnUpdateBorrow(ISBN);
						if (type3) {
							MessageUtil.promt(shell, "温馨提示", "归还成功！");
							text_11.setText("");
							text_12.setText("");
							text_13.setText("");
							text_14.setText("");
							text_15.setText("");
							text_17.setText("");

							String info = text_10.getText().trim();
							try {
								List<Map<String, Object>> list;
								list = operatorDAO.findBorrowBook(info);
								table_1.removeAll();
								if (null != list && list.size() > 0) {
									for (Map<String, Object> map : list) {
										// System.out.println(list.size());
										TableItem tableItem_1 = new TableItem(table_1, SWT.NONE);
										// System.out.println(map.toString());
										tableItem_1.setText(new String[] { map.get("BOOK_NAME").toString(),
												map.get("ISBN").toString(), map.get("TYPE_NAME").toString(),
												map.get("READER_NAME").toString(), map.get("READER_ID").toString(),
												map.get("BORROWDATE").toString(), null });//

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

						} else {
							MessageUtil.promt(shell, "温馨提示", "归还失败！");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setBounds(538, 539, 97, 25);
		btnNewButton_1.setText("图书归还");

		Button btnNewButton_2 = new Button(com_return, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean flag = MessageDialog.openConfirm(shell, "温馨提示", "您确定要退出吗？");
				if (flag) {
					Display.getDefault().close();
					System.exit(0);
				}
			}
		});
		btnNewButton_2.setBounds(734, 539, 102, 25);
		btnNewButton_2.setText("退出");

		CalendarCombo date_return = new CalendarCombo(com_return, SWT.BORDER);
		date_return.setBounds(701, 379, 135, 21);
		// text_16 = new Text(com_return, SWT.BORDER);
		// text_16.setBounds(701, 379, 135, 21);

		text_17 = new Text(com_return, SWT.BORDER);
		text_17.setBounds(701, 478, 135, 21);

		Label label_15 = new Label(com_return, SWT.NONE);
		label_15.setText("系统信息");
		label_15.setBounds(459, 332, 73, 15);

		Button btnNewButton_6 = new Button(com_return, SWT.NONE);

		btnNewButton_6.setBounds(597, 28, 80, 27);
		btnNewButton_6.setText("查询");

		com_select = new Composite(composite, SWT.NONE);
		com_select.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/931294_105434583197_2[1]_副本.jpg"));

		combo = new Combo(com_select, SWT.NONE);
		combo.add("");
		combo.add("书籍名称");
		combo.add("书籍类别");
		combo.add("书籍编号");
		combo.add("书籍作者");
		combo.select(0);
		combo.setBounds(203, 57, 144, 23);

		text_18 = new Text(com_select, SWT.BORDER);
		text_18.setBounds(463, 57, 264, 21);

		Label lblNewLabel_5 = new Label(com_select, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("黑体", 12, SWT.NORMAL));
		lblNewLabel_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		lblNewLabel_5.setBounds(40, 59, 122, 23);
		lblNewLabel_5.setText("请选择查询项目");

		Label label_16 = new Label(com_select, SWT.NONE);
		label_16.setText("查询结果显示");
		label_16.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_16.setFont(SWTResourceManager.getFont("黑体", 12, SWT.NORMAL));
		label_16.setBounds(40, 120, 122, 23);

		table_2 = new Table(com_select, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setBounds(126, 162, 705, 268);
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);

		TableColumn tblclmnNewColumn_4 = new TableColumn(table_2, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("书籍编号");

		TableColumn tableColumn_7 = new TableColumn(table_2, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("书籍名称");

		TableColumn tableColumn_8 = new TableColumn(table_2, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("书籍类别");

		TableColumn tableColumn_9 = new TableColumn(table_2, SWT.NONE);
		tableColumn_9.setWidth(100);
		tableColumn_9.setText("书籍作者");

		TableColumn tableColumn_10 = new TableColumn(table_2, SWT.NONE);
		tableColumn_10.setWidth(100);
		tableColumn_10.setText("书籍价格");

		TableColumn tableColumn_11 = new TableColumn(table_2, SWT.NONE);
		tableColumn_11.setWidth(100);
		tableColumn_11.setText("出版社");

		TableColumn tblclmnNewColumn_5 = new TableColumn(table_2, SWT.NONE);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("当前库存量");

		Button btnNewButton_3 = new Button(com_select, SWT.NONE);

		btnNewButton_3.setBounds(538, 511, 75, 25);
		btnNewButton_3.setText("查询");

		Button btnNewButton_4 = new Button(com_select, SWT.NONE);

		btnNewButton_4.setBounds(652, 511, 75, 25);
		btnNewButton_4.setText("退出");

		com_bookOrder = new Composite(composite, SWT.NONE);
		com_bookOrder.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/2015581704911427_副本_副本.jpg"));

		Label lblNewLabel_7 = new Label(com_bookOrder, SWT.NONE);
		lblNewLabel_7.setBounds(125, 57, 61, 17);
		lblNewLabel_7.setText("图书类别:");

		Label lblNewLabel_8 = new Label(com_bookOrder, SWT.NONE);
		lblNewLabel_8.setBounds(125, 128, 61, 17);
		lblNewLabel_8.setText("图书名称:");

		Label lblNewLabel_9 = new Label(com_bookOrder, SWT.NONE);
		lblNewLabel_9.setBounds(529, 57, 61, 17);
		lblNewLabel_9.setText("图书价格：");

		Label lblNewLabel_10 = new Label(com_bookOrder, SWT.NONE);
		lblNewLabel_10.setBounds(529, 128, 61, 17);
		lblNewLabel_10.setText("订购数量：");

		Label lblNewLabel_11 = new Label(com_bookOrder, SWT.NONE);
		lblNewLabel_11.setBounds(125, 220, 61, 17);
		lblNewLabel_11.setText("订购日期：");

		Label lblNewLabel_12 = new Label(com_bookOrder, SWT.NONE);
		lblNewLabel_12.setBounds(529, 220, 61, 17);
		lblNewLabel_12.setText("操 作 员：");

		text = new Text(com_bookOrder, SWT.BORDER);
		text.setBounds(254, 54, 140, 23);

		text_1 = new Text(com_bookOrder, SWT.BORDER);
		text_1.setBounds(254, 125, 140, 23);

		final CalendarCombo orderDate = new CalendarCombo(com_bookOrder, SWT.BORDER);
		orderDate.setBounds(254, 217, 140, 23);
		// text_2 = new Text(com_bookOrder, SWT.BORDER);
		// text_2.setBounds(254, 217, 140, 23);

		text_3 = new Text(com_bookOrder, SWT.BORDER);
		text_3.setBounds(672, 54, 140, 23);

		text_4 = new Text(com_bookOrder, SWT.BORDER);
		text_4.setBounds(672, 125, 140, 23);

		text_5 = new Text(com_bookOrder, SWT.BORDER);
		text_5.setBounds(672, 217, 140, 23);

		Button btnNewButton_8 = new Button(com_bookOrder, SWT.NONE);

		btnNewButton_8.setBounds(314, 406, 80, 27);
		btnNewButton_8.setText("添加订购");

		Button btnNewButton_9 = new Button(com_bookOrder, SWT.NONE);
		btnNewButton_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean flag = MessageDialog.openConfirm(shell, "温馨提示", "您确定要退出吗？");
				if (flag) {
					Display.getDefault().close();
					System.exit(0);
				}
			}
		});
		btnNewButton_9.setBounds(529, 406, 80, 27);
		btnNewButton_9.setText("退出");

		com_bookReceive = new Composite(composite, SWT.NONE);
		com_bookReceive.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/11_副本.jpg"));

		table_3 = new Table(com_bookReceive, SWT.BORDER | SWT.FULL_SELECTION);
		table_3.setBounds(165, 110, 645, 288);
		table_3.setHeaderVisible(true);
		table_3.setLinesVisible(true);

		TableColumn tblclmnNewColumn_6 = new TableColumn(table_3, SWT.NONE);
		tblclmnNewColumn_6.setWidth(120);
		tblclmnNewColumn_6.setText("图书类别");

		TableColumn tblclmnNewColumn_7 = new TableColumn(table_3, SWT.NONE);
		tblclmnNewColumn_7.setWidth(120);
		tblclmnNewColumn_7.setText("图书名称");

		TableColumn tblclmnNewColumn_8 = new TableColumn(table_3, SWT.NONE);
		tblclmnNewColumn_8.setWidth(100);
		tblclmnNewColumn_8.setText("订购数量");

		TableColumn tblclmnNewColumn_9 = new TableColumn(table_3, SWT.NONE);
		tblclmnNewColumn_9.setWidth(100);
		tblclmnNewColumn_9.setText("图书价格");

		TableColumn tblclmnNewColumn_10 = new TableColumn(table_3, SWT.NONE);
		tblclmnNewColumn_10.setWidth(100);
		tblclmnNewColumn_10.setText("订购日期");

		TableColumn tblclmnNewColumn_11 = new TableColumn(table_3, SWT.NONE);
		tblclmnNewColumn_11.setWidth(100);
		tblclmnNewColumn_11.setText("操作员");

		Menu menu_9 = new Menu(table_3);
		table_3.setMenu(menu_9);

		MenuItem mntmNewItem_12 = new MenuItem(menu_9, SWT.NONE);
		// 验收选择
		mntmNewItem_12.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] item = table_3.getSelection();
				for (TableItem t : item) {
					// 获取到被选中的 ISBN
					String book_name = t.getText(1);
					try {
						List<Map<String, Object>> list = operatorDAO.ReceiveBookNew(book_name);
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {
								text_6.setText(map.get("ORDER_BOOKTYPE").toString());
								text_7.setText(map.get("ORDER_BOOKNAME").toString());
								text_19.setText(map.get("ORDER_DATE").toString());
								text_20.setText(map.get("ORDER_BOOKMONEY").toString());
								text_21.setText(map.get("ORDER_BOOKNUM").toString());
								text_22.setText(map.get("OPERATOR_ID").toString());
								// order_bookType,order_bookName,order_bookNum,order_bookMoney,
								// order_date,operator_id
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
		mntmNewItem_12.setText("验收");

		Label lblNewLabel_13 = new Label(com_bookReceive, SWT.NONE);
		lblNewLabel_13.setBounds(165, 424, 61, 17);
		lblNewLabel_13.setText("图书类别：");

		Label lblNewLabel_14 = new Label(com_bookReceive, SWT.NONE);
		lblNewLabel_14.setBounds(165, 464, 61, 17);
		lblNewLabel_14.setText("图书名称：");

		Label lblNewLabel_15 = new Label(com_bookReceive, SWT.NONE);
		lblNewLabel_15.setBounds(165, 500, 61, 17);
		lblNewLabel_15.setText("订购日期：");

		Label label_17 = new Label(com_bookReceive, SWT.NONE);
		label_17.setText("图书价格：");
		label_17.setBounds(509, 424, 61, 17);

		Label label_18 = new Label(com_bookReceive, SWT.NONE);
		label_18.setText("订购数量：");
		label_18.setBounds(509, 464, 61, 17);

		Label label_19 = new Label(com_bookReceive, SWT.NONE);
		label_19.setText("操 作 员：");
		label_19.setBounds(509, 500, 61, 17);

		text_6 = new Text(com_bookReceive, SWT.BORDER);
		text_6.setBounds(288, 421, 124, 23);

		text_7 = new Text(com_bookReceive, SWT.BORDER);
		text_7.setBounds(288, 461, 124, 23);

		text_19 = new Text(com_bookReceive, SWT.BORDER);
		text_19.setBounds(288, 497, 124, 23);

		text_20 = new Text(com_bookReceive, SWT.BORDER);
		text_20.setBounds(638, 421, 124, 23);

		text_21 = new Text(com_bookReceive, SWT.BORDER);
		text_21.setBounds(638, 458, 124, 23);

		text_22 = new Text(com_bookReceive, SWT.BORDER);
		text_22.setBounds(638, 494, 124, 23);

		Button btnNewButton_10 = new Button(com_bookReceive, SWT.NONE);

		btnNewButton_10.setBounds(332, 545, 80, 27);
		btnNewButton_10.setText("图书验收");

		Button btnNewButton_11 = new Button(com_bookReceive, SWT.NONE);
		btnNewButton_11.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean flag = MessageDialog.openConfirm(shell, "温馨提示", "您确定要退出吗？");
				if (flag) {
					Display.getDefault().close();
					System.exit(0);
				}
			}
		});
		btnNewButton_11.setBounds(509, 545, 80, 27);
		btnNewButton_11.setText("退出");

		com_renew = new Composite(composite, SWT.NONE);
		com_renew.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/11141412_083856958875_2.png"));

		Label lblNewLabel_16 = new Label(com_renew, SWT.NONE);
		lblNewLabel_16.setBounds(192, 76, 61, 17);
		lblNewLabel_16.setText("读者编号：");

		Label label_20 = new Label(com_renew, SWT.NONE);
		label_20.setText("图书名称：");
		label_20.setBounds(192, 429, 61, 17);

		text_23 = new Text(com_renew, SWT.BORDER);
		text_23.setBounds(307, 73, 151, 23);

		text_24 = new Text(com_renew, SWT.BORDER);
		text_24.setBounds(307, 426, 151, 23);

		Button btnNewButton_5 = new Button(com_renew, SWT.NONE);
		// 续借查询
		btnNewButton_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text_23.getText().trim();
				List<Map<String, Object>> list;
				try {
					list = operatorDAO.findBookBorrow(info);

					table_4.removeAll();
					if (null != list && list.size() > 0) {
						for (Map<String, Object> map : list) {
							TableItem tableItem_3 = new TableItem(table_4, SWT.NONE);
							tableItem_3.setText(
									new String[] { map.get("ISBN").toString(), map.get("BORROWDATE").toString(),
											map.get("RETURNDATE").toString(), map.get("READER_ID").toString() });
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
		btnNewButton_5.setBounds(590, 71, 80, 27);
		btnNewButton_5.setText("查询");

		table_4 = new Table(com_renew, SWT.BORDER | SWT.FULL_SELECTION);
		table_4.setLinesVisible(true);
		table_4.setHeaderVisible(true);
		table_4.setBounds(192, 167, 605, 218);

		TableColumn tableColumn_12 = new TableColumn(table_4, SWT.NONE);
		tableColumn_12.setWidth(150);
		tableColumn_12.setText("书籍编号");

		TableColumn tableColumn_13 = new TableColumn(table_4, SWT.NONE);
		tableColumn_13.setWidth(150);
		tableColumn_13.setText("借书日期");

		TableColumn tableColumn_14 = new TableColumn(table_4, SWT.NONE);
		tableColumn_14.setWidth(151);
		tableColumn_14.setText("应还日期");

		TableColumn tableColumn_15 = new TableColumn(table_4, SWT.NONE);
		tableColumn_15.setWidth(150);
		tableColumn_15.setText("读者编号");

		Menu menu_10 = new Menu(table_4);
		table_4.setMenu(menu_10);

		MenuItem mntmNewItem_13 = new MenuItem(menu_10, SWT.NONE);
		// 选择续借
		mntmNewItem_13.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] item = table_4.getSelection();
				for (TableItem t : item) {
					// 获取到被选中的 ISBN
					int ISBN = Integer.parseInt(t.getText(0));
					try {
						List<Map<String, Object>> list = operatorDAO.RenewBookNew(ISBN);
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {
								text_24.setText(map.get("BOOK_NAME").toString());
								text_25.setText(map.get("BORROWDATE").toString());
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
		mntmNewItem_13.setText("续借");

		Button btnNewButton_12 = new Button(com_renew, SWT.NONE);
		// 确认续借操作
		btnNewButton_12.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String book_name = text_24.getText();

				boolean type5;
				try {
					type5 = operatorDAO.RenewBorrowUpdate(book_name);

					if (type5) {
						MessageUtil.promt(shell, "温馨提示", "续借成功！");
						text_24.setText("");
						text_25.setText("");
						text_26.setText("");
					} else {
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
		btnNewButton_12.setBounds(526, 473, 80, 27);
		btnNewButton_12.setText("确定续借");

		Button button_2 = new Button(com_renew, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean flag = MessageDialog.openConfirm(shell, "温馨提示", "您确定要退出吗？");
				if (flag) {
					Display.getDefault().close();
					System.exit(0);
				}
			}
		});
		button_2.setText("退出");
		button_2.setBounds(717, 473, 80, 27);

		Label label_21 = new Label(com_renew, SWT.NONE);
		label_21.setText("借阅日期：");
		label_21.setBounds(192, 478, 61, 17);

		Label label_22 = new Label(com_renew, SWT.NONE);
		label_22.setText("管 理 员：");
		label_22.setBounds(526, 429, 61, 17);

		text_25 = new Text(com_renew, SWT.BORDER);
		text_25.setBounds(307, 475, 151, 23);

		text_26 = new Text(com_renew, SWT.BORDER);
		text_26.setBounds(646, 426, 151, 23);

		com_mimaxg = new Composite(composite, SWT.NONE);
		com_mimaxg.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/密码修改1.jpg"));
		com_mimaxg.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));

		Label label_23 = new Label(com_mimaxg, SWT.NONE);
		label_23.setText("密码修改");
		label_23.setFont(SWTResourceManager.getFont("楷体", 25, SWT.BOLD));
		label_23.setBounds(470, 80, 154, 33);

		Label label_24 = new Label(com_mimaxg, SWT.NONE);
		label_24.setText("请输入原密码：");
		label_24.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD | SWT.ITALIC));
		label_24.setBounds(378, 151, 130, 24);

		text_mimaxg1 = new Text(com_mimaxg, SWT.BORDER);
		text_mimaxg1.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		text_mimaxg1.setBounds(565, 148, 129, 23);

		Label label_25 = new Label(com_mimaxg, SWT.NONE);
		label_25.setText("请输入新密码：");
		label_25.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD | SWT.ITALIC));
		label_25.setBounds(378, 207, 130, 24);

		text_mimaxg2 = new Text(com_mimaxg, SWT.BORDER | SWT.PASSWORD);
		text_mimaxg2.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		text_mimaxg2.setBounds(565, 204, 130, 23);

		Label label_26 = new Label(com_mimaxg, SWT.NONE);
		label_26.setText("确认新密码：");
		label_26.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD | SWT.ITALIC));
		label_26.setBounds(378, 268, 119, 24);

		text_mimaxg3 = new Text(com_mimaxg, SWT.BORDER | SWT.PASSWORD);
		text_mimaxg3.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		text_mimaxg3.setBounds(565, 265, 129, 23);

		Label Label_mimasure = new Label(com_mimaxg, SWT.NONE);
		Label_mimasure.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		Label_mimasure.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD | SWT.ITALIC));
		Label_mimasure.setBounds(735, 268, 154, 24);

		Button Button_mimaxgfinish = new Button(com_mimaxg, SWT.NONE);
		Button_mimaxgfinish.addSelectionListener(new SelectionAdapter() {
			@Override
			// 修改密码
			public void widgetSelected(SelectionEvent e) {
				// 获取旧密码 新密码1 新密码2
				String oldpwd = text_mimaxg1.getText();
				String newpwd = text_mimaxg2.getText();
				String newpwd1 = text_mimaxg3.getText();

				List<Object> params = new ArrayList<Object>();
				params.add(newpwd1);
				params.add(PwdCommon.username);
				if (newpwd1.equals(newpwd)) {
					try {
						boolean flag = operatorDAO.UpdatePwdOperator(params);
						if (flag) {
							MessageUtil.promt(shell, "提示", "密码修改成功");
							shell.dispose();
							// t.stop();
							Login logina = new Login();
							logina.open();
						} else {
							MessageUtil.promt(shell, "提示", "密码修改失败");
						}

						// 清空页面数据
						// text_mimaxg1.setText("");
						// text_mimaxg2.setText("");
						// text_mimaxg3.setText("");

					} catch (SQLException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					MessageUtil.promt(shell, "提示", "新密码需输入一致");
				}

			}
		});
		Button_mimaxgfinish.setText("确认");
		Button_mimaxgfinish.setFont(SWTResourceManager.getFont("宋体", 14, SWT.BOLD));
		Button_mimaxgfinish.setBounds(367, 384, 80, 27);

		Button Button_mimaxgreset = new Button(com_mimaxg, SWT.NONE);
		// 重置
		Button_mimaxgreset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 清空页面数据
				text_mimaxg1.setText("");
				text_mimaxg2.setText("");
				text_mimaxg3.setText("");
			}
		});
		Button_mimaxgreset.setText("重置");
		Button_mimaxgreset.setFont(SWTResourceManager.getFont("宋体", 14, SWT.BOLD));
		Button_mimaxgreset.setBounds(624, 384, 80, 27);

		com_readerselect = new Composite(composite, SWT.NONE);// 读者查询
		// 添加背景图
		com_readerselect.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/读者注册1.jpg"));
		com_readerselect.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_MAGENTA));
		com_readerselect.setBounds(0, 0, 984, 580);

		Label label_27 = new Label(com_readerselect, SWT.NONE);
		label_27.setText("查询条件：");
		label_27.setFont(SWTResourceManager.getFont("新宋体", 14, SWT.BOLD));
		label_27.setBounds(114, 70, 88, 25);

		final Combo combo_select = new Combo(com_readerselect, SWT.NONE);
		combo_select.setItems(new String[] { "读者编号", "读者姓名" });
		combo_select.setBounds(243, 70, 88, 25);

		text_readerselect = new Text(com_readerselect, SWT.BORDER);
		text_readerselect.setBounds(406, 70, 140, 23);

		Button Button_readerselect = new Button(com_readerselect, SWT.NONE);
		Button_readerselect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String dztj1 = text_readerselect.getText().trim();
				if (null == dztj1 || "".equals(dztj1)) {
					try {
						java.util.List<Map<String, Object>> list = readerDao.findAllReader();
						table_readerselect.removeAll();
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {

								TableItem tableItem1 = new TableItem(table_readerselect, SWT.NONE);

								tableItem1.setText(new String[] { map.get("READER_NAME").toString(),
										map.get("READER_ID").toString(), map.get("READER_SEX").toString(),
										map.get("READER_BRI").toString(), map.get("MAXNUM").toString(),
										map.get("READER_TEL").toString() });
							}
						} else {
							MessageUtil.promt(shell, "温馨提示", "无书籍信息");
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				}

				String dztj = combo_select.getText();
				if ("读者编号".equals(dztj.trim())) {
					int reader_id = Integer.parseInt(dztj1);
					// 根据读者编号查询
					try {

						List<Map<String, Object>> list = readerDao.showIdReader(reader_id);
						table_readerselect.removeAll();// 清楚表格数据
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {

								TableItem tableItem1 = new TableItem(table_readerselect, SWT.NONE);

								tableItem1.setText(new String[] { map.get("READER_NAME").toString(),
										map.get("READER_ID").toString(), map.get("READER_SEX").toString(),
										map.get("READER_RD").toString(), map.get("MAXNUM").toString(),
										map.get("READER_TEL").toString() });
							}
						} else {
							MessageUtil.promt(shell, "温馨提示", "查无此读者");

						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					} catch (IOException e1) {
						//
						e1.printStackTrace();
					}

				} else if ("读者姓名".equals(dztj.trim())) {
					// 根据读者姓名查询
					// String reader_name=text_readerselect.getText().trim();
					try {

						List<Map<String, Object>> list = readerDao.showNameReader(dztj1);
						table_readerselect.removeAll();// 清楚表格数据
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {

								TableItem tableItem1 = new TableItem(table_readerselect, SWT.NONE);

								tableItem1.setText(new String[] { map.get("READER_NAME").toString(),
										map.get("READER_ID").toString(), map.get("READER_SEX").toString(),
										map.get("READER_RD").toString(), map.get("MAXNUM").toString(),
										map.get("READER_TEL").toString() });
							}
						} else {
							MessageUtil.promt(shell, "温馨提示", "查无此读者");

						}

					} catch (SQLException e1) {
						e1.printStackTrace();
						// LogUtil.logger.info(e1.getMessage()+ new Date());
					} catch (IOException e1) {
						//
						e1.printStackTrace();
					}

				}
			}
		});
		Button_readerselect.setText("查询");
		Button_readerselect.setFont(SWTResourceManager.getFont("黑体", 12, SWT.NORMAL));
		Button_readerselect.setBounds(640, 70, 61, 27);

		table_readerselect = new Table(com_readerselect, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		// formToolkit.adapt(table_readerselect);
		// formToolkit.paintBordersFor(table_readerselect);
		table_readerselect.setLinesVisible(true);
		table_readerselect.setHeaderVisible(true);
		table_readerselect.setBounds(149, 148, 671, 345);

		TableColumn tableColumn_16 = new TableColumn(table_readerselect, SWT.NONE);
		tableColumn_16.setWidth(97);
		tableColumn_16.setText("姓名");

		TableColumn tableColumn_17 = new TableColumn(table_readerselect, SWT.NONE);
		tableColumn_17.setWidth(109);
		tableColumn_17.setText("读者编号");

		TableColumn tableColumn_18 = new TableColumn(table_readerselect, SWT.NONE);
		tableColumn_18.setWidth(89);
		tableColumn_18.setText("性别");

		TableColumn tableColumn_19 = new TableColumn(table_readerselect, SWT.NONE);
		tableColumn_19.setWidth(128);
		tableColumn_19.setText("注册日期");

		TableColumn tableColumn_21 = new TableColumn(table_readerselect, SWT.NONE);
		tableColumn_21.setWidth(120);
		tableColumn_21.setText("最大借阅量");

		TableColumn tableColumn_22 = new TableColumn(table_readerselect, SWT.NONE);
		tableColumn_22.setWidth(123);
		tableColumn_22.setText("电话");

		Menu menu_11 = new Menu(table_readerselect);
		table_readerselect.setMenu(menu_11);

		// //加载页面时执行查询读者信息显示语句
		// //TODO
		// try {
		// //根据页面显示要求查看读者信息
		// List<Map<String,Object>> list =readerDao.showAllReader();
		// //System.out.println(list.toString());//打印数据
		//
		// for(Map<String, Object> map:list){
		//
		// TableItem tableItem1 = new TableItem(table_readerselect, SWT.NONE);
		//
		// tableItem1.setText(new String[]
		// {map.get("READER_NAME").toString(),map.get("READER_ID").toString(),map.get("READER_SEX").toString(),map.get("READER_RD").toString(),map.get("VIP").toString(),map.get("MAXNUM").toString(),map.get("READER_TEL").toString()});
		// }
		//
		// } catch (SQLException e2) {
		//
		// e2.printStackTrace();
		// } catch (IOException e2) {
		//
		// e2.printStackTrace();
		// }

		MenuItem menuItem_5 = new MenuItem(menu_11, SWT.NONE);
		menuItem_5.setText("删除");

		com_readerenroll = new Composite(composite, SWT.NONE);// 读者注册
		// 添加背景图
		com_readerenroll.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/读者注册.jpg"));
		com_readerenroll.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		com_readerenroll.setBounds(0, 0, 984, 580);

		/*
		 * Label lblNewLabel_5 = new Label(com_select, SWT.NONE);
		 * lblNewLabel_5.setFont(SWTResourceManager.getFont("黑体", 12,
		 * SWT.NORMAL));
		 * lblNewLabel_5.setForeground(SWTResourceManager.getColor(SWT.
		 * COLOR_CYAN)); lblNewLabel_5.setBounds(40, 10, 122, 23);
		 * lblNewLabel_5.setText("请选择查询项目");
		 */
		Label label_28 = new Label(com_readerenroll, SWT.NONE);
		// label_28.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_28.setText("姓  名：");
		label_28.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		label_28.setBounds(71, 50, 61, 17);
		// formToolkit.adapt(label_28, true, true);

		Label label_29 = new Label(com_readerenroll, SWT.NONE);
		label_29.setText("性  别：");
		label_29.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		label_29.setBounds(71, 106, 61, 17);
		// formToolkit.adapt(label_29, true, true);

		Label label_30 = new Label(com_readerenroll, SWT.NONE);
		label_30.setText("密  码：");
		label_30.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		label_30.setBounds(71, 157, 61, 17);
		// formToolkit.adapt(label_30, true, true);

		Label label_31 = new Label(com_readerenroll, SWT.NONE);
		label_31.setText("身份证：");
		label_31.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		label_31.setBounds(71, 264, 77, 17);
		// formToolkit.adapt(label_31, true, true);

		Label label_32 = new Label(com_readerenroll, SWT.NONE);
		label_32.setText("电  话：");
		label_32.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		label_32.setBounds(71, 318, 77, 17);
		// formToolkit.adapt(label_32, true, true);

		Label label_33 = new Label(com_readerenroll, SWT.NONE);
		label_33.setText("出生日期：");
		label_33.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		label_33.setBounds(68, 380, 80, 17);
		// formToolkit.adapt(label_33, true, true);

		Label label_34 = new Label(com_readerenroll, SWT.NONE);
		label_34.setText("可借数量：");
		label_34.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		label_34.setBounds(68, 437, 80, 17);
		// formToolkit.adapt(label_34, true, true);

		text_name = new Text(com_readerenroll, SWT.BORDER);
		text_name.setBounds(202, 48, 133, 23);
		// formToolkit.adapt(text_name, true, true);

		final Button RadioButton_sexnan = new Button(com_readerenroll, SWT.RADIO);
		RadioButton_sexnan.setText("男");
		RadioButton_sexnan.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		RadioButton_sexnan.setBounds(202, 106, 42, 17);
		// formToolkit.adapt(RadioButton_sexnan, true, true);

		final Button RadioButton_sexnv = new Button(com_readerenroll, SWT.RADIO);
		RadioButton_sexnv.setText("女");
		RadioButton_sexnv.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		RadioButton_sexnv.setBounds(284, 106, 51, 17);
		// formToolkit.adapt(RadioButton_sexnv, true, true);

		text_pwd = new Text(com_readerenroll, SWT.BORDER | SWT.PASSWORD);
		text_pwd.setBounds(202, 155, 133, 23);
		// formToolkit.adapt(text_pwd, true, true);

		text_idcard = new Text(com_readerenroll, SWT.BORDER);
		text_idcard.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String IDCard =text_idcard.getText().toString();
				if(IDCard.matches("\\d{18,20}")){
					
					label_ID.setText("");
				}else{
					label_ID.setText("*身份证号为18位整数!");
				}			
			}
		});

		text_idcard.setBounds(202, 262, 133, 23);
		// formToolkit.adapt(text_idcard, true, true);

		text_phone = new Text(com_readerenroll, SWT.BORDER);
		text_phone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String Phone = text_phone.getText();
				if(Phone.matches("\\d{11,12}")){				
					label_TEL.setText("");
				}else{
					label_TEL.setText("*电话号码为11位整数！");
				}			
			}
		});

		text_phone.setBounds(202, 316, 133, 23);
		// formToolkit.adapt(text_phone, true, true);

		final CalendarCombo cc = new CalendarCombo(com_readerenroll, SWT.NONE);
		cc.setBounds(202, 374, 133, 23);
		// formToolkit.adapt(cc);
		// formToolkit.paintBordersFor(cc);

		text_num = new Text(com_readerenroll, SWT.BORDER);
		text_num.setBounds(202, 435, 132, 23);
		// formToolkit.adapt(text_num, true, true);

		final Label Labela_readerimg = new Label(com_readerenroll, SWT.NONE);
		Labela_readerimg.setBounds(631, 51, 236, 216);
		// formToolkit.adapt(Labela_readerimg, true, true);

		final Button Button_addimges = new Button(com_readerenroll, SWT.NONE);
		Button_addimges.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shell, SWT.SINGLE);
				fd.setText("请选择您要上传的照片");
				fd.setFilterPath("SystemRoot");
				fd.setFilterExtensions(new String[] { "*.png", "*.jpg", "*.*" });
				String selected = fd.open();
				if (null == selected) {
					return;
				}
				File file = new File(selected);
				InputStream in;
				try {
					in = new FileInputStream(file);
					Image image = new Image(Display.getDefault(), in);
					Labela_readerimg.setImage(image);
					Button_addimges.setText(selected);// 将图片的路径显示在按钮上

				} catch (FileNotFoundException e1) {
					MessageUtil.promt(shell, "出错了", e1.getMessage());
				}
			}
		});
		Button_addimges.setText("上传照片");
		Button_addimges.setFont(SWTResourceManager.getFont("楷体", 10, SWT.BOLD));
		Button_addimges.setBounds(665, 299, 148, 27);
		// formToolkit.adapt(Button_addimges, true, true);

		Button btn_finish = new Button(com_readerenroll, SWT.NONE);
		btn_finish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String path = Button_addimges.getText();
				File file = new File(path);
				// 获取姓名 性别 密码 出生日期 身份证号码 电话 VIP账号

				String usernamea = text_name.getText();
				String pwda = text_pwd.getText();
				String sex = "";
				if (RadioButton_sexnan.getSelection()) {
					sex = RadioButton_sexnan.getText();

				} else if (RadioButton_sexnv.getSelection()) {
					sex = RadioButton_sexnv.getText();

				}
				String bdate = cc.getDateAsString();
				String id = text_idcard.getText();
				String phone = text_phone.getText();
				String max = text_num.getText();
				List<Object> params = new ArrayList<Object>();
				params.add(usernamea);
				params.add(sex);
				params.add(pwda);
				params.add(bdate);
				params.add(id);
				params.add(phone);
				params.add(file);
				params.add(max);

				// params.add(address);

				try {
					boolean flag = readerDao.registerReader(params);
					if (flag) {
						MessageUtil.promt(shell, "温馨提示", "注册成功");
						// 清楚页面上数据
						text_name.setText("");
						text_pwd.setText("");
						text_Pwd.setText("");
						text_idcard.setText("");
						text_phone.setText("");
						text_num.setText("");
						Button_addimges.setText("");// 上传照片
						Labela_readerimg.setImage(SWTResourceManager.getImage(Main.class, "/images/读者头像.jpg"));
					} else {
						MessageUtil.promt(shell, "温馨提示", "注册失败");
					}

				} catch (SQLException e1) {
					MessageUtil.promt(shell, "出错了", e1.getMessage());
				} catch (IOException e1) {
					MessageUtil.promt(shell, "出错了", e1.getMessage());
				}
			}

		});
		btn_finish.setBounds(585, 420, 97, 34);
		// formToolkit.adapt(btn_finish, true, true);
		btn_finish.setText("完成注册");

		Button Button_exit = new Button(com_readerenroll, SWT.NONE);
		Button_exit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean flag = MessageDialog.openConfirm(shell, "温馨提示", "您确定要取消注册吗？");
				if(flag){
					stackLayout.topControl = composite;
					composite.layout();
				}
				
			}
		});
		Button_exit.setText("取消注册");
		Button_exit.setBounds(770, 420, 97, 34);
		
		Label label_56 = new Label(com_readerenroll, SWT.NONE);
		label_56.setText("确认密码：");
		label_56.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		label_56.setBounds(71, 210, 77, 17);
		
		text_Pwd = new Text(com_readerenroll, SWT.BORDER | SWT.PASSWORD);
		text_Pwd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String  pwda=text_pwd.getText();
			    String pwdb=text_Pwd.getText();
				if(pwda.equals(pwdb)){
					label_Pwd.setText("");
				}else{
					label_Pwd.setText("* 密码不一致！请重新输入！");
				}
			}
		});

		text_Pwd.setBounds(202, 208, 133, 23);
		
		label_Pwd = new Label(com_readerenroll, SWT.NONE);
		label_Pwd.setBounds(379, 210, 192, 23);
		
		label_ID = new Label(com_readerenroll, SWT.NONE);
		label_ID.setBounds(368, 264, 160, 17);
		
		label_TEL = new Label(com_readerenroll, SWT.NONE);
		label_TEL.setBounds(368, 318, 160, 17);
		// formToolkit.adapt(Button_exit, true, true);

		com_readeralter = new Composite(composite, SWT.NONE);// 读者修改
		com_readeralter.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/读者修改.jpg"));
		// 添加背景图

		com_readeralter.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		com_readeralter.setBounds(0, 0, 984, 580);

		Label label_35 = new Label(com_readeralter, SWT.NONE);
		label_35.setText("查询条件：");
		label_35.setFont(SWTResourceManager.getFont("新宋体", 12, SWT.BOLD));
		label_35.setBounds(227, 54, 73, 23);
		// formToolkit.adapt(label_35, true, true);

		final Combo combo_update = new Combo(com_readeralter, SWT.NONE);
		combo_update.setItems(new String[] { "读者姓名", "读者编号" });
		combo_update.setBounds(338, 52, 73, 18);
		// formToolkit.adapt(combo_update);
		// formToolkit.paintBordersFor(combo_update);

		text_rselect = new Text(com_readeralter, SWT.BORDER);
		text_rselect.setBounds(463, 52, 121, 23);
		// formToolkit.adapt(text_rselect, true, true);

		Button NewButton_rselet1 = new Button(com_readeralter, SWT.NONE);
		NewButton_rselet1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String dztj2 = text_rselect.getText().trim();
				if (null == dztj2 || "".equals(dztj2)) {
					MessageUtil.promt(shell, "温馨提示", "请输入查询条件");
					return;
				}

				String dztj3 = combo_update.getText();
				if ("读者编号".equals(dztj3.trim())) {
					int reader_ida = Integer.parseInt(dztj2);
					// 根据读者编号查询
					try {

						List<Map<String, Object>> list = readerDao.showIdReadera(reader_ida);
						table_update.removeAll();// 清楚表格数据
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {

								TableItem tableItem = new TableItem(table_update, SWT.NONE);

								tableItem.setText(new String[] { map.get("READER_ID").toString(),
										map.get("READER_NAME").toString(), map.get("READER_SEX").toString(),
										map.get("READER_PWD").toString(), map.get("READER_BRI").toString(),
										map.get("MAXNUM").toString(), map.get("READER_TEL").toString(),
										map.get("IDENTITYCARD").toString() });
							}
						} else {
							MessageUtil.promt(shell, "温馨提示", "查无此读者");

						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					} catch (IOException e1) {
						//
						e1.printStackTrace();
					}

				} else if ("读者姓名".equals(dztj3.trim())) {
					// 根据读者姓名查询
					// String reader_name=text_readerselect.getText().trim();
					try {

						List<Map<String, Object>> list = readerDao.showNameReadera(dztj2);
						table_update.removeAll();// 清楚表格数据
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {

								TableItem tableItem = new TableItem(table_update, SWT.NONE);

								tableItem.setText(new String[] { map.get("READER_ID").toString(),
										map.get("READER_NAME").toString(), map.get("READER_SEX").toString(),
										map.get("READER_PWD").toString(), map.get("READER_BRI").toString(),
										map.get("MAXNUM").toString(), map.get("READER_TEL").toString(),
										map.get("IDENTITYCARD").toString() });
							}
						} else {
							MessageUtil.promt(shell, "温馨提示", "查无此读者");

						}

					} catch (SQLException e1) {
						e1.printStackTrace();
						// LogUtil.logger.info(e1.getMessage()+ new Date());
					} catch (IOException e1) {
						//
						e1.printStackTrace();
					}

				}
			}
		});
		NewButton_rselet1.setText("查询");
		NewButton_rselet1.setFont(SWTResourceManager.getFont("新宋体", 11, SWT.BOLD));
		NewButton_rselet1.setBounds(681, 50, 80, 25);
		// formToolkit.adapt(NewButton_rselet1, true, true);

		table_update = new Table(com_readeralter, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		table_update.setLinesVisible(true);
		table_update.setHeaderVisible(true);
		table_update.setBounds(130, 93, 752, 228);
		// formToolkit.adapt(table_update);
		// formToolkit.paintBordersFor(table_update);

		TableColumn tableColumn_23 = new TableColumn(table_update, SWT.NONE);
		tableColumn_23.setWidth(87);
		tableColumn_23.setText("读者编号");

		TableColumn tableColumn_24 = new TableColumn(table_update, SWT.NONE);
		tableColumn_24.setWidth(67);
		tableColumn_24.setText("读者姓名");

		TableColumn tableColumn_25 = new TableColumn(table_update, SWT.NONE);
		tableColumn_25.setWidth(64);
		tableColumn_25.setText("读者性别");

		TableColumn tableColumn_26 = new TableColumn(table_update, SWT.NONE);
		tableColumn_26.setWidth(68);
		tableColumn_26.setText("用户密码");

		TableColumn tableColumn_27 = new TableColumn(table_update, SWT.NONE);
		tableColumn_27.setWidth(100);
		tableColumn_27.setText("出生日期");

		TableColumn tableColumn_28 = new TableColumn(table_update, SWT.NONE);
		tableColumn_28.setWidth(79);
		tableColumn_28.setText("最大借阅量");

		TableColumn tableColumn_30 = new TableColumn(table_update, SWT.NONE);
		tableColumn_30.setWidth(124);
		tableColumn_30.setText("电话");

		TableColumn tableColumn_31 = new TableColumn(table_update, SWT.NONE);
		tableColumn_31.setWidth(157);
		tableColumn_31.setText("身份证号");

		Menu menu_right = new Menu(table_update);
		table_update.setMenu(menu_right);

		MenuItem menuItem_7 = new MenuItem(menu_right, SWT.NONE);
		menuItem_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] item1 = table_update.getSelection();
				for (TableItem t1 : item1) {
					// 获取被选中的学生ID
					int readeridb = Integer.parseInt(t1.getText(0));
					List<Object> params = new ArrayList<Object>();
					params.add(readeridb);

					try {
						boolean flag = readerDao.deleteReader(params);
						if (flag) {
							MessageUtil.promt(shell, "温馨提示", "删除成功");

						} else {
							MessageUtil.promt(shell, "温馨提示", "删除失败");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		menuItem_7.setText("删除");

		MenuItem menuItem_8 = new MenuItem(menu_right, SWT.NONE);
		menuItem_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] item = table_update.getSelection();
				for (TableItem t : item) {
					// 获取被选中的读者ID
					int reader_id = Integer.parseInt(t.getText(0));
					try {
						List<Map<String, Object>> list = readerDao.showIdReadera(reader_id);
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {
								text_rreaderid.setText(map.get("READER_ID").toString());
								text_Name.setText(map.get("READER_NAME").toString());
								text_rpwd1.setText(map.get("READER_PWD").toString());
								// text_rborndate.setText(map.get("READER_BRI").toString());
								text_rmax.setText(map.get("MAXNUM").toString());
								text_rphone1.setText(map.get("READER_TEL").toString());
								text_rsfz1.setText(map.get("IDENTITYCARD").toString());

							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		menuItem_8.setText("修改");

		Label label_36 = new Label(com_readeralter, SWT.NONE);
		label_36.setText("读者编号：");
		label_36.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_36.setBounds(182, 356, 61, 17);
		// formToolkit.adapt(label_36, true, true);

		Label label_37 = new Label(com_readeralter, SWT.NONE);
		label_37.setText("读者姓名：");
		label_37.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_37.setBounds(182, 410, 61, 17);
		// formToolkit.adapt(label_37, true, true);

		Label label_38 = new Label(com_readeralter, SWT.NONE);
		label_38.setText("用户密码：");
		label_38.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_38.setBounds(182, 463, 61, 17);
		// formToolkit.adapt(label_38, true, true);

		Label label_40 = new Label(com_readeralter, SWT.NONE);
		label_40.setText("最大借阅量：");
		label_40.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_40.setBounds(511, 356, 73, 17);
		// formToolkit.adapt(label_40, true, true);

		Label label_41 = new Label(com_readeralter, SWT.NONE);
		label_41.setText("电     话：");
		label_41.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_41.setBounds(511, 410, 73, 17);
		// formToolkit.adapt(label_41, true, true);

		Label label_42 = new Label(com_readeralter, SWT.NONE);
		label_42.setText("身 份 证：");
		label_42.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_42.setBounds(511, 463, 73, 17);
		// formToolkit.adapt(label_42, true, true);

		Button Button_rupdate = new Button(com_readeralter, SWT.NONE);
		Button_rupdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 获取读者ID 姓名 密码 出生日期 最大借阅量 电话 身份证号
				String readerid = text_rreaderid.getText();// 读者ID
				String readername = text_Name.getText();// 读者姓名
				String readerpwd = text_rpwd1.getText();// 读者密码
				// String readerbdate=text_bb.getDateAsString();//出生日期
				String readermax = text_rmax.getText();// 最大借阅量
				String readerphone = text_rphone1.getText();// 读者电话
				String readersfz = text_rsfz1.getText();
				List<Object> params = new ArrayList<Object>();
				params.add(readerid);
				params.add(readername);
				params.add(readerpwd);
				// params.add(readerbdate);
				params.add(readermax);
				params.add(readerphone);
				params.add(readersfz);
				params.add(readerid);

				try {
					boolean flag = readerDao.alterReader(params);
					if (flag) {
						MessageUtil.promt(shell, "温馨提示", "修改成功");
						// 清楚页面上数据
						text_rreaderid.setText("");
						text_Name.setText("");
						text_rpwd1.setText("");
						// text_rborndate.setText("");
						text_rmax.setText("");
						text_rphone1.setText("");
						text_rsfz1.setText("");

						String dztj2 = text_rselect.getText().trim();
						if (null == dztj2 || "".equals(dztj2)) {
							MessageUtil.promt(shell, "温馨提示", "请输入查询条件");
							return;
						}

						String dztj3 = combo_update.getText();
						if ("读者编号".equals(dztj3.trim())) {
							int reader_ida = Integer.parseInt(dztj2);
							// 根据读者编号查询
							try {

								List<Map<String, Object>> list = readerDao.showIdReadera(reader_ida);
								table_update.removeAll();// 清楚表格数据
								if (null != list && list.size() > 0) {
									for (Map<String, Object> map : list) {

										TableItem tableItem = new TableItem(table_update, SWT.NONE);

										tableItem
												.setText(
														new String[] { map.get("READER_ID").toString(),
																map.get("READER_NAME").toString(), map.get("READER_SEX")
																		.toString(),
																map.get("READER_PWD").toString(),
																map.get("READER_BRI").toString(),
																map.get("MAXNUM").toString(), null,
																map.get("READER_TEL").toString(),
																map.get("IDENTITYCARD").toString() });
									}
								} else {
									MessageUtil.promt(shell, "温馨提示", "查无此读者");

								}
							} catch (SQLException e1) {

								e1.printStackTrace();
							} catch (IOException e1) {
								//
								e1.printStackTrace();
							}

						} else if ("读者姓名".equals(dztj3.trim())) {
							// 根据读者姓名查询
							// String
							// reader_name=text_readerselect.getText().trim();
							try {

								List<Map<String, Object>> list = readerDao.showNameReadera(dztj2);
								table_update.removeAll();// 清楚表格数据
								if (null != list && list.size() > 0) {
									for (Map<String, Object> map : list) {

										TableItem tableItem = new TableItem(table_update, SWT.NONE);

										tableItem
												.setText(
														new String[] { map.get("READER_ID").toString(),
																map.get("READER_NAME").toString(), map.get("READER_SEX")
																		.toString(),
																map.get("READER_PWD").toString(),
																map.get("READER_BRI").toString(),
																map.get("MAXNUM").toString(), null,
																map.get("READER_TEL").toString(),
																map.get("IDENTITYCARD").toString() });
									}
								} else {
									MessageUtil.promt(shell, "温馨提示", "查无此读者");

								}

							} catch (SQLException e1) {
								e1.printStackTrace();
								// LogUtil.logger.info(e1.getMessage()+ new
								// Date());
							} catch (IOException e1) {
								//
								e1.printStackTrace();
							}

						}

					} else {
						MessageUtil.promt(shell, "温馨提示", "修改失败");
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
		Button_rupdate.setText("修改");
		Button_rupdate.setFont(SWTResourceManager.getFont("新宋体", 13, SWT.BOLD));
		Button_rupdate.setBounds(306, 520, 80, 27);
		// formToolkit.adapt(Button_rupdate, true, true);

		Button Button_rresert = new Button(com_readeralter, SWT.NONE);
		Button_rresert.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 清空页面数据
				// text_rreaderid.setText("");
				text_Name.setText("");
				text_rpwd1.setText("");
				// text_rborndate.setText("");
				text_rmax.setText("");
				text_rphone1.setText("");
				text_rsfz1.setText("");
			}
		});
		Button_rresert.setText("重置");
		Button_rresert.setFont(SWTResourceManager.getFont("新宋体", 13, SWT.BOLD));
		Button_rresert.setBounds(504, 520, 80, 27);
		// formToolkit.adapt(Button_rresert, true, true);

		text_rreaderid = new Text(com_readeralter, SWT.BORDER);
		text_rreaderid.setBounds(306, 353, 80, 23);
		// formToolkit.adapt(text_rreaderid, true, true);

		text_Name = new Text(com_readeralter, SWT.BORDER);
		text_Name.setBounds(306, 404, 80, 23);
		// formToolkit.adapt(text_Name, true, true);

		text_rpwd1 = new Text(com_readeralter, SWT.BORDER);
		text_rpwd1.setBounds(306, 463, 80, 23);
		// formToolkit.adapt(text_rpwd1, true, true);

		text_rmax = new Text(com_readeralter, SWT.BORDER);
		text_rmax.setBounds(655, 356, 138, 23);
		// formToolkit.adapt(text_rmax, true, true);

		text_rphone1 = new Text(com_readeralter, SWT.BORDER);
		text_rphone1.setBounds(655, 410, 138, 23);
		// formToolkit.adapt(text_rphone1, true, true);

		text_rsfz1 = new Text(com_readeralter, SWT.BORDER);
		text_rsfz1.setBounds(655, 463, 138, 23);

		com_booksort = new Composite(composite, SWT.NONE);
		com_booksort.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/读者注册1.jpg"));

		Label label_39 = new Label(com_booksort, SWT.NONE);
		label_39.setText("图书类别添加");
		label_39.setFont(SWTResourceManager.getFont("新宋体", 18, SWT.BOLD | SWT.ITALIC));
		label_39.setBounds(423, 143, 159, 32);

		Label lblId = new Label(com_booksort, SWT.NONE);
		lblId.setText("类 别 ID:");
		lblId.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		lblId.setBounds(360, 211, 111, 21);

		Label label_44 = new Label(com_booksort, SWT.NONE);
		label_44.setText("类别名称:");
		label_44.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		label_44.setBounds(360, 275, 111, 21);

		Label label_45 = new Label(com_booksort, SWT.NONE);
		label_45.setText("可借天数:");
		label_45.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		label_45.setBounds(360, 343, 111, 21);

		Label label_46 = new Label(com_booksort, SWT.NONE);
		label_46.setText("逾期罚款:");
		label_46.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		label_46.setBounds(360, 410, 111, 21);

		text_sortID = new Text(com_booksort, SWT.BORDER);
		text_sortID.setBounds(505, 213, 143, 23);

		text_sortname = new Text(com_booksort, SWT.BORDER);
		text_sortname.setBounds(505, 277, 143, 23);

		text_dateborrow = new Text(com_booksort, SWT.BORDER);
		text_dateborrow.setBounds(505, 345, 143, 23);

		text_overduemoney = new Text(com_booksort, SWT.BORDER);
		text_overduemoney.setBounds(505, 412, 143, 23);

		Button button_3 = new Button(com_booksort, SWT.NONE);
		// 图书类别添加
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 获取 类别ID 类别名称 可借天数 逾期罚款
				String sortid = text_sortID.getText();// 类别ID
				String sortname = text_sortname.getText();// 类别姓名
				String dateborrow = text_dateborrow.getText();// 可借天数
				String overduemoney = text_overduemoney.getText();// 逾期罚款
				List<Object> params = new ArrayList<Object>();
				params.add(sortid);
				params.add(sortname);
				params.add(dateborrow);
				params.add(overduemoney);

				try {
					boolean flag = bookInfoDAO.registerBook(params);
					if (flag) {
						MessageUtil.promt(shell, "温馨提示", "录入成功");
						// 清楚页面上数据
						text_sortID.setText("");
						text_sortname.setText("");
						text_dateborrow.setText("");
						text_overduemoney.setText("");
					} else {
						MessageUtil.promt(shell, "温馨提示", "录入失败失败");
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
		button_3.setText("添加");
		button_3.setFont(SWTResourceManager.getFont("新宋体", 14, SWT.BOLD));
		button_3.setBounds(360, 507, 80, 27);

		Button button_4 = new Button(com_booksort, SWT.NONE);
		// 重置
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_sortID.setText("");
				text_sortname.setText("");
				text_dateborrow.setText("");
				text_overduemoney.setText("");
			}
		});
		button_4.setText("重置");
		button_4.setFont(SWTResourceManager.getFont("新宋体", 14, SWT.BOLD));
		button_4.setBounds(568, 507, 80, 27);

		com_bookenroll = new Composite(composite, SWT.NONE);
		com_bookenroll.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/ntk-1339-4840[1].jpg"));

		Label label_43 = new Label(com_bookenroll, SWT.NONE);
		label_43.setText("图书信息添加");
		label_43.setFont(SWTResourceManager.getFont("新宋体", 18, SWT.BOLD | SWT.ITALIC));
		label_43.setBounds(399, 35, 159, 32);

		Label label_47 = new Label(com_bookenroll, SWT.NONE);
		label_47.setText("图书编号：:");
		label_47.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		label_47.setBounds(161, 92, 111, 21);

		Label label_48 = new Label(com_bookenroll, SWT.NONE);
		label_48.setText("图书名称：");
		label_48.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		label_48.setBounds(161, 153, 111, 21);

		Label label_49 = new Label(com_bookenroll, SWT.NONE);
		label_49.setText("图书作者：");
		label_49.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		label_49.setBounds(161, 227, 111, 21);

		Label label_50 = new Label(com_bookenroll, SWT.NONE);
		label_50.setText("出 版 社：");
		label_50.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		label_50.setBounds(161, 299, 111, 21);

		Label label_51 = new Label(com_bookenroll, SWT.NONE);
		label_51.setText("图书译者：");
		label_51.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		label_51.setBounds(161, 378, 111, 21);

		Label lblId_1 = new Label(com_bookenroll, SWT.NONE);
		lblId_1.setText("图书类别:");
		lblId_1.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		lblId_1.setBounds(532, 92, 111, 21);

		Label label_53 = new Label(com_bookenroll, SWT.NONE);
		label_53.setText("出版日期：");
		label_53.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		label_53.setBounds(532, 153, 111, 21);

		Label label_54 = new Label(com_bookenroll, SWT.NONE);
		label_54.setText("图书价格：");
		label_54.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		label_54.setBounds(532, 227, 111, 21);

		Label label_55 = new Label(com_bookenroll, SWT.NONE);
		label_55.setText("押    金：");
		label_55.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		label_55.setBounds(532, 299, 111, 21);

		Label label_52 = new Label(com_bookenroll, SWT.NONE);
		label_52.setText("图书数量：");
		label_52.setFont(SWTResourceManager.getFont("新宋体", 16, SWT.BOLD));
		label_52.setBounds(532, 378, 111, 21);

		text_bookida = new Text(com_bookenroll, SWT.BORDER);
		text_bookida.setBounds(318, 94, 133, 23);

		text_bookname = new Text(com_bookenroll, SWT.BORDER);
		text_bookname.setBounds(318, 155, 133, 23);

		text_bookzz = new Text(com_bookenroll, SWT.BORDER);
		text_bookzz.setBounds(318, 229, 133, 23);

		text_bookcbs = new Text(com_bookenroll, SWT.BORDER);
		text_bookcbs.setBounds(318, 301, 133, 23);

		text_bookyz = new Text(com_bookenroll, SWT.BORDER);
		text_bookyz.setBounds(318, 380, 133, 23);

		text_bookmoney = new Text(com_bookenroll, SWT.BORDER);
		text_bookmoney.setBounds(693, 229, 133, 23);

		text_keepmoney = new Text(com_bookenroll, SWT.BORDER);
		text_keepmoney.setBounds(693, 301, 133, 23);

		text_booknum = new Text(com_bookenroll, SWT.BORDER);
		text_booknum.setBounds(693, 380, 133, 23);

		final CalendarCombo aa = new CalendarCombo(com_bookenroll, SWT.NONE);
		aa.setBounds(693, 153, 133, 23);

		Button button_5 = new Button(com_bookenroll, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 得到 图书编号 类别ID 图书名称 图书作者 图书译者 出版社 出版日期 图书价格 押金
				int bookida = Integer.parseInt(text_bookida.getText().toString());
				String booksortid = text_booksortid.getText();
				String bookname = text_bookname.getText();
				String bookzz = text_bookzz.getText();
				String bookyz = text_bookyz.getText();
				String bookcbs = text_bookcbs.getText();
				String bookborn = aa.getDateAsString();
				String bookmoney = text_bookmoney.getText();
				String keepmoney = text_keepmoney.getText();
				int booknum = Integer.parseInt(text_booknum.getText().toString());
				List<Object> params = new ArrayList<Object>();
				params.add(bookida);
				params.add(booksortid);
				params.add(bookname);
				params.add(bookzz);
				params.add(bookyz);
				params.add(bookcbs);
				params.add(bookborn);
				params.add(bookmoney);
				params.add(keepmoney);
				params.add(booknum);

				try {
					boolean flag = bookInfoDAO.registerBooka(params);
					if (flag) {
						// int booknum =
						// Integer.parseInt(text_booknum.getText().toString());int
						// i = booknum;
						// int i = booknum;
						// for(int j=0;j<i;j++){
						// int ISBN =
						// Integer.parseInt(text_bookida.getText().toString());
						// List<Object> params1=new ArrayList<Object>();
						// params1.add(ISBN);
						// boolean type
						// =bookInfoDAO.registerUpdateBooka(params1);
						// }
						MessageUtil.promt(shell, "温馨提示", "录入成功");
						//
						text_bookida.setText("");
						text_bookname.setText("");
						text_bookzz.setText("");
						text_bookcbs.setText("");
						text_booksortid.setText("");
						text_bookmoney.setText("");
						text_keepmoney.setText("");
						text_bookyz.setText("");
						text_booknum.setText("");

					} else {
						MessageUtil.promt(shell, "温馨提示", "录入失败");
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
		button_5.setText("添加");
		button_5.setFont(SWTResourceManager.getFont("新宋体", 13, SWT.BOLD));
		button_5.setBounds(399, 475, 80, 27);

		Button button_6 = new Button(com_bookenroll, SWT.NONE);
		button_6.setText("重置");
		button_6.setFont(SWTResourceManager.getFont("新宋体", 13, SWT.BOLD));
		button_6.setBounds(608, 475, 80, 27);

		text_booksortid = new Text(com_bookenroll, SWT.BORDER);
		text_booksortid.setBounds(693, 94, 90, 23);

		combo_srotid = new Combo(com_bookenroll, SWT.NONE);

		// try {// 自动加载显示图书类别信息
		// List<Map<String, Object>> list = bookInfoDAO.showBooksort();
		// for (Map<String, Object> map : list) {
		// // 将类别名称加载到下拉列表
		// combo_srotid.add(map.get("TYPE_ID") + "-" +
		// map.get("TYPE_NAME").toString());
		//
		// }
		// combo_srotid.select(0);// 设置下拉列表里面的默认值
		// } catch (SQLException e2) {
		// // TODO Auto-generated catch block
		// e2.printStackTrace();
		// } catch (IOException e2) {
		// // TODO Auto-generated catch block
		// e2.printStackTrace();
		// }

		// combo_srotid.setItems(new String[] {"计算机类1", "建筑类2"});
		combo_srotid.setBounds(804, 94, 111, 25);

		com_bookupdate = new Composite(composite, SWT.NONE);
		com_bookupdate
				.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/11141412_083856958875_2.png"));

		Label lblNewLabel_17 = new Label(com_bookupdate, SWT.NONE);
		lblNewLabel_17.setBounds(326, 75, 76, 17);
		lblNewLabel_17.setText("书籍编号：");

		text_27 = new Text(com_bookupdate, SWT.BORDER);
		text_27.setBounds(422, 72, 141, 23);

		Button btnNewButton_13 = new Button(com_bookupdate, SWT.NONE);
		btnNewButton_13.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text_27.getText().trim();
				try {
					List<Map<String, Object>> list = operatorDAO.findBookByISBN(info);
					table_5.removeAll();
					if (null != list && list.size() > 0) {
						for (Map<String, Object> map : list) {
							TableItem tableItem_5 = new TableItem(table_5, SWT.NONE);
							tableItem_5.setText(new String[] { map.get("ISBN").toString(),
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
			}
		});
		btnNewButton_13.setBounds(627, 70, 80, 27);
		btnNewButton_13.setText("查询");

		Button btnNewButton_14 = new Button(com_bookupdate, SWT.NONE);
		btnNewButton_14.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// String ISBN= text_27.getText();
				// String bookNUM =text_28.getText();
				int ISBN = Integer.parseInt(text_27.getText().toString());
				int bookNUM = Integer.parseInt(text_28.getText().toString());
				List<Object> params = new ArrayList<Object>();

				params.add(bookNUM);
				params.add(ISBN);
				// System.out.println(ISBN+"+"+bookNUM);

				try {
					boolean flag = operatorDAO.UpdateBookNew(params);
					if (flag) {
						MessageUtil.promt(shell, "温馨提示", "修改成功！");
						text_28.setText("");
						String info = text_27.getText().trim();
						try {
							List<Map<String, Object>> list = operatorDAO.findBookByISBN(info);
							table_5.removeAll();
							if (null != list && list.size() > 0) {
								for (Map<String, Object> map : list) {
									TableItem tableItem_5 = new TableItem(table_5, SWT.NONE);
									tableItem_5.setText(new String[] { map.get("ISBN").toString(),
											map.get("BOOK_NAME").toString(), map.get("TYPE_NAME").toString(),
											map.get("BOOK_WRITER").toString(), map.get("BOOK_COST").toString(),
											map.get("BOOK_PUBLISHER").toString(), map.get("BOOK_NUM").toString() });

								}
							}

						} catch (SQLException e1) {
							LogUtil.logger.info(e1.getMessage() + new Date());
						} catch (IOException e1) {
							LogUtil.logger.info(e1.getMessage() + new Date());
						}
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
		btnNewButton_14.setBounds(534, 479, 80, 27);
		btnNewButton_14.setText("修改");

		Button btnNewButton_15 = new Button(com_bookupdate, SWT.NONE);
		btnNewButton_15.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean flag = MessageDialog.openConfirm(shell, "温馨提示", "您确定要退出吗？");
				if (flag) {
					Display.getDefault().close();
					System.exit(0);
				}
			}
		});
		btnNewButton_15.setBounds(666, 479, 80, 27);
		btnNewButton_15.setText("退出");

		table_5 = new Table(com_bookupdate, SWT.BORDER | SWT.FULL_SELECTION);
		table_5.setLinesVisible(true);
		table_5.setHeaderVisible(true);
		table_5.setBounds(152, 131, 705, 268);

		TableColumn tableColumn_20 = new TableColumn(table_5, SWT.NONE);
		tableColumn_20.setWidth(100);
		tableColumn_20.setText("书籍编号");

		TableColumn tableColumn_29 = new TableColumn(table_5, SWT.NONE);
		tableColumn_29.setWidth(100);
		tableColumn_29.setText("书籍名称");

		TableColumn tableColumn_32 = new TableColumn(table_5, SWT.NONE);
		tableColumn_32.setWidth(100);
		tableColumn_32.setText("书籍类别");

		TableColumn tableColumn_33 = new TableColumn(table_5, SWT.NONE);
		tableColumn_33.setWidth(100);
		tableColumn_33.setText("书籍作者");

		TableColumn tableColumn_34 = new TableColumn(table_5, SWT.NONE);
		tableColumn_34.setWidth(100);
		tableColumn_34.setText("书籍价格");

		TableColumn tableColumn_35 = new TableColumn(table_5, SWT.NONE);
		tableColumn_35.setWidth(100);
		tableColumn_35.setText("出版社");

		TableColumn tableColumn_36 = new TableColumn(table_5, SWT.NONE);
		tableColumn_36.setWidth(100);
		tableColumn_36.setText("当前库存量");

		Menu menu_12 = new Menu(table_5);
		table_5.setMenu(menu_12);

		MenuItem mntmNewItem_9 = new MenuItem(menu_12, SWT.NONE);
		mntmNewItem_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String ISBN = text_27.getText();
				try {
					List<Map<String, Object>> list = operatorDAO.findUpdateBookNew(ISBN);
					if (null != list && list.size() > 0) {
						for (Map<String, Object> map : list) {
							text_28.setText(map.get("BOOK_NUM").toString());
						}
					}
					return;
				} catch (SQLException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mntmNewItem_9.setText("修改");

		Label lblNewLabel_18 = new Label(com_bookupdate, SWT.NONE);
		lblNewLabel_18.setBounds(161, 484, 100, 17);
		lblNewLabel_18.setText("当前库存量：");

		text_28 = new Text(com_bookupdate, SWT.BORDER);
		text_28.setBounds(279, 478, 120, 23);

		com_BarChart = new Composite(composite, SWT.NONE);
		com_BarChart.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/11_副本.jpg"));
		com_BarChart.setLayout(new FormLayout());

		Label lblNewLabel_19 = new Label(com_BarChart, SWT.NONE);
		FormData fd_lblNewLabel_19 = new FormData();
		fd_lblNewLabel_19.left = new FormAttachment(0, 287);
		lblNewLabel_19.setLayoutData(fd_lblNewLabel_19);
		lblNewLabel_19.setText("统计方式：");

		final Combo combo_1 = new Combo(com_BarChart, SWT.NONE);
		fd_lblNewLabel_19.top = new FormAttachment(combo_1, 3, SWT.TOP);
		fd_lblNewLabel_19.right = new FormAttachment(combo_1, -55);
		FormData fd_combo_1 = new FormData();
		fd_combo_1.top = new FormAttachment(0, 50);
		fd_combo_1.left = new FormAttachment(0, 403);
		combo_1.setLayoutData(fd_combo_1);
		combo_1.setItems(new String[] { "日借阅量", "月借阅量", "年借阅量" });

		Button button_7 = new Button(com_BarChart, SWT.NONE);
		fd_combo_1.right = new FormAttachment(button_7, -143);
		FormData fd_button_7 = new FormData();
		fd_button_7.top = new FormAttachment(0, 48);
		fd_button_7.right = new FormAttachment(100, -270);
		fd_button_7.left = new FormAttachment(0, 634);
		button_7.setLayoutData(fd_button_7);

		button_7.setText("统计查询");

		composite_1 = new Composite(com_BarChart, SWT.NONE);
		FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(100, -138);
		fd_composite_1.top = new FormAttachment(combo_1, 18);
		fd_composite_1.right = new FormAttachment(100, -155);
		fd_composite_1.left = new FormAttachment(0, 176);
		composite_1.setLayoutData(fd_composite_1);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		// 统计
		button_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sLayout.topControl = composite_1;
				com_BarChart.layout();
				String BarChart = combo_1.getText();
				if (null == BarChart || "".equals(BarChart)) {
					if (compositechart == null) {
						JFreeChart chart = getChart();
						compositechart = new ChartComposite(composite_1, SWT.NONE, chart);
						composite_1.layout();
						return;

					}
					compositechart.dispose();
					JFreeChart chart = getChart();
					compositechart = new ChartComposite(composite_1, SWT.NONE, chart);
					composite_1.layout();
				} else if ("日借阅量".equals(BarChart)) {
					if (compositechart == null) {
						JFreeChart chart1 = getChart1();
						compositechart = new ChartComposite(composite_1, SWT.NONE, chart1);
						composite_1.layout();
						return;

					}
					compositechart.dispose();
					JFreeChart chart1 = getChart1();
					compositechart = new ChartComposite(composite_1, SWT.NONE, chart1);
					composite_1.layout();
				} else if ("月借阅量".equals(BarChart.trim())) {
					if (compositechart == null) {
						JFreeChart chart2 = getChart2();
						compositechart = new ChartComposite(composite_1, SWT.NONE, chart2);
						composite_1.layout();
						return;

					}
					compositechart.dispose();
					JFreeChart chart2 = getChart2();
					compositechart = new ChartComposite(composite_1, SWT.NONE, chart2);
					composite_1.layout();
				} else if ("年借阅量".equals(BarChart.trim())) {
					if (compositechart == null) {
						JFreeChart chart3 = getChart3();
						compositechart = new ChartComposite(composite_1, SWT.NONE, chart3);
						composite_1.layout();
						return;

					}
					compositechart.dispose();
					JFreeChart chart3 = getChart3();
					compositechart = new ChartComposite(composite_1, SWT.NONE, chart3);
					composite_1.layout();
				}

			}
		});

		// formToolkit.adapt(com_booksort);
		// formToolkit.paintBordersFor(com_booksort);
		// formToolkit.adapt(com_statistics);
		// formToolkit.paintBordersFor(com_statistics);
		// formToolkit.adapt(text_rsfz1, true, true);

		btnNewButton_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = reader_id.getText().trim();
				try {
					List<Map<String, Object>> list;
					list = operatorDAO.findBorrowReaderNew(info);
					if (null != list && list.size() > 0) {
						for (Map<String, Object> map : list) {

							reader_name.setText(map.get("READER_NAME").toString());
							maxNum.setText(map.get("MAXNUM").toString());
							reader_tel.setText(map.get("READER_TEL").toString());
						}
						int maxnum = Integer.parseInt(maxNum.getText().toString());
						String rd_name = reader_name.getText();
						if (maxnum <= 0) {
							MessageUtil.promt(shell, "温馨提示", "该读者当前可借阅量为0，不能借阅书籍！");
							shell.dispose();
							Main main = new Main();
							main.open();
					
						} else {
							MessageUtil.promt(shell, "温馨提示", rd_name + ",您当前还剩" + maxnum + "次借阅量！");
						}
					} else {
						MessageUtil.promt(shell, "温馨提示", "无该读者信息！");
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

		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = ISBN.getText().trim();
				try {
					List<Map<String, Object>> list;
					list = operatorDAO.findBorrowBookNew(info);
					if (null != list && list.size() > 0) {
						for (Map<String, Object> map : list) {
							book_name.setText(map.get("BOOK_NAME").toString());
							type_name.setText(map.get("TYPE_NAME").toString());
							inv_num.setText(map.get("BOOK_NUM").toString());
							keepMoney.setText(map.get("KEEPMONEY").toString());
							book_borrowDay.setText(map.get("BOOK_BORROWDAY").toString());
						}
						int invnum = Integer.parseInt(inv_num.getText().toString());
						String bk_name = book_name.getText();
						if (invnum <= 0) {
							MessageUtil.promt(shell, "温馨提示", "该书籍当前库存量为0，无法借阅！");
							shell.dispose();
							Main main = new Main();
							main.open();
					
						} else {
							MessageUtil.promt(shell, "温馨提示", "该书籍当前库存量为" + invnum + "您是否确定借阅！");
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

		btnNewButton_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text_10.getText().trim();
				try {
					List<Map<String, Object>> list;
					list = operatorDAO.findBorrowBook(info);
					table_1.removeAll();
					if (null != list && list.size() > 0) {
						for (Map<String, Object> map : list) {
							// System.out.println(list.size());
							TableItem tableItem_1 = new TableItem(table_1, SWT.NONE);
							// System.out.println(map.toString());
							tableItem_1
									.setText(new String[] { map.get("BOOK_NAME").toString(), map.get("ISBN").toString(),
											map.get("TYPE_NAME").toString(), map.get("READER_NAME").toString(),
											map.get("READER_ID").toString(), map.get("BORROWDATE").toString(), null });//
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

		// 退出
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean flag = MessageDialog.openConfirm(shell, "温馨提示", "您确定要退出吗？");
				if (flag) {
					Display.getDefault().close();
					System.exit(0);
				}
			}
		});

		// 添加订购操作
		btnNewButton_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String type_name = text.getText();
				String book_name = text_1.getText();
				String order_date = orderDate.getDateAsString();
				int book_money = Integer.parseInt(text_3.getText().toString());
				int book_num = Integer.parseInt(text_4.getText().toString());
				int operator_id = Integer.parseInt(text_5.getText().toString());

				List<Object> params = new ArrayList<Object>();
				params.add(type_name);
				params.add(book_name);
				params.add(book_num);
				params.add(book_money);
				params.add(order_date);
				params.add(operator_id);

				try {
					boolean flag = operatorDAO.OrderBook(params);
					if (flag) {
						MessageUtil.promt(shell, "温馨提示", "订购成功");
						// 清楚页面上数据
						text.setText("");
						text_1.setText("");
						orderDate.setText("");
						text_3.setText("");
						text_4.setText("");
						text_5.setText("");
					} else {
						MessageUtil.promt(shell, "温馨提示", "订购失败");
					}

				} catch (SQLException e1) {
					MessageUtil.promt(shell, "出错了", e1.getMessage());
				} catch (IOException e1) {
					MessageUtil.promt(shell, "出错了", e1.getMessage());
				}

			}
		});
		// 查询操作
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text_18.getText().trim();

				// 表示文本框没有输入查询条件
				if (null == info || "".equals(info)) {
					// 第一种方法：提示错误信息
					// MessageUtil.promt(shell, "错误提示", "必须输入班级或学号信息");
					// 第二种方法：查看所有信息
					try {
						java.util.List<Map<String, Object>> list = operatorDAO.findAllBook();
						table_2.removeAll();
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {
								// System.out.println(list.size());
								TableItem tableItem_2 = new TableItem(table_2, SWT.NONE);
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
						table_2.removeAll();
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {
								// System.out.println(list.size());

								TableItem tableItem_2 = new TableItem(table_2, SWT.NONE);
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
						table_2.removeAll();
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {
								TableItem tableItem_2 = new TableItem(table_2, SWT.NONE);
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
						table_2.removeAll();
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {
								TableItem tableItem_2 = new TableItem(table_2, SWT.NONE);
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
						table_2.removeAll();
						if (null != list && list.size() > 0) {
							for (Map<String, Object> map : list) {
								TableItem tableItem_2 = new TableItem(table_2, SWT.NONE);
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

		// 借出当前图书
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// int stu_id=Integer.parseInt(t.getText(0));
				int Reader_id = Integer.parseInt(reader_id.getText().toString());
				int Book_id = Integer.parseInt(ISBN.getText().toString());
				int isback = Integer.parseInt(book_borrowDay.getText());
				String sysdate = borrow_date.getDateAsString();
				int operator_id = Integer.parseInt(text_9.getText());

				List<Object> params = new ArrayList<Object>();
				params.add(Reader_id);
				params.add(Book_id);
				params.add(isback);
				params.add(sysdate);
				params.add(operator_id);

				try {
					boolean flag;
					try {
						flag = operatorDAO.BorrowBook(params);

						if (flag) {

							MessageUtil.promt(shell, "温馨提示", "借阅成功");

							// reader_id.setText("");
							reader_name.setText("");
							maxNum.setText("");
							reader_tel.setText("");
							// ISBN.setText("");
							book_name.setText("");
							type_name.setText("");
							inv_num.setText("");
							keepMoney.setText("");
							borrow_date.setText("");
							text_9.setText("");
							book_borrowDay.setText("");

							boolean flag1 = operatorDAO.BorrowUpdate(Reader_id);

							boolean flag2 = operatorDAO.BorrowUpdate1(Book_id);

							String info = reader_id.getText().trim();
							List<Map<String, Object>> list;
							try {
								list = operatorDAO.findBookBorrow(info);

								table.removeAll();
								if (null != list && list.size() > 0) {
									for (Map<String, Object> map : list) {
										TableItem tableItem_3 = new TableItem(table, SWT.NONE);
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

						} else {
							MessageUtil.promt(shell, "温馨提示", "借阅失败");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (IOException e12) {
					// TODO Auto-generated catch block
					e12.printStackTrace();
				}
			}
		});

		// 确认验收
		btnNewButton_10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String type_name = text_6.getText();
				String book_name = text_7.getText();
				int book_cost = Integer.parseInt(text_20.getText().toString());
				int book_num = Integer.parseInt(text_21.getText().toString());
				String order_date = text_19.getText();
				int operator_id = Integer.parseInt(text_22.getText().toString());

				List<Object> params = new ArrayList<Object>();
				params.add(type_name);
				params.add(book_name);
				params.add(book_cost);
				params.add(book_num);
				params.add(order_date);
				params.add(operator_id);

				try {
					boolean flag = operatorDAO.ReceiveBook(params);
					if (flag) {
						MessageUtil.promt(shell, "温馨提示", "验收成功");

						try {
							boolean type1 = operatorDAO.ReceiveBook(book_name);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						table_3.removeAll();
						try {
							// 根据页面显示要求查看读者信息
							List<Map<String, Object>> list = operatorDAO.showAllOrder();
							// System.out.println(list.toString());//打印数据

							for (Map<String, Object> map : list) {

								TableItem tableItem5 = new TableItem(table_3, SWT.NONE);
								tableItem5.setText(new String[] { map.get("ORDER_BOOKTYPE").toString(),
										map.get("ORDER_BOOKNAME").toString(), map.get("ORDER_BOOKNUM").toString(),
										map.get("ORDER_BOOKMONEY").toString(), map.get("ORDER_DATE").toString(),
										map.get("OPERATOR_ID").toString() });
							}

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						// 清楚页面上数据
						text_6.setText("");
						text_7.setText("");
						text_19.setText("");
						text_20.setText("");
						text_21.setText("");
						text_22.setText("");
					} else {
						MessageUtil.promt(shell, "温馨提示", "验收失败");
					}

				} catch (SQLException e1) {
					MessageUtil.promt(shell, "出错了", e1.getMessage());
				} catch (IOException e1) {
					MessageUtil.promt(shell, "出错了", e1.getMessage());
				}

			}
		});
	}

	private JFreeChart getChart() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<Map<String, Object>> list = null;
		try {
			list = operatorDAO.countBorrowByColn();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Map<String, Object> map : list) {
			dataset.addValue(Integer.parseInt(map.get("C").toString()), map.get("B").toString(),
					map.get("B").toString());
		}

		JFreeChart chart = ChartFactory.createBarChart3D("各书籍借阅数量统计", "书籍名称", "借阅数量(本)", dataset,
				PlotOrientation.VERTICAL, false, false, false);
		return chart;
	}

	private JFreeChart getChart1() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<Map<String, Object>> list = null;
		try {
			list = operatorDAO.countBorrowByColn1();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Map<String, Object> map : list) {
			dataset.addValue(Integer.parseInt(map.get("C").toString()), map.get("B").toString(),
					map.get("B").toString());
		}

		JFreeChart chart1 = ChartFactory.createBarChart3D("各书籍借阅数量统计", "借阅日期(日)", "借阅数量(本)", dataset,
				PlotOrientation.VERTICAL, false, false, false);
		return chart1;
	}

	private JFreeChart getChart2() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<Map<String, Object>> list = null;
		try {
			list = operatorDAO.countBorrowByColn2();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Map<String, Object> map : list) {
			dataset.addValue(Integer.parseInt(map.get("C").toString()), map.get("B").toString(),
					map.get("B").toString());
		}

		JFreeChart chart2 = ChartFactory.createBarChart3D("各书籍借阅数量统计", "借阅日期(月)", "借阅数量(本)", dataset,
				PlotOrientation.VERTICAL, false, false, false);
		return chart2;
	}

	private JFreeChart getChart3() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<Map<String, Object>> list = null;
		try {
			list = operatorDAO.countBorrowByColn3();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Map<String, Object> map : list) {
			dataset.addValue(Integer.parseInt(map.get("C").toString()), map.get("B").toString(),
					map.get("B").toString());
		}

		JFreeChart chart3 = ChartFactory.createBarChart3D("各书籍借阅数量统计", "借阅日期(年)", "借阅数量(本)", dataset,
				PlotOrientation.VERTICAL, false, false, false);
		return chart3;
	}
}
