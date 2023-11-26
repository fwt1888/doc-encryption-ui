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
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;


public class MainView {
	
	private static int icon_width = 40;
	private static int icon_height = 40;

	private JFrame frame;
	
	// �ұ߿ɱ�����
	public JPanel panelRight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingUtilities.invokeLater(() -> {
						setGlobalFontSize(13);
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
		
		// ���� GridBag
		GridBagLayout frameLayout = new GridBagLayout();
        frame.getContentPane().setLayout(frameLayout);
		
        // ���Ҳ���
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
		GridBagLayout prLayout = new GridBagLayout();
		panelRight.setLayout(prLayout);

		// ���ѡ�
		// ѡ��1: ��ݹ���
		JLabel iconLabel_1 = new JLabel("");
		iconLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image1 = new ImageIcon(MainView.class.getResource("/gui/resources/muffin_411035.png"));
		image1.setImage(image1.getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_DEFAULT ));
		iconLabel_1.setIcon(image1);
		
		JButton button_1 = new JButton("��ݹ���");
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.DARK_GRAY);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		// ѡ��2: ���ݼ���
		JLabel iconLabel_2 = new JLabel("");
		iconLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image2 = new ImageIcon(MainView.class.getResource("/gui/resources/key_807241.png"));
		image2.setImage(image2.getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_DEFAULT ));
		iconLabel_2.setIcon(image2);
		
		JButton button_2 = new JButton("���ݼ���");
		button_2.setBackground(Color.DARK_GRAY);
		button_2.setForeground(Color.WHITE);
		
		// ѡ��3: ���ݽ���
		JLabel iconLabel_3 = new JLabel("");
		iconLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image3 = new ImageIcon(MainView.class.getResource("/gui/resources/pixels_358909.png"));
		image3.setImage(image3.getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_DEFAULT ));
		iconLabel_3.setIcon(image3);
		
		JButton button_3 = new JButton("���ݽ���");
		button_3.setBackground(Color.DARK_GRAY);
		button_3.setForeground(Color.WHITE);
		
		// ѡ��4: �߼�����
		JLabel iconLabel_4 = new JLabel("");
		iconLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image4 = new ImageIcon(MainView.class.getResource("/gui/resources/game_10125801.png"));
		image4.setImage(image4.getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_DEFAULT ));
		iconLabel_4.setIcon(image4);
		
		JButton button_4 = new JButton("�߼�����");
		button_4.setIcon(null);
		button_4.setBackground(Color.DARK_GRAY);
		button_4.setForeground(Color.WHITE);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		// ѡ��5: ����
		JLabel iconLabel_5 = new JLabel("");
		iconLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image5 = new ImageIcon(MainView.class.getResource("/gui/resources/bisexual_8236845.png"));
		image5.setImage(image5.getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_DEFAULT ));
		iconLabel_5.setIcon(image5);
		
		JButton button_5 = new JButton("����");
		button_5.setSelected(true);
		button_5.setIcon(null);
		button_5.setBackground(Color.DARK_GRAY);
		button_5.setForeground(Color.WHITE);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("1");
			}
		});
		
		// ���������ӽ�jFrame
		frame.getContentPane().add(panelLeft); 
		frame.getContentPane().add(separator);
		frame.getContentPane().add(panelRight);
		panelLeft.add(iconLabel_1); panelLeft.add(button_1);
		panelLeft.add(iconLabel_2); panelLeft.add(button_2);
		panelLeft.add(iconLabel_3); panelLeft.add(button_3);
		panelLeft.add(iconLabel_4); panelLeft.add(button_4);
		panelLeft.add(iconLabel_5); panelLeft.add(button_5);

		
		// ����GridBag����
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;  

        // panel
        Insets insets = new Insets(20, 10, 20, 10);
        gbc.insets = insets;
        
        gbc.weightx = 0.2; // �������
        gbc.weighty = 1; // �������
        frameLayout.setConstraints(panelLeft, gbc);
        
        gbc.weightx = 0.1; // �������
        gbc.weighty = 1; // �������
        frameLayout.setConstraints(separator, gbc);
        
        gbc.weightx = 0.9; // �������
        gbc.weighty = 1.0; // �������
        frameLayout.setConstraints(panelRight, gbc);
        
        // ѡ��1
        insets = new Insets(20, 5, 20, 5); //�ϡ����¡���
        gbc.insets = insets;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        plLayout.setConstraints(iconLabel_1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        plLayout.setConstraints(button_1, gbc);
        
        // ѡ��2
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        plLayout.setConstraints(iconLabel_2, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        plLayout.setConstraints(button_2, gbc);
        
        // ѡ��3
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        plLayout.setConstraints(iconLabel_3, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.7;
        plLayout.setConstraints(button_3, gbc);
        
        // ѡ��4
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        plLayout.setConstraints(iconLabel_4, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.7;
        plLayout.setConstraints(button_4, gbc);
        
        // ѡ��5
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        plLayout.setConstraints(iconLabel_5, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0.7;
        plLayout.setConstraints(button_5, gbc);
       
        // ÿ�γ�������ʱ���Զ�����һ��button_5
        simulateButtonClick(button_5);
	}
	
	 private static void setGlobalFontSize(int size) {
	        // ��ȡ��ǰ������
	        Font currentFont = UIManager.getFont("Label.font");

	        // �����µ�������������������ơ���ʽ�ʹ�С
	        Font newFont = new Font(currentFont.getName(), currentFont.getStyle(), size);

	        // ����ȫ������
	        UIManager.put("Label.font", newFont);
	        UIManager.put("Button.font", newFont);
	        // ���Ը�����Ҫ�����������������

	        // ����UI��ʹ������Ч
	        SwingUtilities.updateComponentTreeUI(new JFrame());
	    }
	 
	 private static void simulateButtonClick(final JButton button) {
	        // �� Swing �¼��ַ��߳���ģ�ⰴť����¼�
	        SwingUtilities.invokeLater(() -> button.doClick());
	    }

}
