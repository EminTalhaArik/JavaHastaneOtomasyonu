package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Appointment {

	private int id;
	private int doctorID;
	private String doctorName;
	private int hastaID;
	private String hastaName;
	private String date;

	DBConnection dbConnection = new DBConnection();

	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement;

	public Appointment(int id, int doctorID, String doctorName, int hastaID, String hastaName, String date) {
		super();
		this.id = id;
		this.doctorID = doctorID;
		this.doctorName = doctorName;
		this.hastaID = hastaID;
		this.hastaName = hastaName;
		this.date = date;
	}

	public Appointment() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public int getHastaID() {
		return hastaID;
	}

	public void setHastaID(int hastaID) {
		this.hastaID = hastaID;
	}

	public String getHastaName() {
		return hastaName;
	}

	public void setHastaName(String hastaName) {
		this.hastaName = hastaName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<Appointment> getHastaList(int hastaID) {

		ArrayList<Appointment> list = new ArrayList<>();
		Connection connection = dbConnection.dbConnection();
		Appointment obj;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM appointment WHERE hasta_id = " + hastaID);

			while (resultSet.next()) {

				obj = new Appointment();

				obj.setId(resultSet.getInt("id"));
				obj.setDoctorID(resultSet.getInt("doctor_id"));
				obj.setHastaID(resultSet.getInt("hasta_id"));
				obj.setDoctorName(resultSet.getString("doctor_name"));
				obj.setHastaName(resultSet.getString("hasta_name"));
				obj.setDate(resultSet.getString("date"));

				list.add(obj);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<Appointment> getDoctorList(int doctorID) {

		ArrayList<Appointment> list = new ArrayList<>();
		Connection connection = dbConnection.dbConnection();
		Appointment obj;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM appointment WHERE doctor_id = " + doctorID);

			while (resultSet.next()) {

				obj = new Appointment();

				obj.setId(resultSet.getInt("id"));
				obj.setDoctorID(resultSet.getInt("doctor_id"));
				obj.setHastaID(resultSet.getInt("hasta_id"));
				obj.setDoctorName(resultSet.getString("doctor_name"));
				obj.setHastaName(resultSet.getString("hasta_name"));
				obj.setDate(resultSet.getString("date"));

				list.add(obj);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return list;
	}

}
