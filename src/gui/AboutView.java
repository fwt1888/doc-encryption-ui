package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class AboutView {
	
	public static JLabel lb_1, lb_2, lb_3, lb_4, lb_5, lb_6, lb_7, lb_8;
	public static JTextArea ta;
	public static JLabel linkLabel_1,linkLabel_2;
	
	private static int icon_width = 30;
	private static int icon_height = 30;
	public static JScrollPane sp;
	
	public static String url_2 = "https://github.com/fwt1888/doc-encryption-ui";
	public static String url_1 = "https://www.yuque.com/fwt1888/doc_encryption_ui";
	
	public static void init() {
		MainView.panelRight.removeAll();
		
		lb_1 = new JLabel("== ���ܼ�� ==");
		lb_1.setForeground(SystemColor.activeCaption);
		ta = new JTextArea("DOC_ENCRYPTION_UI ģ���˶���û�֮�����ݴ���ĳ�����" +
		"�����Զ���������ݣ����շ����ͷ���������ʵ�����ַ������ļ���ǩ�����ܹ��̡�\n" +
		"\n== ��ݹ��� == \nÿһ����ݰ�һ��RSA��˽Կ�������շ���Ҫ�����ݽ��н��ܣ����ͷ����ṩ��Կ��Ӧ��.pem�ļ����ɡ�\n" + 
		"\n== ���ݼ���/���� == \n��ѡ�ĶԳƼ����㷨����DES��AES���ԳƼ�����Կ���Զ����ṩ�������ɳ����Զ������������ɣ�Hash�㷨����SHA��MD5��" + 
		"���������ܾ���ͨ���߼����ý��и���)\n" + 
		"\n== ���������õ�ͼƬ�زľ���Դ��freepik ==\n" +
		"\n== �����·��������ĵ��Ի�ȡ������Ϣ ==\n");
        ta.setColumns(5); // �����������������
        ta.setLineWrap(true); // �����Զ�����
		ta.setForeground(Color.WHITE);
		ta.setBackground(Color.DARK_GRAY);
		ta.setEditable(false);
		sp = new JScrollPane(ta);
		
		lb_2 = new JLabel("�����ĵ�: ");
		lb_2.setForeground(SystemColor.activeCaption);
		
		linkLabel_1 = new JLabel(url_1);
		linkLabel_1.setForeground(Color.WHITE);
		linkLabel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // ������
                    Desktop.getDesktop().browse(new URI(url_1));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
		
		lb_3 = new JLabel("����ֿ�: ");
		lb_3.setForeground(SystemColor.activeCaption);
		
		linkLabel_2 = new JLabel(url_2);
		linkLabel_2.setForeground(Color.WHITE);
		linkLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // ������
                    Desktop.getDesktop().browse(new URI(url_2));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
		
		lb_4 = new JLabel("��ϵ����: ");
		lb_4.setForeground(SystemColor.activeCaption);
		
		lb_5 = new JLabel("1439103711@qq.com");
		lb_5.setForeground(SystemColor.activeCaption);
		
		lb_6 = new JLabel();
		lb_6.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image_1 = new ImageIcon(MainView.class.getResource("/gui/resources/donut_12751656.png"));
		image_1.setImage(image_1.getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_DEFAULT ));
		lb_6.setIcon(image_1);
		
		lb_7 = new JLabel();
		lb_7.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image_2 = new ImageIcon(MainView.class.getResource("/gui/resources/fish_411041.png"));
		image_2.setImage(image_2.getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_DEFAULT ));
		lb_7.setIcon(image_2);
		
		lb_8 = new JLabel();
		lb_8.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image_3 = new ImageIcon(MainView.class.getResource("/gui/resources/naughty_5127055.png"));
		image_3.setImage(image_3.getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_DEFAULT ));
		lb_8.setIcon(image_3);
		
		MainView.panelRight.add(lb_1);
		MainView.panelRight.add(sp);
		MainView.panelRight.add(lb_6);
		MainView.panelRight.add(lb_2);
		MainView.panelRight.add(linkLabel_1);
		MainView.panelRight.add(lb_7);
		MainView.panelRight.add(lb_3);
		MainView.panelRight.add(linkLabel_2);
		MainView.panelRight.add(lb_8);
		MainView.panelRight.add(lb_4);
		MainView.panelRight.add(lb_5);
		MainView.panelRight.add(lb_6);
		
		 // ����
		GridBagLayout prLayout = new GridBagLayout();
		MainView.panelRight.setLayout(prLayout);
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;  
        
        Insets insets = new Insets(5, 5, 5, 5);
        gbc.insets = insets;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_1, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        prLayout.setConstraints(sp, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        prLayout.setConstraints(lb_6, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_2, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(linkLabel_1, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_7, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_3, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(linkLabel_2, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_8, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_4, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        prLayout.setConstraints(lb_5, gbc);
	}

}
