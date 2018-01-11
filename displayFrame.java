package myQRCode;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLayeredPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class displayFrame extends JFrame {

	private JPanel contentPane;
	private JPanel subpanel;
	private JLabel label2;
	private JButton button_1;
	private JButton button;

	/**
	 * Create the frame.
	 */
	public void disposeSelf() {
		this.dispose();
	}
	
	public displayFrame(ImageIcon icon) {
		this.setTitle("下面是您的二维码");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 375, 410);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		subpanel = new JPanel();
		subpanel.setBounds(10, 10, 339, 321);
		contentPane.add(subpanel);
		
		label2 = new JLabel();
		label2.setIcon(icon);
		
		subpanel.add(label2);
		
		button_1 = new JButton("\u7EE7\u7EED\u521B\u5EFA");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				disposeSelf();
			}
		});
		button_1.setBounds(56, 338, 93, 23);
		contentPane.add(button_1);
		
		button = new JButton("\u9000\u51FA\u7A0B\u5E8F");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		button.setBounds(196, 338, 93, 23);
		contentPane.add(button);
	}

}
