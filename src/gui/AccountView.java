package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import core.UserAccount;
import core.utils.AccountManager;
import core.utils.FileUtil;

public class AccountView {
	
	private static DefaultTableModel tableModel;
	public static JScrollPane scrollPane_1; // JTable
	public static JScrollPane scrollPane_2; // JTextArea
	public static JTextField nameField;
	public static JTextArea readOnlyField;
	public static JTable table;
	public static UserAccount selectedUser; // 鼠标选中的user account
	public static int selectedRow;
	
	public static void init() throws Exception {
		
		MainView.panelRight.removeAll();
		
		// Account List
		initAccountList();
        
        // button : 身份创建
        JButton button_1 = new JButton("身份创建");
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.DARK_GRAY);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String userName = nameField.getText();
					createUser(userName);
					readOnlyField.setText("== 已新建身份 " + userName + " ==");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
        
        // button : 身份删除
		JButton button_2 = new JButton("身份删除");
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(Color.DARK_GRAY);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteAccount(selectedUser.name, selectedRow);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
        // button : 复制公钥
		JButton button_3 = new JButton("复制公钥");
		button_3.setForeground(Color.WHITE);
		button_3.setBackground(Color.DARK_GRAY);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyPublicKey();
			}
			
		});
        
        // button : 重新产生密钥对
		JButton button_4 = new JButton("重新产生密钥对");
		button_4.setForeground(Color.WHITE);
		button_4.setBackground(Color.DARK_GRAY);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					regenerateKeyPair();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
        
        // JTextField
		readOnlyField = new JTextArea("目前没有要显示的内容哦~");
        readOnlyField.setEditable(false);  // 设置为不可编辑
        readOnlyField.setBackground(Color.GRAY);
        readOnlyField.setForeground(Color.WHITE);
        readOnlyField.setColumns(5); // 设置列数，决定宽度
        readOnlyField.setLineWrap(true); // 启用自动换行
//        readOnlyField.setWrapStyleWord(true); // 按单词换行
        
        nameField = new JTextField("此处输入新建身份的Name字段");
        nameField.setBackground(Color.GRAY);
        nameField.setForeground(Color.WHITE);
        
        scrollPane_2 = new JScrollPane(readOnlyField);
        
        // 添加进panelRight
        MainView.panelRight.add(scrollPane_1);
        MainView.panelRight.add(button_1);
        MainView.panelRight.add(button_2);
        MainView.panelRight.add(button_3);
        MainView.panelRight.add(button_4);
        MainView.panelRight.add(nameField);
        MainView.panelRight.add(scrollPane_2);
        
        // 布局
		GridBagLayout prLayout = new GridBagLayout();
		MainView.panelRight.setLayout(prLayout);
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;  
        
        Insets insets = new Insets(10, 5, 10, 5);
        gbc.insets = insets;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        prLayout.setConstraints(scrollPane_1, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1; 
        gbc.weightx = 0;
        gbc.weighty = 0;
        prLayout.setConstraints(button_1, gbc);

        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        prLayout.setConstraints(button_2, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.weightx = 0.5;
        gbc.ipady = 10;
        prLayout.setConstraints(nameField, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.ipady = 0;
        prLayout.setConstraints(button_3, gbc);
        
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        prLayout.setConstraints(button_4, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.ipady = 40;
        gbc.weighty = 1.0;
        prLayout.setConstraints(scrollPane_2, gbc);
        
	}

	
	public static void initAccountList() throws Exception {
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
        		pk = account.pkString.substring(0,10);
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
        table = new JTable(tableModel);
        table.setBackground(Color.GRAY);
        table.setForeground(Color.WHITE);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // 获取选中行的数据
                        String name = table.getValueAt(selectedRow, 0).toString();
                        selectedUser = AccountManager.userAccounts.get(name);
                        
                        // 在控制台输出选中行的数据
                        System.out.println("Selected Row Data: " + name);
                        
                        // readOnlyField中显示 Name + PK
                        String content = "Name : " + name + "\n";
                        content = content + "Public Key : " + selectedUser.pkString;
                        readOnlyField.setText(content);
                    }
                }
            }
        });
        
        
        // 设置表头的背景色和前景色
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.DARK_GRAY);
        tableHeader.setForeground(SystemColor.activeCaption);
        // 创建滚动面板
        scrollPane_1 = new JScrollPane(table);
		// 设置 JViewport 的背景色
        JViewport viewport = scrollPane_1.getViewport();
        viewport.setBackground(Color.DARK_GRAY);
        

	}
	
	/**
	 * button_1 
	 * @throws Exception 
	 */
	public static void createUser(String userName) throws Exception {
		System.out.println(userName);
		AccountManager.userRegist(new UserAccount(userName, null, null));
		
		AccountManager.getAllUsers();
    	UserAccount account = AccountManager.userAccounts.get(userName);
    	String pk = "not exist",rk = "not exist";
  			
    	// public key
    	if(account.publicKey != null) {
    		pk = account.pkString.substring(0,10);
    		pk = pk + "***";
    	}
    	// private key
    	if(account.getPrivateKey() != null) {
    		rk = "*****";
    	}
    	
    	
		tableModel.addRow(new Vector<>(List.of(userName, pk, rk)));
		tableModel.fireTableDataChanged();
//		MainView.refreshPanelRight();
	}
	
	/**
	 * button_3
	 */
	public static void copyPublicKey() {
		copyToClipboard(selectedUser.pkString);
		readOnlyField.setText("== 已将 " + selectedUser.name + " 的公钥复制至剪切板 ==");
	}
	
	/**
	 * button_4
	 * @throws Exception 
	 */
	public static void regenerateKeyPair() throws Exception {
		deleteSelectedRow();
		String userName = selectedUser.name;
		createUser(userName);
		readOnlyField.setText("== 已重新生成身份 " + userName + " 的密钥对 ==");
	}
	
	/**
	 * button_2
	 * @throws Exception 
	 */
	public static void deleteAccount(String userName, int rowIndex) throws Exception {		
		
		AccountManager.deleteUserAccount(userName);
		
		if(UserSettings.deleteRealPEMFile == true) {
			
			// 删除文件
			String pkPath = FileUtil.FOLDER_FOR_KEYSTORE + "/pk/" + userName + ".pem";
			String rkPath = FileUtil.FOLDER_FOR_KEYSTORE + "/rk/" + userName + ".pem";
			
			FileUtil.deleteFile(pkPath);
			FileUtil.deleteFile(rkPath);
		}
		
		tableModel.removeRow(rowIndex);
		tableModel.fireTableDataChanged();
//		MainView.refreshPanelRight();
		
		readOnlyField.setText("== 已将身份 " + userName + " 删除 ==");
	}
	
	private static void copyToClipboard(String text) {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // 创建 StringSelection 对象，用于包装文本
        StringSelection stringSelection = new StringSelection(text);

        // 将 StringSelection 对象设置到剪贴板
        clipboard.setContents(stringSelection, null);
    }
	
	 private static void deleteSelectedRow() {
        // 获取选中的行索引
        int selectedRow = table.getSelectedRow();

        // 删除选中的行
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        }
	 }
	
}
