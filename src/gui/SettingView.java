package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import core.utils.FileUtil;
import core.utils.UserSettings;

public class SettingView {
	
	public static JLabel lb_1, lb_2, lb_3, lb_4, lb_5, lb_6, lb_7;
	public static JComboBox<String> cb_1, cb_2, cb_3, cb_4;
	public static JTextArea ta;
	public static JTextField tf;
	public static JButton button_1, button_2;
	public static JScrollPane sp;
	
	public static JFrame frame;
	public static JFileChooser fc;
	
	public static void init() {
		MainView.panelRight.removeAll();
		
		frame = new JFrame("File Chooser");
		fc = new JFileChooser();
		
		lb_1 = new JLabel("== ȫ�ּ��ܲ��� ==");
		lb_1.setForeground(SystemColor.activeCaption);
//		lb_1.setOpaque(true); // ����Ϊ��͸��
		
		lb_2 = new JLabel("�ԳƼ����㷨");
		lb_2.setForeground(Color.WHITE);
		cb_1 = new JComboBox<>();
		cb_1.setForeground(Color.WHITE);
		cb_1.setBackground(Color.GRAY);
        cb_1.addItem("DES");
        cb_1.addItem("AES");

		lb_3 = new JLabel("�ԳƼ�����Կ");
		lb_3.setForeground(Color.WHITE);
		cb_2 = new JComboBox<>();
		cb_2.setBackground(Color.GRAY);
		cb_2.setForeground(Color.WHITE);
        cb_2.addItem("ÿ�θ��������������");
        cb_2.addItem("�ṩ�Զ�����Կ");
        
		ta = new JTextArea("�˴�������ѡ�ĶԳƼ�����Կ(HEX)~\n(ע�⣺��Ҫʹ��ͬһ��Կ���ܴ����ļ�Ŷ)");
		ta.setForeground(Color.WHITE);
        cb_2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                // ��ȡѡ�����
                String selectedOption = (String) cb_2.getSelectedItem();

                // ����ѡ����������ı�����Ŀɱ༭״̬
                if ("�ṩ�Զ�����Կ".equals(selectedOption)) {
                    ta.setEditable(true);
                    System.out.println("Text Area is Editable");
                } else if ("ÿ�θ��������������".equals(selectedOption)) {
                    ta.setEditable(false);
                    System.out.println("Text Area is not Editable");
                }
            }
        });
		ta.setBackground(Color.GRAY);
		ta.setEditable(false);
		sp = new JScrollPane(ta);
		
		lb_4 = new JLabel("Hash�㷨");
		lb_4.setForeground(Color.WHITE);
		cb_3 = new JComboBox<>();
		cb_3.setForeground(Color.WHITE);
		cb_3.setBackground(Color.GRAY);
		cb_3.addItem("SHA-1");
		cb_3.addItem("MD5");
		cb_3.addItem("SHA3-256");
		cb_3.addItem("SHA3-384");
		cb_3.addItem("SHA3-512");
		
		lb_5 = new JLabel("== ��Կ����ʽ ==");
		lb_5.setForeground(SystemColor.activeCaption);
//		lb_5.setOpaque(true); // ����Ϊ��͸��
		
		lb_6 = new JLabel("��Կ�洢λ��");
		lb_6.setForeground(Color.WHITE);
		tf = new JTextField(FileUtil.FOLDER_FOR_KEYSTORE);
		tf.setBackground(Color.GRAY);
		tf.setForeground(Color.WHITE);
		button_1 = new JButton("Open");
		button_1.setBackground(Color.DARK_GRAY);
		button_1.setForeground(Color.WHITE);
		button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					openFileChooser();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
		
		lb_7 = new JLabel("ɾ�����ʱ���Ƿ�ͬʱɾ�����ش����д洢��.pem�ļ�");
		lb_7.setForeground(Color.WHITE);
		cb_4 = new JComboBox<>();
		cb_4.setForeground(Color.WHITE);
		cb_4.addItem("��");
		cb_4.addItem("��");
		cb_4.setBackground(Color.GRAY);
		
		button_2 = new JButton("��������");
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(Color.DARK_GRAY);
		button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					saveSettings();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
		
		MainView.panelRight.add(lb_1);
		MainView.panelRight.add(lb_2);
		MainView.panelRight.add(cb_1);
		
		MainView.panelRight.add(lb_3);
		MainView.panelRight.add(cb_2);
		MainView.panelRight.add(sp);
		
		MainView.panelRight.add(lb_4);
		MainView.panelRight.add(cb_3);
		
		MainView.panelRight.add(lb_5);
		MainView.panelRight.add(lb_6);		
		MainView.panelRight.add(tf);
		MainView.panelRight.add(button_1);
		
		MainView.panelRight.add(lb_7);
		MainView.panelRight.add(cb_4);
		MainView.panelRight.add(button_2);
		
        // ����
		GridBagLayout prLayout = new GridBagLayout();
		MainView.panelRight.setLayout(prLayout);
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;  
        
        Insets insets = new Insets(10, 5, 10, 5);
        gbc.insets = insets;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_1, gbc);
		
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_2, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(cb_1, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_3, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(cb_2, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        prLayout.setConstraints(sp, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        prLayout.setConstraints(lb_4, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(cb_3, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_5, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_6, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        prLayout.setConstraints(tf, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(button_1, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_7, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(cb_4, gbc);
        
        gbc.gridx = GridBagConstraints.WEST;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(button_2, gbc);
        
        
		readSettings();
	}
	
	private static void openFileChooser() {

        // ��ʾ�ļ�ѡ��Ի���
        int result = fc.showOpenDialog(frame);

        // �����û���ѡ��
        if (result == JFileChooser.APPROVE_OPTION) {
            // �û�����ˡ��򿪡���ť
            String selectedFilePath = fc.getSelectedFile().getAbsolutePath();
            tf.setText(selectedFilePath);
        } else if (result == JFileChooser.CANCEL_OPTION) {
            // �û�����ˡ�ȡ������ť
            tf.setText(FileUtil.FOLDER_FOR_KEYSTORE); // ����ı���
        } else if (result == JFileChooser.ERROR_OPTION) {
            // ��������
            JOptionPane.showMessageDialog(frame, "Error occurred during file selection.");
        }
    }
	
	/**
	 * view�����ñ�����UseSettings
	 */
	public static void saveSettings() {
		
		// ��ȡview������
		// cb_1
		UserSettings.symAlgo = (String) cb_1.getSelectedItem();
		// cb_2
		String selectedOption = (String) cb_2.getSelectedItem();
		if("�ṩ�Զ�����Կ".equals(selectedOption)) {
			UserSettings.symKey = "provided";
			UserSettings.providedSymKey = ta.getText();
		}else if("ÿ�θ��������������".equals(selectedOption)){
			UserSettings.symKey = "seed";
			UserSettings.providedSymKey = null;
		}
		// cb_3
		UserSettings.hashAlgo = (String) cb_3.getSelectedItem();
		// tf
		UserSettings.keyStorePath = tf.getText();
		// cb_4
		selectedOption = (String) cb_4.getSelectedItem();
		if("��".equals(selectedOption)) {
			UserSettings.ifDeleteRealFile = "y";
		}else if("ÿ�θ��������������".equals(selectedOption)){
			UserSettings.ifDeleteRealFile = "n";
		}	
		
		// �������ļ� & ��������
		UserSettings.saveToFile();
		UserSettings.changeSettings();
	}

	/** 
	 * ��ʾUserSettings��view��
	 */
	public static void readSettings() {
		
		// ��ȡ�ļ�
		UserSettings.readFromFile();
		
		// ���������Ϣ
		// cb_1
		cb_1.setSelectedItem(UserSettings.symAlgo);
		// cb_2
		if("seed".equals(UserSettings.symKey)) {
			cb_2.setSelectedItem("ÿ�θ��������������");
			ta.setText("�˴�������ѡ�ĶԳƼ�����Կ(HEX)~\n(ע�⣺��Ҫʹ��ͬһ��Կ���ܴ����ļ�Ŷ)");
		}else if ("provided".equals(UserSettings.symKey)) {
			cb_2.setSelectedItem("�ṩ�Զ�����Կ");
			ta.setText("��ѡ����Կ(HEX): " + UserSettings.providedSymKey);
		}

		// cb_3
		cb_3.setSelectedItem(UserSettings.hashAlgo);
		// tf
		tf.setText(UserSettings.keyStorePath);
		// cb_4
		if("y".equals(UserSettings.ifDeleteRealFile)) {
			cb_4.setSelectedItem("��");
		}else if ("n".equals(UserSettings.ifDeleteRealFile)){
			cb_4.setSelectedItem("��");
		}		
	}

}
