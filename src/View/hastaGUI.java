package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Appointment;
import Model.Bashekim;
import Model.Clinic;
import Model.Doctor;
import Model.Hasta;
import Model.WorkHour;
import Helper.*;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class hastaGUI extends JFrame {

	// TableModel
	private DefaultTableModel workHourModel;
	private Object[] workHourData;
	private DefaultTableModel appointmentModel;
	private Object[] appointmentData;
	private DefaultTableModel doctorModel;
	private Object[] doctorData;

	// Class Definition
	private Appointment appointment = new Appointment();
	private Doctor doctor = new Doctor();
	private Bashekim bashekim = new Bashekim();
	private static Hasta hasta = new Hasta();

	// Genel Deðiþkenler
	private int selectDoctorID = 0;
	private String selectDoctorName = null;

	// Defualt
	private JPanel contentPane;
	private JTable tblDoctor;
	private Clinic clinic = new Clinic();
	private JTable tblWorkHour;

	private JTable tblAppointment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hastaGUI frame = new hastaGUI(hasta);
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
	public hastaGUI(Hasta hasta) {

		// Doktor Model
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[2];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Doktor Adý- Soyadý";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[2];
		// ***************************

		// Work Hour Model
		workHourModel = new DefaultTableModel();
		Object[] colWorkHourName = new Object[2];
		colWorkHourName[0] = "ID";
		colWorkHourName[1] = "Tarih";
		workHourData = new Object[2];
		workHourModel.setColumnIdentifiers(colWorkHourName);
		// ***************************

		// Appointment Model

		appointmentModel = new DefaultTableModel();
		Object[] colAppointment = new Object[3];
		colAppointment[0] = "ID";
		colAppointment[1] = "Doktor";
		colAppointment[2] = "Tarih";
		appointmentModel.setColumnIdentifiers(colAppointment);
		appointmentData = new Object[3];

		for (int i = 0; i < appointment.getHastaList(hasta.getId()).size(); i++) {

			appointmentData[0] = appointment.getHastaList(hasta.getId()).get(i).getId();
			appointmentData[1] = appointment.getHastaList(hasta.getId()).get(i).getDoctorName();
			appointmentData[2] = appointment.getHastaList(hasta.getId()).get(i).getDate();

			appointmentModel.addRow(appointmentData);

		}

		// *****************************

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("D:\\EclipseProjectsSpace\\HastaneOtomasyonu\\src\\View\\medicine.png"));
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 549);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz Sayýn " + hasta.getName());
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 293, 39);
		contentPane.add(lblNewLabel);

		JButton btnExit = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnExit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnExit.setBounds(690, 10, 109, 33);
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
  
			}
		});
		contentPane.add(btnExit);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 59, 789, 443);
		contentPane.add(tabbedPane);

		JPanel pnlAppointment = new JPanel();
		pnlAppointment.setBackground(Color.WHITE);
		tabbedPane.addTab("Randevu Sistemi", null, pnlAppointment, null);
		pnlAppointment.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 303, 354);
		pnlAppointment.add(scrollPane);

		tblDoctor = new JTable(doctorModel);
		scrollPane.setViewportView(tblDoctor);

		JLabel lblDoktorListesi = new JLabel("Doktor Listesi");
		lblDoktorListesi.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblDoktorListesi.setBackground(Color.WHITE);
		lblDoktorListesi.setBounds(10, 10, 293, 39);
		pnlAppointment.add(lblDoktorListesi);

		JComboBox selectClinic = new JComboBox();
		selectClinic.setBounds(323, 52, 182, 39);

		selectClinic.addItem("-- Poliklinik Seç --");

		for (int i = 0; i < clinic.getList().size(); i++) {

			selectClinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));

		}
		selectClinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectClinic.getSelectedIndex() != 0) {
					JComboBox comboBox = (JComboBox) e.getSource();

					Item item = (Item) comboBox.getSelectedItem();

					DefaultTableModel clearModel = (DefaultTableModel) tblDoctor.getModel();
					clearModel.setRowCount(0);

					for (int i = 0; i < bashekim.getClinicDoktorList(item.getKey()).size(); i++) {

						doctorData[0] = bashekim.getClinicDoktorList(item.getKey()).get(i).getId();
						doctorData[1] = bashekim.getClinicDoktorList(item.getKey()).get(i).getName();

						doctorModel.addRow(doctorData);
					}

				} else {

					DefaultTableModel clearModel = (DefaultTableModel) tblDoctor.getModel();
					clearModel.setRowCount(0);

				}

			}
		});

		pnlAppointment.add(selectClinic);

		JLabel lblPoliklinikAd = new JLabel("Poliklinik Ad\u0131");
		lblPoliklinikAd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblPoliklinikAd.setBackground(Color.WHITE);
		lblPoliklinikAd.setBounds(323, 10, 182, 39);
		pnlAppointment.add(lblPoliklinikAd);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Doktor Se\u00E7");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2_1_1_1_1.setBounds(323, 148, 122, 25);
		pnlAppointment.add(lblNewLabel_2_1_1_1_1);

		JButton btnSelectDoktor = new JButton("Se\u00E7");
		btnSelectDoktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblDoctor.getSelectedRow();

				if (row >= 0) {
					String value = tblDoctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) tblWorkHour.getModel();
					clearModel.setRowCount(0);

					for (int i = 0; i < doctor.getWorkHourList(id).size(); i++) {
						workHourData[0] = doctor.getWorkHourList(id).get(i).getId();
						workHourData[1] = doctor.getWorkHourList(id).get(i).getWorkDate();

						workHourModel.addRow(workHourData);
					}

					tblWorkHour.setModel(workHourModel);

					selectDoctorID = id;
					selectDoctorName = tblDoctor.getModel().getValueAt(row, 1).toString();

				} else {

					Helper.showMessage("Lütfen Bir Doktor Seçiniz.");

				}

			}
		});
		btnSelectDoktor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnSelectDoktor.setBounds(323, 176, 182, 51);
		pnlAppointment.add(btnSelectDoktor);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(515, 62, 259, 354);
		pnlAppointment.add(scrollPane_1);

		tblWorkHour = new JTable();
		scrollPane_1.setViewportView(tblWorkHour);

		JLabel lblRandevuTarihi = new JLabel("Uygun Saatler");
		lblRandevuTarihi.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblRandevuTarihi.setBackground(Color.WHITE);
		lblRandevuTarihi.setBounds(515, 20, 141, 39);
		pnlAppointment.add(lblRandevuTarihi);

		JButton btnAddAppointment = new JButton("Randevu Al");
		btnAddAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selRow = tblWorkHour.getSelectedRow();
				if (selRow >= 0) {

					String date = tblWorkHour.getModel().getValueAt(selRow, 1).toString();

					boolean control = hasta.addAppointment(selectDoctorID, hasta.getId(), selectDoctorName,
							hasta.getName(), date);

					if (control) {

						Helper.showMessage("succes");
						hasta.updateWorkhourStatus(selectDoctorID, date);
						updateWorkhourModel(selectDoctorID);
						updateAppointmentModel(hasta.getId());
						
					}

				} else {
					Helper.showMessage("Lütfen geçerli bir tarih seçiniz.");
				}

			}
		});
		btnAddAppointment.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnAddAppointment.setBounds(323, 287, 182, 51);
		pnlAppointment.add(btnAddAppointment);

		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Randevu");
		lblNewLabel_2_1_1_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2_1_1_1_1_1.setBounds(323, 259, 122, 25);
		pnlAppointment.add(lblNewLabel_2_1_1_1_1_1);

		JPanel pnlShowAppoint = new JPanel();
		pnlShowAppoint.setBackground(Color.WHITE);
		tabbedPane.addTab("Randevular\u0131m", null, pnlShowAppoint, null);
		pnlShowAppoint.setLayout(null);

		JScrollPane scrollAppoint = new JScrollPane();
		scrollAppoint.setBounds(10, 10, 764, 396);
		pnlShowAppoint.add(scrollAppoint);

		tblAppointment = new JTable(appointmentModel);
		scrollAppoint.setViewportView(tblAppointment);
	}

	public void updateWorkhourModel(int doctorID) {

		DefaultTableModel clearModel = (DefaultTableModel) tblWorkHour.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < doctor.getWorkHourList(doctorID).size(); i++) {
			workHourData[0] = doctor.getWorkHourList(doctorID).get(i).getId();
			workHourData[1] = doctor.getWorkHourList(doctorID).get(i).getWorkDate();

			workHourModel.addRow(workHourData);
		}
	}

	public void updateAppointmentModel(int hastaID) {
		DefaultTableModel clearModel = (DefaultTableModel) tblAppointment.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < appointment.getHastaList(hastaID).size(); i++) {

			appointmentData[0] = appointment.getHastaList(hastaID).get(i).getId();
			appointmentData[1] = appointment.getHastaList(hastaID).get(i).getDoctorName();
			appointmentData[2] = appointment.getHastaList(hastaID).get(i).getDate();

			appointmentModel.addRow(appointmentData);

		}
	}
}
