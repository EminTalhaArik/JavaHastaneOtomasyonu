package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private Hasta hasta = new Hasta();
	private LoginGUI loginGUI = new LoginGUI();
	private JTextField txtName;
	private JTextField txtTCNo;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("D:\\EclipseProjectsSpace\\HastaneOtomasyonu\\src\\View\\medicine.png"));
		setResizable(false);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setBounds(100, 100, 394, 430);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Ad - Soyad ");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 0, 207, 25);
		contentPane.add(lblNewLabel_2);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(10, 35, 362, 31);
		contentPane.add(txtName);

		JLabel lblNewLabel_2_1 = new JLabel("T.C Numaras\u0131");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(10, 92, 207, 25);
		contentPane.add(lblNewLabel_2_1);

		txtTCNo = new JTextField();
		txtTCNo.setColumns(10);
		txtTCNo.setBounds(10, 127, 362, 31);
		contentPane.add(txtTCNo);

		JLabel lblNewLabel_2_2 = new JLabel("\u015Eifre");
		lblNewLabel_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblNewLabel_2_2.setBounds(10, 184, 207, 25);
		contentPane.add(lblNewLabel_2_2);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(10, 219, 362, 31);
		contentPane.add(txtPassword);

		JButton btnRegister = new JButton("Kay\u0131t Ol");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtName.getText().length() == 0 || txtTCNo.getText().length() == 0
						|| txtPassword.getText().length() == 0) {

					Helper.showMessage("fill");

				} else {

					boolean control = hasta.addPatient(txtTCNo.getText(), txtName.getText(), txtPassword.getText());

					if (control) {

						Helper.showMessage("succes");
						loginGUI.setVisible(true);
						dispose();

					} else {
						Helper.showMessage("error");
					}

				}

			}
		});
		btnRegister.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnRegister.setBounds(10, 271, 362, 61);
		contentPane.add(btnRegister);

		JButton btnExit = new JButton("Geri D\u00F6n");
		btnExit.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnExit.setBounds(10, 342, 362, 40);
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				loginGUI.setVisible(true);
				dispose();

			}
		});
		contentPane.add(btnExit);
	}
}
