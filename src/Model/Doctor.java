package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Doctor extends User {
	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement;
	

	public Doctor() {
		super();
	}

	public Doctor(int id, String name, String tcno, String password, String type) {
		super(id, name, tcno, password, type);
	}

	public boolean addWorHour(int doctorID, String doctorName, String workDate) {
		Connection connection = mainConnection.dbConnection();
		boolean key = false;
		int count = 0;

		String query = "INSERT INTO workhour" + "(doctor_id,doctor_name,work_date) VALUES" + "(?,?,?)";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM workhour WHERE status='aktif' AND doctor_id=" + doctorID
					+ " AND work_date='" + workDate + "'");

			while (resultSet.next()) {

				count = 1;

			}

			if (count == 0) {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, doctorID);
				preparedStatement.setString(2, doctorName);
				preparedStatement.setString(3, workDate);
				preparedStatement.executeUpdate();

				key = true;
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

		return key;
	}

	public ArrayList<WorkHour> getWorkHourList(int doctorID) {
		ArrayList<WorkHour> list = new ArrayList<>();
		Connection connection = mainConnection.dbConnection();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM workhour WHERE status='aktif' AND doctor_id=" + doctorID);

			WorkHour obj;
			while (resultSet.next()) {
				obj = new WorkHour();

				obj.setId(resultSet.getInt("id"));
				obj.setDoctorID(resultSet.getInt("doctor_id"));
				obj.setDoctorName(resultSet.getString("doctor_name"));
				obj.setStatus(resultSet.getString("status"));
				obj.setWorkDate(resultSet.getString("work_date"));
				list.add(obj);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return list;
	}

	public boolean removeWorkHour(int id) {
		String query = "DELETE FROM workhour WHERE id = ?";
		boolean key = false;
		Connection connection = mainConnection.dbConnection();

		try {
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			key = true;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return key;
	}

}
