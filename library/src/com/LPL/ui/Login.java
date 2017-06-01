package com.LPL.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.LPL.commons.MessageUtil;
import com.LPL.commons.PwdCommon;
import com.LPL.dao.OperatorDAO;
import com.LPL.dao.OperatorInfoDAO;
import com.LPL.ui.Main;
import com.LPL.dao.StudentDAO;
import com.LPL.ui.StuMain;
import com.LPL.ui.Register;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;

public class Login {

	protected Shell shell;
	private Text text_name;
	private Text text_pwd;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
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
		shell = new Shell(shell,SWT.MIN);
		shell.setImage(SWTResourceManager.getImage(Login.class, "/images/logo.jpg"));
		shell.setSize(450, 300);
		shell.setText("图书管理系统登录");
		//主窗体居中
		//Dimension：标出尺寸，Toolkit:工具包
		Dimension dem=Toolkit.getDefaultToolkit().getScreenSize();
		//窗体居中显示
		shell.setLocation((dem.width-shell.getSize().x)/2,(dem.height-shell.getSize().y)/2);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
		sashForm.setSize(444, 272);
		sashForm.setLocation(0, 0);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(Login.class, "/images/login_副本.jpg"));
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		

		final Button btn_operator = new Button(composite_1, SWT.RADIO);
		btn_operator.setBounds(165, 20, 90, 16);
		btn_operator.setText("管理员");
		
		final Button btn_reader = new Button(composite_1, SWT.RADIO);
		btn_reader.setBounds(276, 20, 90, 16);
		btn_reader.setText("学生");
		
		Label label_name = new Label(composite_1, SWT.NONE);
		label_name.setBounds(78, 52, 73, 15);
		label_name.setText("用  户  名：");
		
		Label label_pwd = new Label(composite_1, SWT.NONE);
		label_pwd.setBounds(78, 94, 73, 15);
		label_pwd.setText("密        码：");
		
		text_name = new Text(composite_1, SWT.BORDER);
		
				
		
		text_name.setBounds(167, 49, 153, 21);
		
		text_pwd = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);
		text_pwd.setBounds(167, 91, 153, 21);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		//登录操作
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			
		int q=2;//cuowucishu 
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name=text_name.getText();
				String pwd=text_pwd.getText();
				String operator_name= name;
				Map<String, Object> map=null;
				try{
					if(btn_operator.getSelection()){
						OperatorDAO dao=new OperatorDAO();
						map=new HashMap<String,Object>();
						map=dao.login(name, pwd);
						//判断登录是否成功
						if(null!=map&&map.size()>0){
							//关闭当前主窗体
							PwdCommon.username = operator_name;
							shell.close();
							Main main=new Main();
							main.open();
						}else{//用户名或密码不正确
							//if(q!=0){
							
							
							for (int i = 0; i < 3; i++) {
								
							if(q!=0){
								MessageUtil.promt(shell, "登录失败","您的用户名或密码输入错误，请重新输入，您还有"+q+"次机会");
								q--;
								text_pwd.setText("");
								break;
								
								
							}else{//机会用完
								MessageUtil.promt(shell, "登录失败","您已经输错3次，程序将关闭");
								Display.getDefault().close();
								System.exit(0);
							   }
							
							}
							
							
						}
					
							
					}else if(btn_reader.getSelection()){
					    StudentDAO dao=new StudentDAO();
						map=new HashMap<String,Object>();
						map= dao.login(name, pwd);
						//判断登录是否成功
						if(null!=map&&map.size()>0){
							//关闭当前主窗体
							PwdCommon.username = operator_name;
							shell.close();
							StuMain main=new StuMain();
							main.open();
						}else{//用户名或密码不正确
							//if(q!=0){
							
							
							for (int i = 0; i < 3; i++) {
								
							if(q!=0){
								MessageUtil.promt(shell, "登录失败","您的用户名或密码输入错误，请重新输入，您还有"+q+"次机会");
								q--;
								text_pwd.setText("");
								break;
								
								
							}else{//机会用完
								MessageUtil.promt(shell, "登录失败","您已经输错3次，程序将关闭");
								Display.getDefault().close();
								System.exit(0);
							   }
							
							}
							
							
						}
					
							
					}
				}catch(SQLException | IOException e1){
					MessageUtil.promt(shell, "登录失败",e1.getMessage());
				}
				
			}
		});
		btnNewButton.setBounds(78, 144, 75, 25);
		btnNewButton.setText("登录");
		
		Button btnNewButton_1 = new Button(composite_1, SWT.NONE);
		//注册
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				Register register =new Register();
				
				register.open();
			}
		});
		btnNewButton_1.setBounds(245, 144, 75, 25);
		btnNewButton_1.setText("注册");
		
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(79, 21, 55, 15);
		lblNewLabel.setText("用户类型：");
		sashForm.setWeights(new int[] {86, 183});
		/*
		 * 
	
		 */

	}
}
