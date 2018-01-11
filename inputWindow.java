package myQRCode;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.zxing.WriterException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inputWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inputWindow frame = new inputWindow();
					frame.setTitle("二维码生成器");
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public inputWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel label = new JLabel("\u8BF7\u5728\u4E0B\u65B9\u8F93\u5165\u60A8\u9700\u8981\u8F6C\u6362\u4E3A\u4E8C\u7EF4\u7801\u7684\u6587\u5B57");

		JTextArea textArea = new JTextArea();
		textArea.setText("\u5728\u6B64\u952E\u5165\u6587\u5B57");

		JButton button = new JButton("\u751F\u6210QRCode");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (createQRCode.getPath()==null) {
					JOptionPane.showMessageDialog(null, "请先为二维码配置路径", "无法找到路径", JOptionPane.ERROR_MESSAGE, null);
				}
				else {
					if (textArea.getText() != null) {
						String msg = textArea.getText();

						try {

							createQRCode.encondeQRCode(msg);
							textArea.setText(null);
							JOptionPane.showMessageDialog(null, "QRCode created successfully!");
							
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										displayFrame frame = new displayFrame(createQRCode.showQRCode());
										frame.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							

						} catch (WriterException | IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			}
		});
		
		textField = new JTextField();
		textField.setColumns(20);
		textField.setEditable(false);
		
		JButton button2 = new JButton("\u9009\u62E9\u4FDD\u5B58\u8DEF\u5F84");
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jf = new JFileChooser();
				jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int i = jf.showOpenDialog(null);
				if (i == JFileChooser.APPROVE_OPTION) {
					String filePath = jf.getSelectedFile().getAbsolutePath();
					System.out.println(filePath);
					createQRCode.setPath(filePath);
					textField.setText(createQRCode.getPath());
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(148)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(182, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addGap(10)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button)
					.addGap(18))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
