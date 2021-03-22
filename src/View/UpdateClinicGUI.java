package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Clinic;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Helper.*;
import java.awt.Toolkit;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUpdateClinic;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\EclipseProjectsSpace\\HastaneOtomasyonu\\src\\View\\medicine.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 250, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUpdateClinic = new JTextField();
		txtUpdateClinic.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		txtUpdateClinic.setColumns(10);
		txtUpdateClinic.setBounds(10, 45, 216, 31);
		txtUpdateClinic.setText(clinic.getName());
		contentPane.add(txtUpdateClinic);

		JButton btnUpdateClinic = new JButton("D\u00FCzenle");
		btnUpdateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean control = clinic.updateClinic(clinic.getId(), txtUpdateClinic.getText());

				if (control) {
					Helper.showMessage("succes");
					dispose();
				}

			}
		});
		btnUpdateClinic.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnUpdateClinic.setBounds(10, 86, 216, 51);
		contentPane.add(btnUpdateClinic);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_2_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2_1_1_1.setBounds(10, 10, 132, 25);
		contentPane.add(lblNewLabel_2_1_1_1);
	}

}
