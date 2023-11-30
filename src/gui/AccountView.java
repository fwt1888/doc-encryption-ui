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
		
		// 读取信息
		AccountManager.getAllUsers();
		
		// 获取 Name List
		List<String> nameList = new ArrayList<>(AccountManager.userAccounts.keySet());
		
		// 创建数据模型
		listModel = new DefaultListModel<>();
        for (String item : nameList) {
            listModel.addElement(item);
        }
        
        // 创建 JList
        JList<String> jList = new JList<>(listModel);

        // 创建滚动面板
        JScrollPane scrollPane = new JScrollPane(jList);
        
        // 添加进 panelRight
        
        
	}

	
	public static void refreshAccountList() {
		
		
		
	}
	
	
}
