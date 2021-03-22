package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.sun.jdi.connect.Connector.SelectedArgument;

import Helper.Helper;
import Helper.Item;
import Model.Bashekim;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import Model.Clinic;

import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Toolkit;

public class BashekimGUI extends JFrame {

	Clinic clinic = new Clinic();
	static Bashekim bashekim = new Bashekim();
	private JPanel contentPane;
	private JTable tblDoktor;
	private JTextField txtAddDoktorPassword;
	private JTextField txtRemoveDoktorID;
	private DefaultTableModel doctorModel;
	private Object[] doctorData;
	private JTextField txtAddDoktorName;
	private JTextField txtAddDoktorTC;
	private JTextField txtAddClinic;
	private JTable tblClinic;
	private DefaultTableModel clinicModel;
	private Object[] clinicData;
	private JPopupMenu clinicMenu;
	private JTable tblClinicDoktor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(Bashekim bashekim) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\EclipseProjectsSpace\\HastaneOtomasyonu\\src\\View\\medicine.png"));
		setTitle("Ba\u015Fhekim Paneli");

		// Doctor Model

		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ýsim Soyisim";
		colDoctorName[2] = "T.C No";
		colDoctorName[3] = "Þifre";

		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];

		for (int i = 0; i < bashekim.getDoktorList().size(); i++) {
			doctorData[0] = bashekim.getDoktorList().get(i).getId();
			doctorData[1] = bashekim.getDoktorList().get(i).getName();
			doctorData[2] = bashekim.getDoktorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoktorList().get(i).getPassword();

			doctorModel.addRow(doctorData);
		}

		// Clinic Model

		clinicModel = new DefaultTableModel();
		Object[] colClinicName = new Object[2];

		colClinicName[0] = "ID";
		colClinicName[1] = "Poliklinik Adý";

		clinicModel.setColumnIdentifiers(colClinicName);
		clinicData = new Object[2];

		for (int i = 0; i < clinic.getList().size(); i++) {

			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();

			clinicModel.addRow(clinicData);
		}

		// Worker Model

		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];

		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";

		workerModel.setColumnIdentifiers(colWorker);

		Object[] workerData = new Object[2];

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 549);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz Sayýn " + bashekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 293, 39);
		contentPane.add(lblNewLabel);

		JButton btnExit = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();

			}
		});
		btnExit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnExit.setBounds(690, 10, 109, 33);
		contentPane.add(btnExit);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 59, 789, 443);
		contentPane.add(tabbedPane);

		JPanel pnlDoktor = new JPanel();
		pnlDoktor.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Y\u00F6netim", null, pnlDoktor, null);
		pnlDoktor.setLayout(null);

		JScrollPane paneDoktor = new JScrollPane();
		paneDoktor.setBounds(10, 10, 521, 396);
		pnlDoktor.add(paneDoktor);

		tblDoktor = new JTable(doctorModel);
		tblDoktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					txtRemoveDoktorID.setText(tblDoktor.getValueAt(tblDoktor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}

			}
		});

		tblDoktor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {

					int selectID = Integer.parseInt(tblDoktor.getValueAt(tblDoktor.getSelectedRow(), 0).toString());
					String selectName = tblDoktor.getValueAt(tblDoktor.getSelectedRow(), 1).toString();
					String selectTCno = tblDoktor.getValueAt(tblDoktor.getSelectedRow(), 3).toString();
					String selectPassword = tblDoktor.getValueAt(tblDoktor.getSelectedRow(), 2).toString();

					boolean control = bashekim.updateDoktor(selectID, selectName, selectTCno, selectPassword);

				}

			}
		});

		paneDoktor.setViewportView(tblDoktor);

		JLabel lblNewLabel_2 = new JLabel("Ad - Soyad ");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2.setBounds(567, 10, 207, 25);
		pnlDoktor.add(lblNewLabel_2);

		txtAddDoktorPassword = new JTextField();
		txtAddDoktorPassword.setColumns(10);
		txtAddDoktorPassword.setBounds(567, 181, 207, 31);
		pnlDoktor.add(txtAddDoktorPassword);

		JLabel lblNewLabel_2_1 = new JLabel("T.C No");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(567, 82, 207, 25);
		pnlDoktor.add(lblNewLabel_2_1);

		JButton btnAddDoktor = new JButton("Ekle");
		btnAddDoktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtAddDoktorPassword.getText().length() == 0 || txtAddDoktorPassword.getText().length() == 0
						|| txtAddDoktorTC.getText().length() == 0) {
					Helper.showMessage("fill");
				} else {
					boolean control = bashekim.addDoktor(txtAddDoktorTC.getText(), txtAddDoktorName.getText(),
							txtAddDoktorPassword.getText());

					if (control) {
						Helper.showMessage("succes");
						txtAddDoktorPassword.setText(null);
						txtAddDoktorName.setText(null);
						txtAddDoktorTC.setText(null);

						updateDoktorTable();
					}
				}

			}
		});
		btnAddDoktor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnAddDoktor.setBounds(567, 222, 207, 51);
		pnlDoktor.add(btnAddDoktor);

		JLabel lblNewLabel_2_1_1 = new JLabel("\u015Eifre");
		lblNewLabel_2_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2_1_1.setBounds(567, 152, 207, 25);
		pnlDoktor.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_2 = new JLabel("Doktor ID");
		lblNewLabel_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2_2.setBounds(567, 283, 207, 25);
		pnlDoktor.add(lblNewLabel_2_2);

		txtRemoveDoktorID = new JTextField();
		txtRemoveDoktorID.setEditable(false);
		txtRemoveDoktorID.setColumns(10);
		txtRemoveDoktorID.setBounds(567, 316, 207, 31);
		pnlDoktor.add(txtRemoveDoktorID);

		JButton btnRemoveDoktor = new JButton("Sil");
		btnRemoveDoktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirmMessage("sure")) {
					int selID = Integer.parseInt(txtRemoveDoktorID.getText());

					boolean control = bashekim.removeDoktor(selID);

					if (control) {
						Helper.showMessage("succes");
						txtRemoveDoktorID.setText(null);
						updateDoktorTable();
					}
				}

			}

		});
		btnRemoveDoktor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnRemoveDoktor.setBounds(567, 359, 207, 47);
		pnlDoktor.add(btnRemoveDoktor);

		txtAddDoktorName = new JTextField();
		txtAddDoktorName.setBounds(567, 45, 207, 31);
		pnlDoktor.add(txtAddDoktorName);
		txtAddDoktorName.setColumns(10);

		txtAddDoktorTC = new JTextField();
		txtAddDoktorTC.setBounds(567, 117, 207, 31);
		pnlDoktor.add(txtAddDoktorTC);
		txtAddDoktorTC.setColumns(10);

		JPanel pnlClinic = new JPanel();
		pnlClinic.setBackground(Color.WHITE);
		tabbedPane.addTab("Poliklinikler", null, pnlClinic, null);
		pnlClinic.setLayout(null);

		JScrollPane paneClinici = new JScrollPane();
		paneClinici.setBounds(10, 10, 276, 396);
		pnlClinic.add(paneClinici);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");

		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(tblClinic.getValueAt(tblClinic.getSelectedRow(), 0).toString());

				if (Helper.confirmMessage("sure")) {
					boolean control = clinic.deleteClinic(selectedID);

					if (control) {
						Helper.showMessage("succes");
						updateClinicTable();
					}
				}

			}
		});

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer.parseInt(tblClinic.getValueAt(tblClinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selectedID);
				UpdateClinicGUI updateClinicGUI = new UpdateClinicGUI(selectClinic);
				updateClinicGUI.setVisible(true);
				updateClinicGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						updateClinicTable();
					}
				});
			}

		});

		tblClinic = new JTable(clinicModel);
		tblClinic.setComponentPopupMenu(clinicMenu);
		tblClinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = tblClinic.rowAtPoint(point);
				tblClinic.setRowSelectionInterval(selectedRow, selectedRow);

			}
		});

		paneClinici.setViewportView(tblClinic);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(498, 10, 276, 396);
		pnlClinic.add(scrollPane_1);

		tblClinicDoktor = new JTable();
		scrollPane_1.setViewportView(tblClinicDoktor);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_2_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2_1_1_1.setBounds(296, 10, 122, 25);
		pnlClinic.add(lblNewLabel_2_1_1_1);

		txtAddClinic = new JTextField();
		txtAddClinic.setColumns(10);
		txtAddClinic.setBounds(296, 45, 192, 31);
		pnlClinic.add(txtAddClinic);

		JButton btnAddClinic = new JButton("Ekle");
		btnAddClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtAddClinic.getText().length() == 0) {
					Helper.showMessage("fill");
				} else {

					boolean control = clinic.addClinic(txtAddClinic.getText());

					if (control) {

						Helper.showMessage("succes");
						txtAddClinic.setText(null);
						updateClinicTable();

					}

				}

			}
		});
		btnAddClinic.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnAddClinic.setBounds(296, 86, 192, 51);
		pnlClinic.add(btnAddClinic);

		JComboBox selectDoktor = new JComboBox();
		selectDoktor.setBounds(296, 294, 192, 51);
		for (int i = 0; i < bashekim.getDoktorList().size(); i++) {

			selectDoktor.addItem(
					new Item(bashekim.getDoktorList().get(i).getId(), bashekim.getDoktorList().get(i).getName()));

		}

		selectDoktor.addActionListener(e -> {

			JComboBox comboBox = (JComboBox) e.getSource();
			Item item = (Item) comboBox.getSelectedItem();

			System.out.println(item.getKey() + " : " + item.getValue());

		});

		pnlClinic.add(selectDoktor);

		JButton btnAddWorker = new JButton("Ekle");
		btnAddWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selectedRow = tblClinic.getSelectedRow();
				if (selectedRow >= 0) {

					String selectedClinic = tblClinic.getModel().getValueAt(selectedRow, 0).toString();
					int selectedClinicID = Integer.parseInt(selectedClinic);
					Item doktorID = (Item) selectDoktor.getSelectedItem();
					boolean control = bashekim.addWorker(doktorID.getKey(), selectedClinicID);

					if (control) {

						Helper.showMessage("succes");

					} else {
						Helper.showMessage("Error");
					}

				} else {
					Helper.showMessage("Lütfen bir Poliklinik seçiniz.");
				}

			}
		});
		btnAddWorker.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnAddWorker.setBounds(296, 355, 192, 51);
		pnlClinic.add(btnAddWorker);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2_1_1_1_1.setBounds(296, 175, 122, 25);
		pnlClinic.add(lblNewLabel_2_1_1_1_1);

		JButton btnSelectClinic = new JButton("Se\u00E7");
		btnSelectClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selectedRow = tblClinic.getSelectedRow();
				if (selectedRow >= 0) {

					String selClinic = tblClinic.getModel().getValueAt(selectedRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);

					DefaultTableModel clearModel = (DefaultTableModel) tblClinicDoktor.getModel();
					clearModel.setRowCount(0);

					for (int i = 0; i < bashekim.getClinicDoktorList(selClinicID).size(); i++) {

						workerData[0] = bashekim.getClinicDoktorList(selClinicID).get(i).getId();
						workerData[1] = bashekim.getClinicDoktorList(selClinicID).get(i).getName();

						workerModel.addRow(workerData);

					}

					tblClinicDoktor.setModel(workerModel);

				}

			}
		});
		btnSelectClinic.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnSelectClinic.setBounds(296, 203, 192, 51);
		pnlClinic.add(btnSelectClinic);

		JButton btnRefresh = new JButton("Yenile");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Tablolar Güncellendi
				updateClinicTable();
				updateDoktorTable();
				clearClinicDoktorTable();

				// SelectDoktor ComboBox Güncellendi

				try {
					selectDoktor.removeAllItems();

				} catch (Exception ex) {

				}
				for (int i = 0; i < bashekim.getDoktorList().size(); i++) {

					selectDoktor.addItem(new Item(bashekim.getDoktorList().get(i).getId(),
							bashekim.getDoktorList().get(i).getName()));

				}

			}
		});
		btnRefresh.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnRefresh.setBounds(571, 10, 109, 33);
		contentPane.add(btnRefresh);
	}

	public void updateDoktorTable() {
		DefaultTableModel clearModel = (DefaultTableModel) tblDoktor.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < bashekim.getDoktorList().size(); i++) {
			doctorData[0] = bashekim.getDoktorList().get(i).getId();
			doctorData[1] = bashekim.getDoktorList().get(i).getName();
			doctorData[2] = bashekim.getDoktorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoktorList().get(i).getPassword();

			doctorModel.addRow(doctorData);
		}

	}

	public void updateClinicTable() {

		DefaultTableModel clearModel = (DefaultTableModel) tblClinic.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < clinic.getList().size(); i++) {

			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();

			clinicModel.addRow(clinicData);
		}

	}

	public void clearClinicDoktorTable() {
		DefaultTableModel clearModel = (DefaultTableModel) tblClinicDoktor.getModel();
		clearModel.setRowCount(0);

	}
}
