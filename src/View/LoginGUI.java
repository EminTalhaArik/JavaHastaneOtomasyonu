package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import Model.*;
import Helper.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoginGUI extends JFrame {

	private DBConnection mainConnection = new DBConnection();

	private JPanel contentPane;
	private JTextField txtHastaTC;
	private JPasswordField txtHastaPassword;
	private JTextField txtDoktorTC;
	private JPasswordField txtDoktorPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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

	public LoginGUI() {
		setResizable(false);

		setIconImage(
				Toolkit.getDefaultToolkit().getImage("D:\\EclipseProjectsSpace\\HastaneOtomasyonu\\src\\View\\medicine.png"));
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 436);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblNewLabel.setBounds(125, 26, 390, 43);
		contentPane.add(lblNewLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 92, 632, 297);
		contentPane.add(tabbedPane);

		JPanel pnlHastaGiriþi = new JPanel();
		pnlHastaGiriþi.setBackground(Color.WHITE);
		tabbedPane.addTab("Hasta Giri\u015Fi", null, pnlHastaGiriþi, null);
		pnlHastaGiriþi.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("T.C Numaran\u0131z : ");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2.setBounds(138, 42, 138, 25);
		pnlHastaGiriþi.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("\u015Eifreniz : ");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(188, 77, 88, 25);
		pnlHastaGiriþi.add(lblNewLabel_2_1);

		txtHastaTC = new JTextField();
		txtHastaTC.setBounds(286, 42, 185, 25);
		pnlHastaGiriþi.add(txtHastaTC);
		txtHastaTC.setColumns(10);

		txtHastaPassword = new JPasswordField();
		txtHastaPassword.setBounds(286, 77, 185, 25);
		pnlHastaGiriþi.add(txtHastaPassword);

		JButton btnHastaLogin = new JButton("Giri\u015F Yap");
		btnHastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtHastaTC.getText().length() == 0 || txtHastaPassword.getText().length() == 0) {
					Helper.showMessage("fill");
				} else {

					try {
						Connection connection = mainConnection.dbConnection();
						Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
						Hasta hasta = new Hasta();

						while (resultSet.next()) {
							if (txtHastaTC.getText().equals(resultSet.getString("tcno"))
									&& txtHastaPassword.getText().equals(resultSet.getString("password"))) {
								if (resultSet.getString("type").equals("hasta")) {

									hasta.setId(resultSet.getInt("ID"));
									hasta.setName(resultSet.getString("name"));
									hasta.setPassword(resultSet.getString("password"));
									hasta.setTcno(resultSet.getString("tcno"));
									hasta.setType(resultSet.getString("type"));

									System.out.println(hasta.getName());
									hastaGUI hastaGUI = new hastaGUI(hasta);
									hastaGUI.setVisible(true);
									dispose();

								}

							}

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnHastaLogin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnHastaLogin.setBounds(91, 158, 207, 51);
		pnlHastaGiriþi.add(btnHastaLogin);

		JButton btnHastaKaytOl = new JButton("Kay\u0131t Ol");
		btnHastaKaytOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				RegisterGUI registerGUI = new RegisterGUI();
				registerGUI.setVisible(true);
				dispose();
			}
		});
		btnHastaKaytOl.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnHastaKaytOl.setBounds(322, 158, 207, 51);
		pnlHastaGiriþi.add(btnHastaKaytOl);

		JPanel pnlDoktorGiriþi = new JPanel();
		pnlDoktorGiriþi.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Giri\u015F", null, pnlDoktorGiriþi, null);
		pnlDoktorGiriþi.setLayout(null);

		JLabel lblNewLabel_2_2 = new JLabel("T.C Numaran\u0131z : ");
		lblNewLabel_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2_2.setBounds(148, 41, 138, 25);
		pnlDoktorGiriþi.add(lblNewLabel_2_2);

		txtDoktorTC = new JTextField();
		txtDoktorTC.setColumns(10);
		txtDoktorTC.setBounds(296, 41, 185, 25);
		pnlDoktorGiriþi.add(txtDoktorTC);

		JLabel lblNewLabel_2_1_1 = new JLabel("\u015Eifreniz : ");
		lblNewLabel_2_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2_1_1.setBounds(198, 76, 88, 25);
		pnlDoktorGiriþi.add(lblNewLabel_2_1_1);

		txtDoktorPassword = new JPasswordField();
		txtDoktorPassword.setBounds(296, 76, 185, 25);
		pnlDoktorGiriþi.add(txtDoktorPassword);

		JButton btnDoktorLOgin = new JButton("Giri\u015F Yap");
		btnDoktorLOgin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtDoktorTC.getText().length() == 0 || txtDoktorPassword.getText().length() == 0) {
					Helper.showMessage("fill");
				} else {

					try {
						Connection connection = mainConnection.dbConnection();
						Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
						Bashekim bashekim = new Bashekim();

						while (resultSet.next()) {
							if (txtDoktorTC.getText().equals(resultSet.getString("tcno"))
									&& txtDoktorPassword.getText().equals(resultSet.getString("password"))) {
								if (resultSet.getString("type").equals("bashekim")) {

									bashekim.setId(resultSet.getInt("ID"));
									bashekim.setName(resultSet.getString("name"));
									bashekim.setPassword(resultSet.getString("password"));
									bashekim.setTcno(resultSet.getString("tcno"));
									bashekim.setType(resultSet.getString("type"));

									System.out.println(bashekim.getName());
									BashekimGUI bashekimGUI = new BashekimGUI(bashekim);
									bashekimGUI.setVisible(true);
									dispose();

								}

								if (resultSet.getString("type").equals("doktor")) {

									Doctor doctor = new Doctor();

									doctor.setId(resultSet.getInt("ID"));
									doctor.setName(resultSet.getString("name"));
									doctor.setPassword(resultSet.getString("password"));
									doctor.setTcno(resultSet.getString("tcno"));
									doctor.setType(resultSet.getString("type"));

									System.out.println(doctor.getName());
									DoctorGUI doctorGUI = new DoctorGUI(doctor);
									doctorGUI.setVisible(true);
									dispose();

								}

							}

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnDoktorLOgin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnDoktorLOgin.setBounds(148, 150, 333, 51);
		pnlDoktorGiriþi.add(btnDoktorLOgin);
	}
}
