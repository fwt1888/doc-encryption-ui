package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import core.utils.AccountManager;

public class AccountView {
	
	private static DefaultListModel<String> listModel;
	
	public static void initAccountList() throws Exception {
		
		// ��ȡ��Ϣ
		AccountManager.getAllUsers();
		
		// ��ȡ Name List
		List<String> nameList = new ArrayList<>(AccountManager.userAccounts.keySet());
		
		// ��������ģ��
		listModel = new DefaultListModel<>();
        for (String item : nameList) {
            listModel.addElement(item);
        }
        
        // ���� JList
        JList<String> jList = new JList<>(listModel);

        // �����������
        JScrollPane scrollPane = new JScrollPane(jList);
        
        // ��ӽ� panelRight
        
        
	}

	
	public static void refreshAccountList() {
		
		
		
	}
	
	
}
