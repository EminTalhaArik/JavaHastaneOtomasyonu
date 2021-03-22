package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doctor;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class DoctorGUI extends JFrame {

	private JPanel contentPane;
	private static Doctor doctor = new Doctor();
	private JDateChooser dateSelector;
	private JTable tblWorkHour;

	private DefaultTableModel workHourModel;
	private Object[] workHourData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) {

		// Work Hour Model

		workHourModel = new DefaultTableModel();
		Object[] colWorkHour = new Object[2];
		colWorkHour[0] = "ID";
		colWorkHour[1] = "Tarih";

		workHourModel.setColumnIdentifiers(colWorkHour);

		workHourData = new Object[2];

		for (int i = 0; i < doctor.getWorkHourList(doctor.getId()).size(); i++) {

			workHourData[0] = doctor.getWorkHourList(doctor.getId()).get(i).getId();
			workHourData[1] = doctor.getWorkHourList(doctor.getId()).get(i).getWorkDate();

			workHourModel.addRow(workHourData);

		}

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

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz Sayýn " + doctor.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 293, 39);
		contentPane.add(lblNewLabel);

		JTabbedPane paneTabb = new JTabbedPane(JTabbedPane.TOP);
		paneTabb.setBounds(10, 59, 789, 443);
		contentPane.add(paneTabb);

		JPanel pnlWorkHour = new JPanel();
		pnlWorkHour.setBackground(Color.WHITE);
		paneTabb.addTab("\u00C7al\u0131\u015Fma Saatleri", null, pnlWorkHour, null);
		pnlWorkHour.setLayout(null);

		dateSelector = new JDateChooser();
		dateSelector.setBounds(10, 10, 154, 30);
		pnlWorkHour.add(dateSelector);

		JComboBox hourSelector = new JComboBox();
		hourSelector.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		hourSelector.setModel(new DefaultComboBoxModel(new String[] { "10:00", "10:30", "11:00", "11:30", "12:00",
				"12:30", "13:30", "14:00", "14:30", "15:00", "15:30" }));
		hourSelector.setBounds(174, 10, 82, 30);
		pnlWorkHour.add(hourSelector);

		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = simpleDateFormat.format(dateSelector.getDate());

				if (date.length() == 0) {
					Helper.showMessage("Lütfen Bir Tarih Seçiniz.");

				} else {
					String time = " " + hourSelector.getSelectedItem().toString() + ":00";
					String selectedDate = date + time;

					boolean control = doctor.addWorHour(doctor.getId(), doctor.getName(), selectedDate);

					if (control) {

						Helper.showMessage("succes");
						updateWorkDateTable(doctor);

					} else {

						Helper.showMessage("Error");

					}

				}

			}
		});
		btnEkle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnEkle.setBounds(266, 10, 89, 30);
		pnlWorkHour.add(btnEkle);

		JScrollPane paneWorkHour = new JScrollPane();
		paneWorkHour.setBounds(10, 50, 764, 356);
		pnlWorkHour.add(paneWorkHour);

		tblWorkHour = new JTable(workHourModel);
		paneWorkHour.setViewportView(tblWorkHour);

		JButton btnDelete = new JButton("Sil");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Helper.confirmMessage("sure")) {

					int selectedRow = tblWorkHour.getSelectedRow();
					if (selectedRow >= 0) {

						int selectedID = Integer.parseInt(tblWorkHour.getModel().getValueAt(selectedRow, 0).toString());
						boolean control = doctor.removeWorkHour(selectedID);

						if (control) {

							Helper.showMessage("succes");
							updateWorkDateTable(doctor);
							
						}

					} else {
						Helper.showMessage("Lütfen Bir Tarih Seçiniz. ");
					}

				}

			}
		});
		btnDelete.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnDelete.setBounds(685, 10, 89, 30);
		pnlWorkHour.add(btnDelete);

		JButton btnExit = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();

			}
		});
		btnExit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnExit.setBounds(673, 10, 126, 33);
		contentPane.add(btnExit);
	}

	public void updateWorkDateTable(Doctor doctor) {

		DefaultTableModel clearModel = (DefaultTableModel) tblWorkHour.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < doctor.getWorkHourList(doctor.getId()).size(); i++) {

			workHourData[0] = doctor.getWorkHourList(doctor.getId()).get(i).getId();
			workHourData[1] = doctor.getWorkHourList(doctor.getId()).get(i).getWorkDate();

			workHourModel.addRow(workHourData);

		}

	}
}
