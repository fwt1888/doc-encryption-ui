package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import core.DataDecrypt;
import core.DataEncrypt;
import core.utils.UserSettings;

public class EncryptView {
	
	public static JTextArea strategy;
	public static JButton button_1; // 设为sender
	public static JButton button_2; // 设为receiver

//	public static JTextField tf_1; // 发送者
	public static JLabel lb_1;
//	public static JTextField tf_2; // 接收者
	public static JLabel lb_2;
	
	public static JButton button_3; // 字符串加密
	public static JTextArea ta_1; // 输入字符串
	public static JButton button_4; // 文件加密
	
	public static JFileChooser fc; // 文件选择
	public static JTextField tf_3;
	public static JButton button_5;
	
//	public static JTextArea ta_2; // 提示信息
	
	public static JScrollPane sp_1;
	public static JScrollPane sp_2;
	
	public static ActionListener al_3;
	public static ActionListener al_4;
	
	private static JFrame frame;
	
	public static void init() throws Exception {
		MainView.panelRight.removeAll();
		
		// Account List
		AccountView.initAccountList();
		
		// 当前加密策略
		strategy = new JTextArea("=== 当前加密策略 ===\n");
		strategy.append("对称加密算法: " + UserSettings.symAlgo + "\n");
		if (UserSettings.symKey.equals("seed")) {
			strategy.append("对称加密密钥: " + "根据种子产生\n");
		}else {
			strategy.append("对称加密密钥: " + UserSettings.symKey + "\n");
		}
		strategy.append("Hash算法: " + UserSettings.hashAlgo);
		strategy.setBackground(Color.GRAY);
        strategy.setForeground(Color.WHITE);
        sp_2 = new JScrollPane(strategy);
		
		// button_1 设为sender
		button_1 = new JButton("设为 Sender"); 
		button_1.setBackground(Color.DARK_GRAY);
        button_1.setForeground(Color.WHITE);
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSender();
            }
        });
		
		// button_2 设为receiver
		button_2 = new JButton("设为 Receiver");
		button_2.setBackground(Color.DARK_GRAY);
        button_2.setForeground(Color.WHITE);
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReceiver();
            }
        });
		
		// textfield 1&2
		lb_1 = new JLabel("Sender: 没有选定");
        lb_1.setForeground(Color.WHITE);
//		tf_1 = new JTextField("没有选定"); // 发送者
//		tf_1.setBackground(Color.GRAY);
//        tf_1.setForeground(Color.WHITE);
		
		lb_2 = new JLabel("Receiver: 没有选定");
        lb_2.setForeground(Color.WHITE);
//		tf_2 = new JTextField("没有选定"); 
//		tf_2.setBackground(Color.GRAY);
//        tf_2.setForeground(Color.WHITE);
		
		// 字符串加密
		button_3 = new JButton("字符串加密"); // 字符串加密
		button_3.setBackground(Color.DARK_GRAY);
        button_3.setForeground(Color.WHITE);
        button_3.addActionListener(al_3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					stringEncrypt();
					System.out.println("String Encryption");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
		ta_1 = new JTextArea("此处输入待加密的字符串~"); // 输入字符串
		ta_1.setBackground(Color.GRAY);
        ta_1.setForeground(Color.WHITE);
        ta_1.setColumns(5); // 设置列数，决定宽度
        ta_1.setLineWrap(true); // 启用自动换行
        
		sp_1 = new JScrollPane(ta_1);
		
		// 文件加密
		button_4 = new JButton("文件加密"); // 文件加密
		button_4.setBackground(Color.DARK_GRAY);
        button_4.setForeground(Color.WHITE);
        button_4.addActionListener(al_4 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					fileEncrypt();
					System.out.println("File Encryption");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
		tf_3 = new JTextField("选择文件");
		tf_3.setBackground(Color.GRAY);
        tf_3.setForeground(Color.WHITE);
		button_5 = new JButton("Open");
		button_5.setBackground(Color.DARK_GRAY);
        button_5.setForeground(Color.WHITE);
        button_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFileChooser();
            }
        });
        
        // 创建文件选择器
        fc = new JFileChooser();
        
        frame = new JFrame("File Chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 提示信息
		AccountView.readOnlyField = new JTextArea("目前没有要显示的内容哦~");
		AccountView.readOnlyField.setEditable(false);  // 设置为不可编辑
		AccountView.readOnlyField.setBackground(Color.GRAY);
		AccountView.readOnlyField.setForeground(Color.WHITE);
		AccountView.readOnlyField.setColumns(5); // 设置列数，决定宽度
		AccountView.readOnlyField.setLineWrap(true); // 启用自动换行
        
		AccountView.scrollPane_2 = new JScrollPane(AccountView.readOnlyField);
		
		// 添加进panelRight
        MainView.panelRight.add(AccountView.scrollPane_1);
        
        MainView.panelRight.add(sp_2);
        MainView.panelRight.add(button_1);
        MainView.panelRight.add(button_2);
        
        MainView.panelRight.add(lb_1);
//        MainView.panelRight.add(tf_1);

        MainView.panelRight.add(lb_2);
//        MainView.panelRight.add(tf_2);

        MainView.panelRight.add(button_3);
        MainView.panelRight.add(sp_1);
        MainView.panelRight.add(button_4);
        
        MainView.panelRight.add(tf_3);
        MainView.panelRight.add(button_5);
        MainView.panelRight.add(AccountView.scrollPane_2);
        
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
        prLayout.setConstraints(AccountView.scrollPane_1, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 2;
        gbc.weightx = 0.2;
        gbc.weighty = 0;
        gbc.ipady = 55;
        prLayout.setConstraints(sp_2, gbc);
        
        gbc.gridx = 9;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.ipady = 0;
        prLayout.setConstraints(button_1, gbc);
        
        gbc.gridx = 9;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(button_2, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_1, gbc);
        
//        gbc.gridx = 6;
//        gbc.gridy = 2;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
//        gbc.ipadx = 110;
//        prLayout.setConstraints(tf_1, gbc);
        
        gbc.gridx = 7;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
//        gbc.ipadx = 0;
        prLayout.setConstraints(lb_2, gbc); 
        
//        gbc.gridx = 8;
//        gbc.gridy = 2;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
//        prLayout.setConstraints(tf_2, gbc); 
        
        gbc.gridx = 5;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(button_3, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.ipady = 30;
        prLayout.setConstraints(sp_1, gbc); 
        
        gbc.gridx = 5;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipady = 0;
        prLayout.setConstraints(button_4, gbc); 
        
        gbc.gridx = 6;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        prLayout.setConstraints(tf_3, gbc); 
        
        gbc.gridx = 9;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(button_5, gbc); 
        
        gbc.gridx = 5;
        gbc.gridy = 6;
        gbc.gridwidth = 5;
        gbc.gridheight = 3;
        gbc.weightx = 0;
        gbc.weighty = 0;
        prLayout.setConstraints(AccountView.scrollPane_2, gbc); 
		
	}
	
	private static void openFileChooser() {

        // 显示文件选择对话框
        int result = fc.showOpenDialog(frame);

        // 处理用户的选择
        if (result == JFileChooser.APPROVE_OPTION) {
            // 用户点击了“打开”按钮
            String selectedFilePath = fc.getSelectedFile().getAbsolutePath();
            tf_3.setText(selectedFilePath);
        } else if (result == JFileChooser.CANCEL_OPTION) {
            // 用户点击了“取消”按钮
            tf_3.setText("选择加密文件"); // 清空文本框
        } else if (result == JFileChooser.ERROR_OPTION) {
            // 发生错误
            JOptionPane.showMessageDialog(frame, "Error occurred during file selection.");
        }
    }
	
	public static void setSender() {
		DataEncrypt.sender = AccountView.selectedUser;
		DataDecrypt.sender = AccountView.selectedUser;
		lb_1.setText("Sender: " + DataEncrypt.sender.name);
	}
	
	public static void setReceiver() {
		DataEncrypt.receiver = AccountView.selectedUser;
		DataDecrypt.receiver = AccountView.selectedUser;
		lb_2.setText("Recevier: " + DataEncrypt.receiver.name);
	}
	
	public static void stringEncrypt() throws Exception {
		String s = ta_1.getText();
		String encS = DataEncrypt.stringEncrypt(s);
		AccountView.readOnlyField.setText("字符串加密成功~\nHEX: " + encS);
	}
	
	public static void fileEncrypt() throws Exception {
		String oriFile = tf_3.getText();
		String encFile = DataEncrypt.encrypt(oriFile);
		AccountView.readOnlyField.setText("文件加密成功~\n加密后的文件位置: " + encFile);
	}


}
