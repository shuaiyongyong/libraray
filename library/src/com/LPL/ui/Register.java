package com.LPL.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.LPL.commons.MessageUtil;
import com.LPL.dao.StudentDAO;
import com.ibm.icu.util.Calendar;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class Register {

	protected Shell shell;
	private Text text_name;
	private Text text_pwd;
	private Text text_idcard;
	private Text text_phone;
	StudentDAO studentDAO=new StudentDAO();
	Label Labela_readerimg ;
	private Text text_borndate;
	private Text text_Pwd;
	Label label_pwd;
	Label label_telnum;
	Label label_idnum;
	Composite composite ;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Register window = new Register();
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
		shell.setImage(SWTResourceManager.getImage(Register.class, "/images/logo.jpg"));
		shell.setSize(600, 500);
		shell.setText("读者注册系统");
		//背景虚化
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);//控件背景虚化
		//主窗体居中
		//Dimension：标出尺寸，Toolkit:工具包
		
		Dimension dem=Toolkit.getDefaultToolkit().getScreenSize();
		//窗体居中显示
		shell.setLocation((dem.width-shell.getSize().x)/2,(dem.height-shell.getSize().y)/2);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(Register.class, "/images/2015581704911427_副本.jpg"));
		//com_readerenroll.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/读者注册.jpg"));
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		lblNewLabel.setBounds(45, 38, 84, 17);
		lblNewLabel.setText("学 生 姓 名 :");

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		lblNewLabel_1.setBounds(45, 91, 84, 17);
		lblNewLabel_1.setText("学 生 性 别 ：");

		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		lblNewLabel_2.setBounds(45, 146, 84, 17);
		lblNewLabel_2.setText("用 户 密 码 ：");

		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		lblNewLabel_4.setBounds(45, 238, 84, 17);
		lblNewLabel_4.setText("出 生 日 期 ：");

		final Button btnRadioButton_1 = new Button(composite, SWT.RADIO);
		btnRadioButton_1.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		btnRadioButton_1.setBounds(201, 91, 57, 17);
		btnRadioButton_1.setText("女");

		final Button btnRadioButton = new Button(composite, SWT.RADIO);
		btnRadioButton.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		btnRadioButton.setBounds(135, 91, 55, 17);
		btnRadioButton.setText("男");

		Label lblNewLabel_5 = new Label(composite, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		lblNewLabel_5.setBounds(45, 281, 84, 17);
		lblNewLabel_5.setText("身份份证号码：：");

		text_name = new Text(composite, SWT.BORDER);
		text_name.setBounds(139, 35, 119, 23);

		text_pwd = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_pwd.setBounds(136, 143, 119, 23);

		text_idcard = new Text(composite, SWT.BORDER);
		text_idcard.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String IDCard =text_idcard.getText().toString();
				if(IDCard.matches("\\d{18,20}")){
					
					label_idnum.setText("");
				}else{
					label_idnum.setText("*身份证号为18位整数!");
				}				

			}
		});
		text_idcard.setBounds(135, 278, 123, 23);

		text_phone = new Text(composite, SWT.BORDER);
		text_phone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String Phone = text_phone.getText();
				if(Phone.matches("\\d{11,12}")){				
					label_telnum.setText("");
				}else{
					label_telnum.setText("*电话号码为11位整数！");
				}				
				
			}
		});
		text_phone.setBounds(137, 318, 119, 23);

		final Button Button_addimges = new Button(composite, SWT.NONE);
		Button_addimges.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		
		Button_addimges.setBounds(468, 250, 80, 27);
		Button_addimges.setText("上传照片");

		Labela_readerimg = new Label(composite, SWT.NONE);
		//Labela_readerimg.setImage(SWTResourceManager.getImage(Register.class, "/images/03W27UXOZ17J[1]_副本.jpg"));
		Labela_readerimg.setBounds(439, 38, 135, 179);

		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		btnNewButton_1.setBounds(57, 385, 80, 27);
		btnNewButton_1.setText("注册");

		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		//退出
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				// t.stop();
				Login logina = new Login();
				logina.open();
			}
		});
		btnNewButton_2.setBounds(374, 397, 80, 27);
		btnNewButton_2.setText("退出");

		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		label.setText("电 话 号 码 ：");
		label.setBounds(45, 321, 84, 17);
		
		
		////text_borndate = new Text(composite, SWT.BORDER);
		//Calendar bri_date = Calendar.getInstance();
		//text_borndate.setBounds(135, 235, 123, 23);
		 final CalendarCombo bri_date=new CalendarCombo(composite, SWT.BORDER);
		 bri_date.setBounds(135, 232, 123, 23);
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		lblNewLabel_3.setBounds(45, 193, 84, 17);
		lblNewLabel_3.setText("确认用户密码：");
		
		text_Pwd = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_Pwd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String  pwda=text_pwd.getText();
			    String pwdb=text_Pwd.getText();
				if(pwda.equals(pwdb)){
					label_pwd.setText("");
				}else{
					label_pwd.setText("* 密码不一致！请重新输入！");
				}
			}
		});
		text_Pwd.setBounds(135, 190, 123, 23);
		
		label_pwd = new Label(composite, SWT.NONE);
		//label_pwd.addFocusListener(new FocusAdapter() {
			//@Override
			//public void focusLost(FocusEvent e) {
			
			//}
		//});
		//label_pwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_pwd.setBounds(285, 193, 148, 17);
		
		label_idnum = new Label(composite, SWT.NONE);
		//label_idnum.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_idnum.setBounds(285, 281, 148, 17);
		label_idnum.setText("");
		
		label_telnum = new Label(composite, SWT.NONE);
		label_telnum.setText("");
		//label_telnum.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_telnum.setBounds(285, 318, 148, 17);

		//注册方法
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//获取图片路
				
					String path=Button_addimges.getText();
					File file =new File(path);
					//获取姓名 性别  密码  出生日期 身份证号码  电话  VIP账号
					
					String  usernamea=text_name.getText();
					String sex="";
				    if(btnRadioButton.getSelection()){
				    	sex=btnRadioButton.getText();
				    	
				    }else if(btnRadioButton_1.getSelection()){
				    	sex=btnRadioButton_1.getText();
				    	
				    }
				    String  pwda=text_pwd.getText();
				    String pwdb=text_Pwd.getText();
				    String bdate = bri_date.getDateAsString();
				   // System.out.println(bdate);
				    String id =text_idcard.getText();
				    String phone = text_phone.getText();
				    List<Object> params=new ArrayList<Object>();
				    params.add(usernamea);
				    params.add(sex);
				    params.add(pwda);
				    params.add(bdate);
				    params.add(id);
				    params.add(phone);
				    params.add(file);
				    
				    
				    try {
						boolean flag=studentDAO.registerStudent(params);
						if (flag) {
							MessageUtil.promt(shell, "温馨提示", "注册成功");
							//清楚页面上数据
							text_name.setText("");
							text_pwd.setText("");
							text_Pwd.setText("");
							bri_date.setText("");
							text_idcard.setText("");
							text_phone.setText("");
							Button_addimges.setText("");//上传照片
							Labela_readerimg.setImage(SWTResourceManager.getImage(Main.class,"/images/03W27UXOZ17J[1]_副本.jpg"));
						}else{
							MessageUtil.promt(shell, "温馨提示", "注册失败");
						}
						
					} catch (SQLException e1) {
						MessageUtil.promt(shell, "出错了",e1.getMessage() );
					} catch (IOException e1) {
						MessageUtil.promt(shell, "出错了",e1.getMessage() );
					}	
				
				
				
			}
			});
		
		//上传照片
				Button_addimges.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						FileDialog fd=new FileDialog(shell,SWT.SINGLE);
						fd.setText("请选择您要上传的图片");
						fd.setFilterPath("SystemRoot");
						fd.setFilterExtensions(new String []{"*.png","*.jpg","*.*"});
						String selected=fd.open();
						if(null==selected){
							return;
						}
						File file=new File(selected);
						InputStream in;

						try {
							in=new FileInputStream(file);
							Image image=new Image(Display.getDefault(),in);
							Labela_readerimg.setImage(image);

							Button_addimges.setText(selected);//将图片的路径显示在按钮上

						} catch (FileNotFoundException e1) {
							MessageUtil.promt(shell, "出错了", e1.getMessage());
						}

					}
				});
			
	}
}
