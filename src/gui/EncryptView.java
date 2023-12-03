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
	public static JButton button_1; // ��Ϊsender
	public static JButton button_2; // ��Ϊreceiver

//	public static JTextField tf_1; // ������
	public static JLabel lb_1;
//	public static JTextField tf_2; // ������
	public static JLabel lb_2;
	
	public static JButton button_3; // �ַ�������
	public static JTextArea ta_1; // �����ַ���
	public static JButton button_4; // �ļ�����
	
	public static JFileChooser fc; // �ļ�ѡ��
	public static JTextField tf_3;
	public static JButton button_5;
	
//	public static JTextArea ta_2; // ��ʾ��Ϣ
	
	public static JScrollPane sp_1;
	public static JScrollPane sp_2;
	
	public static ActionListener al_3;
	public static ActionListener al_4;
	
	private static JFrame frame;
	
	public static void init() throws Exception {
		MainView.panelRight.removeAll();
		
		// Account List
		AccountView.initAccountList();
		
		// ��ǰ���ܲ���
		strategy = new JTextArea("=== ��ǰ���ܲ��� ===\n");
		strategy.append("�ԳƼ����㷨: " + UserSettings.symAlgo + "\n");
		if (UserSettings.symKey.equals("seed")) {
			strategy.append("�ԳƼ�����Կ: " + "�������Ӳ���\n");
		}else {
			strategy.append("�ԳƼ�����Կ: " + UserSettings.symKey + "\n");
		}
		strategy.append("Hash�㷨: " + UserSettings.hashAlgo);
		strategy.setBackground(Color.GRAY);
        strategy.setForeground(Color.WHITE);
        sp_2 = new JScrollPane(strategy);
		
		// button_1 ��Ϊsender
		button_1 = new JButton("��Ϊ Sender"); 
		button_1.setBackground(Color.DARK_GRAY);
        button_1.setForeground(Color.WHITE);
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSender();
            }
        });
		
		// button_2 ��Ϊreceiver
		button_2 = new JButton("��Ϊ Receiver");
		button_2.setBackground(Color.DARK_GRAY);
        button_2.setForeground(Color.WHITE);
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReceiver();
            }
        });
		
		// textfield 1&2
		lb_1 = new JLabel("Sender: û��ѡ��");
        lb_1.setForeground(Color.WHITE);
//		tf_1 = new JTextField("û��ѡ��"); // ������
//		tf_1.setBackground(Color.GRAY);
//        tf_1.setForeground(Color.WHITE);
		
		lb_2 = new JLabel("Receiver: û��ѡ��");
        lb_2.setForeground(Color.WHITE);
//		tf_2 = new JTextField("û��ѡ��"); 
//		tf_2.setBackground(Color.GRAY);
//        tf_2.setForeground(Color.WHITE);
		
		// �ַ�������
		button_3 = new JButton("�ַ�������"); // �ַ�������
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
        
		ta_1 = new JTextArea("�˴���������ܵ��ַ���~"); // �����ַ���
		ta_1.setBackground(Color.GRAY);
        ta_1.setForeground(Color.WHITE);
        ta_1.setColumns(5); // �����������������
        ta_1.setLineWrap(true); // �����Զ�����
        
		sp_1 = new JScrollPane(ta_1);
		
		// �ļ�����
		button_4 = new JButton("�ļ�����"); // �ļ�����
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
        
		tf_3 = new JTextField("ѡ���ļ�");
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
        
        // �����ļ�ѡ����
        fc = new JFileChooser();
        
        frame = new JFrame("File Chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ��ʾ��Ϣ
		AccountView.readOnlyField = new JTextArea("Ŀǰû��Ҫ��ʾ������Ŷ~");
		AccountView.readOnlyField.setEditable(false);  // ����Ϊ���ɱ༭
		AccountView.readOnlyField.setBackground(Color.GRAY);
		AccountView.readOnlyField.setForeground(Color.WHITE);
		AccountView.readOnlyField.setColumns(5); // �����������������
		AccountView.readOnlyField.setLineWrap(true); // �����Զ�����
        
		AccountView.scrollPane_2 = new JScrollPane(AccountView.readOnlyField);
		
		// ��ӽ�panelRight
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
        
        // ����
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

        // ��ʾ�ļ�ѡ��Ի���
        int result = fc.showOpenDialog(frame);

        // �����û���ѡ��
        if (result == JFileChooser.APPROVE_OPTION) {
            // �û�����ˡ��򿪡���ť
            String selectedFilePath = fc.getSelectedFile().getAbsolutePath();
            tf_3.setText(selectedFilePath);
        } else if (result == JFileChooser.CANCEL_OPTION) {
            // �û�����ˡ�ȡ������ť
            tf_3.setText("ѡ������ļ�"); // ����ı���
        } else if (result == JFileChooser.ERROR_OPTION) {
            // ��������
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
		AccountView.readOnlyField.setText("�ַ������ܳɹ�~\nHEX: " + encS);
	}
	
	public static void fileEncrypt() throws Exception {
		String oriFile = tf_3.getText();
		String encFile = DataEncrypt.encrypt(oriFile);
		AccountView.readOnlyField.setText("�ļ����ܳɹ�~\n���ܺ���ļ�λ��: " + encFile);
	}


}
