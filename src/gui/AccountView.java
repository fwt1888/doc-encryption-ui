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
	public static UserAccount selectedUser; // ���ѡ�е�user account
	public static int selectedRow;
	
	public static void init() throws Exception {
		
		MainView.panelRight.removeAll();
		
		// Account List
		initAccountList();
        
        // button : ��ݴ���
        JButton button_1 = new JButton("��ݴ���");
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.DARK_GRAY);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String userName = nameField.getText();
					createUser(userName);
					readOnlyField.setText("== ���½���� " + userName + " ==");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
        
        // button : ���ɾ��
		JButton button_2 = new JButton("���ɾ��");
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
		
        // button : ���ƹ�Կ
		JButton button_3 = new JButton("���ƹ�Կ");
		button_3.setForeground(Color.WHITE);
		button_3.setBackground(Color.DARK_GRAY);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyPublicKey();
			}
			
		});
        
        // button : ���²�����Կ��
		JButton button_4 = new JButton("���²�����Կ��");
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
		readOnlyField = new JTextArea("Ŀǰû��Ҫ��ʾ������Ŷ~");
        readOnlyField.setEditable(false);  // ����Ϊ���ɱ༭
        readOnlyField.setBackground(Color.GRAY);
        readOnlyField.setForeground(Color.WHITE);
        readOnlyField.setColumns(5); // �����������������
        readOnlyField.setLineWrap(true); // �����Զ�����
//        readOnlyField.setWrapStyleWord(true); // �����ʻ���
        
        nameField = new JTextField("�˴������½���ݵ�Name�ֶ�");
        nameField.setBackground(Color.GRAY);
        nameField.setForeground(Color.WHITE);
        
        scrollPane_2 = new JScrollPane(readOnlyField);
        
        // ��ӽ�panelRight
        MainView.panelRight.add(scrollPane_1);
        MainView.panelRight.add(button_1);
        MainView.panelRight.add(button_2);
        MainView.panelRight.add(button_3);
        MainView.panelRight.add(button_4);
        MainView.panelRight.add(nameField);
        MainView.panelRight.add(scrollPane_2);
        
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
		// ��ȡ��Ϣ
		AccountManager.getAllUsers();
		
		// ��ʼ���������
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Name");
        columnNames.add("Public Key");
        columnNames.add("Private Key");
        
        // ���Ͻ�JTable
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

        // ��������ģ��
        tableModel = new DefaultTableModel(rowData, columnNames);
        // �������
        table = new JTable(tableModel);
        table.setBackground(Color.GRAY);
        table.setForeground(Color.WHITE);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // ��ȡѡ���е�����
                        String name = table.getValueAt(selectedRow, 0).toString();
                        selectedUser = AccountManager.userAccounts.get(name);
                        
                        // �ڿ���̨���ѡ���е�����
                        System.out.println("Selected Row Data: " + name);
                        
                        // readOnlyField����ʾ Name + PK
                        String content = "Name : " + name + "\n";
                        content = content + "Public Key : " + selectedUser.pkString;
                        readOnlyField.setText(content);
                    }
                }
            }
        });
        
        
        // ���ñ�ͷ�ı���ɫ��ǰ��ɫ
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.DARK_GRAY);
        tableHeader.setForeground(SystemColor.activeCaption);
        // �����������
        scrollPane_1 = new JScrollPane(table);
		// ���� JViewport �ı���ɫ
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
		readOnlyField.setText("== �ѽ� " + selectedUser.name + " �Ĺ�Կ���������а� ==");
	}
	
	/**
	 * button_4
	 * @throws Exception 
	 */
	public static void regenerateKeyPair() throws Exception {
		deleteSelectedRow();
		String userName = selectedUser.name;
		createUser(userName);
		readOnlyField.setText("== ������������� " + userName + " ����Կ�� ==");
	}
	
	/**
	 * button_2
	 * @throws Exception 
	 */
	public static void deleteAccount(String userName, int rowIndex) throws Exception {		
		
		AccountManager.deleteUserAccount(userName);
		
		if(UserSettings.deleteRealPEMFile == true) {
			
			// ɾ���ļ�
			String pkPath = FileUtil.FOLDER_FOR_KEYSTORE + "/pk/" + userName + ".pem";
			String rkPath = FileUtil.FOLDER_FOR_KEYSTORE + "/rk/" + userName + ".pem";
			
			FileUtil.deleteFile(pkPath);
			FileUtil.deleteFile(rkPath);
		}
		
		tableModel.removeRow(rowIndex);
		tableModel.fireTableDataChanged();
//		MainView.refreshPanelRight();
		
		readOnlyField.setText("== �ѽ���� " + userName + " ɾ�� ==");
	}
	
	private static void copyToClipboard(String text) {
        // ��ȡϵͳ������
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // ���� StringSelection �������ڰ�װ�ı�
        StringSelection stringSelection = new StringSelection(text);

        // �� StringSelection �������õ�������
        clipboard.setContents(stringSelection, null);
    }
	
	 private static void deleteSelectedRow() {
        // ��ȡѡ�е�������
        int selectedRow = table.getSelectedRow();

        // ɾ��ѡ�е���
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        }
	 }
	
}
