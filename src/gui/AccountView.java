package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import core.UserAccount;
import core.utils.AccountManager;

public class AccountView {
	
	private static DefaultTableModel tableModel;
	public static JScrollPane scrollPane;
	
	public static void initAccountView() throws Exception {
		
		MainView.panelRight.removeAll();
		
		// Account List
		refreshAccountList();
        
        // button : 身份创建
        JButton button_1 = new JButton("身份创建");
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.DARK_GRAY);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
        
        // button : 复制公钥
		JButton button_2 = new JButton("复制公钥");
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(Color.DARK_GRAY);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
        
        // button : 重新产生密钥对
		JButton button_3 = new JButton("重新产生密钥对");
		button_3.setForeground(Color.WHITE);
		button_3.setBackground(Color.DARK_GRAY);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
        
        // JTextField
		JTextField readOnlyField = new JTextField("目前没有要显示的内容哦~");
        readOnlyField.setEditable(false);  // 设置为不可编辑
        
        JTextField nameField = new JTextField("此处输入新建身份的Name字段");
        nameField.setEditable(false);  // 设置为不可编辑
        
        // 添加进panelRight
        MainView.panelRight.add(scrollPane);
        MainView.panelRight.add(button_1);
        MainView.panelRight.add(button_2);
        MainView.panelRight.add(button_3);
        MainView.panelRight.add(nameField);
        MainView.panelRight.add(readOnlyField);
        
        // 布局
		GridBagLayout prLayout = new GridBagLayout();
		MainView.panelRight.setLayout(prLayout);
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;  
        
        Insets insets = new Insets(5, 5, 5, 5);
        gbc.insets = insets;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        prLayout.setConstraints(scrollPane, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1; 
        prLayout.setConstraints(button_1, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1; 
        prLayout.setConstraints(nameField, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1; 
        prLayout.setConstraints(button_2, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1; 
        prLayout.setConstraints(button_3, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = GridBagConstraints.REMAINDER; 
        prLayout.setConstraints(readOnlyField, gbc);
        
	}

	
	public static void refreshAccountList() throws Exception {
		// 读取信息
		AccountManager.getAllUsers();
		
		// 初始化表格数据
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Name");
        columnNames.add("Public Key");
        columnNames.add("Private Key");
        
        // 整合进JTable
        Vector<Vector<String>> rowData = new Vector<>();
        for(String name: AccountManager.userAccounts.keySet()) {
        	UserAccount account = AccountManager.userAccounts.get(name);
        	String pk = "not exist",rk = "not exist";
      			
        	// public key
        	if(account.publicKey != null) {
        		pk = account.pkString;
        		pk = pk + "***";
        	}
        	// private key
        	if(account.getPrivateKey() != null) {
        		rk = "*****";
        	}
        	rowData.add(new Vector<>(List.of(name, pk, rk)));
        }

        // 创建数据模型
        tableModel = new DefaultTableModel(rowData, columnNames);
        // 创建表格
        JTable table = new JTable(tableModel);
        table.setBackground(Color.GRAY);
        table.setForeground(Color.WHITE);
        // 设置表头的背景色和前景色
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.DARK_GRAY);
        tableHeader.setForeground(SystemColor.activeCaption);
        // 创建滚动面板
        scrollPane = new JScrollPane(table);
		// 设置 JViewport 的背景色
        JViewport viewport = scrollPane.getViewport();
        viewport.setBackground(Color.DARK_GRAY);
	}
	
	/**
	 * button_1 
	 */
	public static void createUser() {
		
	}
	
	/**
	 * button_2
	 */
	public static void copyPublicKey() {
		
	}
	
	/**
	 * button_3
	 */
	public static void regenerateKeyPair() {
		
	}
	
}
