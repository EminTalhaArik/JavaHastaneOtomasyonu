package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Hasta extends User {

	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement;

	public Hasta() {
		super();
	}

	public Hasta(int id, String name, String tcno, String password, String type) {
		super(id, name, tcno, password, type);
	}

	public boolean addPatient(String tcno, String name, String password) {

		boolean key = false;
		int count = 0;

		String query = "INSERT INTO user" + "(tcno,password,name) VALUES" + "(?,?,?)";
		Connection connection = mainConnection.dbConnection();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM user WHERE tcno='" + tcno + "'");

			while (resultSet.next()) {

				count = 1;

			}

			if (count == 0) {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
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

	public boolean addAppointment(int doctorID, int hastaID, String doctorName, String hastaName, String date) {

		boolean key = false;

		String query = "INSERT INTO appointment" + "(doctor_id, doctor_name, hasta_id, hasta_name, date) VALUES"
				+ "(?,?,?,?,?)";
		Connection connection = mainConnection.dbConnection();

		try {
			statement = connection.createStatement();

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, doctorID);
			preparedStatement.setString(2, doctorName);
			preparedStatement.setInt(3, hastaID);
			preparedStatement.setString(4, hastaName);
			preparedStatement.setString(5, date);

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

	public boolean updateWorkhourStatus(int doctorID, String date) {

		boolean key = false;

		String query = "UPDATE workhour SET status = ? WHERE doctor_id = ?  AND work_date = ?";
		Connection connection = mainConnection.dbConnection();

		try {
			statement = connection.createStatement();

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "pasif");
			preparedStatement.setInt(2, doctorID);
			preparedStatement.setString(3, date);

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
