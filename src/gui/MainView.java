package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import core.utils.UserSettings;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;


public class MainView {
	
	private static int icon_width = 30;
	private static int icon_height = 30;

	private static JFrame frame;
	
	// 右边可变区域
	public static JPanel panelRight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingUtilities.invokeLater(() -> {
						setGlobalFontSize(13);
//						setGlobalTextColor(Color.WHITE);
						UserSettings.readFromFile();
						UserSettings.changeSettings();
						MainView window = new MainView();
						window.frame.setVisible(true);	
					});	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("DOC_ENCRYPTION_UI");
		frame.setBounds(150, 150, 1000, 500);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		// 设置 GridBag
		GridBagLayout frameLayout = new GridBagLayout();
        frame.getContentPane().setLayout(frameLayout);
		
        // 左右布局
		JPanel panelLeft = new JPanel();
		GridBagLayout plLayout = new GridBagLayout();
		panelLeft.setBackground(Color.DARK_GRAY);
		panelLeft.setLayout(plLayout);
		
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setPreferredSize(new Dimension(5, Integer.MAX_VALUE));
        separator.setForeground(Color.GRAY);
        separator.setBackground(SystemColor.activeCaption);
		
		panelRight = new JPanel();
		panelRight.setBackground(Color.DARK_GRAY);

		// 左边选项卡
		// 选项1: 身份管理
		JLabel iconLabel_1 = new JLabel("");
		iconLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image1 = new ImageIcon(MainView.class.getResource("/gui/resources/muffin_411035.png"));
		image1.setImage(image1.getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_DEFAULT ));
		iconLabel_1.setIcon(image1);
		
		JButton button_1 = new JButton("身份管理");
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.DARK_GRAY);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AccountView.init();
					refreshFrame();
					System.out.println("== account view ==");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// 选项2: 数据加密
		JLabel iconLabel_2 = new JLabel("");
		iconLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image2 = new ImageIcon(MainView.class.getResource("/gui/resources/key_807241.png"));
		image2.setImage(image2.getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_DEFAULT ));
		iconLabel_2.setIcon(image2);
		
		JButton button_2 = new JButton("数据加密");
		button_2.setBackground(Color.DARK_GRAY);
		button_2.setForeground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EncryptView.init();
					refreshFrame();
					System.out.println("== encrypt view ==");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// 选项3: 数据解密
		JLabel iconLabel_3 = new JLabel("");
		iconLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image3 = new ImageIcon(MainView.class.getResource("/gui/resources/pixels_358909.png"));
		image3.setImage(image3.getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_DEFAULT ));
		iconLabel_3.setIcon(image3);
		
		JButton button_3 = new JButton("数据解密");
		button_3.setBackground(Color.DARK_GRAY);
		button_3.setForeground(Color.WHITE);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DecryptView.init();
					refreshFrame();
					System.out.println("== decrypt view ==");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// 选项4: 高级设置
		JLabel iconLabel_4 = new JLabel("");
		iconLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image4 = new ImageIcon(MainView.class.getResource("/gui/resources/game_10125801.png"));
		image4.setImage(image4.getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_DEFAULT ));
		iconLabel_4.setIcon(image4);
		
		JButton button_4 = new JButton("高级设置");
		button_4.setIcon(null);
		button_4.setBackground(Color.DARK_GRAY);
		button_4.setForeground(Color.WHITE);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SettingView.init();
					refreshFrame();
					System.out.println("== setting view ==");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// 选项5: 关于
		JLabel iconLabel_5 = new JLabel("");
		iconLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image5 = new ImageIcon(MainView.class.getResource("/gui/resources/bisexual_8236845.png"));
		image5.setImage(image5.getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_DEFAULT ));
		iconLabel_5.setIcon(image5);
		
		JButton button_5 = new JButton("关于");
		button_5.setSelected(true);
		button_5.setIcon(null);
		button_5.setBackground(Color.DARK_GRAY);
		button_5.setForeground(Color.WHITE);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutView.init();
				refreshFrame();
				System.out.println("== about view ==");
			}
		});
		
		// 所有组件添加进jFrame
		frame.getContentPane().add(panelLeft); 
		frame.getContentPane().add(separator);
		frame.getContentPane().add(panelRight);
		panelLeft.add(iconLabel_1); panelLeft.add(button_1);
		panelLeft.add(iconLabel_2); panelLeft.add(button_2);
		panelLeft.add(iconLabel_3); panelLeft.add(button_3);
		panelLeft.add(iconLabel_4); panelLeft.add(button_4);
		panelLeft.add(iconLabel_5); panelLeft.add(button_5);
		
//		// test panel right
//		JButton test = new JButton("test");
//		panelRight.add(test);

		
		// 设置GridBag布局
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;  

        // panel
        Insets insets = new Insets(20, 10, 20, 10);
        gbc.insets = insets;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        frameLayout.setConstraints(panelLeft, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        frameLayout.setConstraints(separator, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        frameLayout.setConstraints(panelRight, gbc);
        
        // 选项1
        insets = new Insets(20, 5, 20, 5); //上、左、下、右
        gbc.insets = insets;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight =1;
        plLayout.setConstraints(iconLabel_1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight =1;
        plLayout.setConstraints(button_1, gbc);
        
        // 选项2
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight =1;
        plLayout.setConstraints(iconLabel_2, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight =1;
        plLayout.setConstraints(button_2, gbc);
        
        // 选项3
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight =1;
        plLayout.setConstraints(iconLabel_3, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight =1;
        plLayout.setConstraints(button_3, gbc);
        
        // 选项4
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight =1;
        plLayout.setConstraints(iconLabel_4, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight =1;
        plLayout.setConstraints(button_4, gbc);
        
        // 选项5
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight =1;
        plLayout.setConstraints(iconLabel_5, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight =1;
        plLayout.setConstraints(button_5, gbc);
       
        // 每次程序运行时，自动触发一次button_5
        simulateButtonClick(button_5);
	}
	
	 private static void setGlobalFontSize(int size) {
        // 获取当前的字体
        Font currentFont = UIManager.getFont("Label.font");

        // 创建新的字体对象，设置字体名称、样式和大小
        Font newFont = new Font(currentFont.getName(), currentFont.getStyle(), size);

        // 设置全局字体
        UIManager.put("Label.font", newFont);
        UIManager.put("Button.font", newFont);
        // 可以根据需要设置其他组件的字体

        // 更新UI，使设置生效
        SwingUtilities.updateComponentTreeUI(new JFrame());
    }
	 
	 private static void simulateButtonClick(final JButton button) {
        // 在 Swing 事件分发线程上模拟按钮点击事件
        SwingUtilities.invokeLater(() -> button.doClick());
    }
	 
	 
	 private static void refreshFrame() {
        // 使用 revalidate() 和 repaint() 来刷新窗体显示
        frame.revalidate();
        frame.repaint();
   }
	 
	 public static void refreshPanelRight() {
        // 使用 revalidate() 和 repaint() 来刷新窗体显示
        MainView.panelRight.revalidate();
        MainView.panelRight.repaint();
   }
	 
	 // 设置全局文字颜色
    private static void setGlobalTextColor(Color textColor) {
        UIDefaults defaults = UIManager.getDefaults();
        Enumeration<Object> keys = defaults.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            if (key instanceof String && ((String) key).endsWith(".foreground")) {
                defaults.put(key, textColor);
            }
        }
    }

}
