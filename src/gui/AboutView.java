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
		
		lb_1 = new JLabel("== 功能简介 ==");
		lb_1.setForeground(SystemColor.activeCaption);
		ta = new JTextArea("DOC_ENCRYPTION_UI 模拟了多个用户之间数据传输的场景，" +
		"可以自定义设置身份（接收方或发送方），最终实现了字符串、文件的签名加密过程。\n" +
		"\n== 身份管理 == \n每一个身份绑定一对RSA公私钥。若接收方需要对数据进行解密，发送方仅提供公钥对应的.pem文件即可。\n" + 
		"\n== 数据加密/解密 == \n可选的对称加密算法包括DES、AES；对称加密密钥可自定义提供，或者由程序自动根据种子生成；Hash算法包括SHA、MD5。" + 
		"（上述功能均可通过高级设置进行更改)\n" + 
		"\n== 程序中所用的图片素材均来源于freepik ==\n" +
		"\n== 访问下方的在线文档以获取其他信息 ==\n");
        ta.setColumns(5); // 设置列数，决定宽度
        ta.setLineWrap(true); // 启用自动换行
		ta.setForeground(Color.WHITE);
		ta.setBackground(Color.DARK_GRAY);
		ta.setEditable(false);
		sp = new JScrollPane(ta);
		
		lb_2 = new JLabel("在线文档: ");
		lb_2.setForeground(SystemColor.activeCaption);
		
		linkLabel_1 = new JLabel(url_1);
		linkLabel_1.setForeground(Color.WHITE);
		linkLabel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // 打开链接
                    Desktop.getDesktop().browse(new URI(url_1));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
		
		lb_3 = new JLabel("代码仓库: ");
		lb_3.setForeground(SystemColor.activeCaption);
		
		linkLabel_2 = new JLabel(url_2);
		linkLabel_2.setForeground(Color.WHITE);
		linkLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // 打开链接
                    Desktop.getDesktop().browse(new URI(url_2));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
		
		lb_4 = new JLabel("联系作者: ");
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
		
		 // 布局
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
