package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Helper.DBConnection;

public class WorkHour {

	private int id;
	private int doctorID;
	private String doctorName;
	private String status;
	private String workDate;

	DBConnection dbConnection = new DBConnection();

	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement;

	public WorkHour(int id, int doctorID, String doctorName, String status, String workDate) {
		super();
		this.id = id;
		this.doctorID = doctorID;
		this.doctorName = doctorName;
		this.status = status;
		this.workDate = workDate;
	}

	public WorkHour() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

}
