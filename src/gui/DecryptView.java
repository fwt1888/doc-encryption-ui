package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import core.DataDecrypt;
import core.DataEncrypt;
import core.utils.ByteUtil;
import core.utils.StringUtil;

public class DecryptView {
	
	public static void init() throws Exception {
		EncryptView.init();
		EncryptView.button_3.setText("�ַ�������");
		EncryptView.button_4.setText("�ļ�����");
		EncryptView.ta_1.setText("�˴���������ܵ��ַ���(HEX)~");
		EncryptView.tf_3.setText("ѡ������ļ�"); 
//		EncryptView.fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		EncryptView.button_3.removeActionListener(EncryptView.al_3);
		EncryptView.button_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					stringDecrypt();
					System.out.println("String Decryption");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }


        });
		
		EncryptView.button_4.removeActionListener(EncryptView.al_4);
        EncryptView.button_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					fileDecrypt();
					System.out.println("File Decryption");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }


        });
		
	}
	
	public static void stringDecrypt() throws Exception {
		String s = EncryptView.ta_1.getText();
		String decS = DataDecrypt.stringDecrypt(s);
		AccountView.readOnlyField.setText("�ַ������ܳɹ�~\nHEX: " + decS
				+ "\n����: " + StringUtil.byteArrayToString(ByteUtil.hexStringToByteArray(decS)));
		
	}
	
	private static void fileDecrypt() {
		String oriFile = EncryptView.tf_3.getText();
		String decFile = DataDecrypt.decrypt(oriFile);
		AccountView.readOnlyField.setText("�ļ����ܳɹ�~\n���ܺ���ļ�λ��: " + decFile);
	}

}
