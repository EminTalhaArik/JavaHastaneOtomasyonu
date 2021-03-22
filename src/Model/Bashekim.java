package Model;

import java.sql.*;
import java.util.ArrayList;

public class Bashekim extends User {

	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement;

	public Bashekim(int id, String name, String tcno, String password, String type) {
		super(id, name, tcno, password, type);

	}

	public Bashekim() {
	}

	public ArrayList<User> getDoktorList() {
		ArrayList<User> list = new ArrayList<>();
		Connection connection = mainConnection.dbConnection();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM user WHERE type = 'doktor'");

			User obj;
			while (resultSet.next()) {
				obj = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("tcno"),
						resultSet.getString("password"), resultSet.getString("type"));

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

	public boolean addDoktor(String tcno, String name, String password) {
		String query = "INSERT INTO user" + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";
		boolean key = false;
		Connection connection = mainConnection.dbConnection();

		try {
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");

			preparedStatement.executeUpdate();

			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public boolean removeDoktor(int id) {
		String query = "DELETE FROM user WHERE id = ?";
		boolean key = false;
		Connection connection = mainConnection.dbConnection();

		try {
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public boolean updateDoktor(int id, String name, String tcno, String password) {
		String query = "UPDATE user SET name = ? , tcno = ?, password = ? WHERE id = ?";
		boolean key = false;
		Connection connection = mainConnection.dbConnection();

		try {
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, password);
			preparedStatement.setInt(4, id);

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

	public boolean addWorker(int userID, int clinicID) {
		String query = "INSERT INTO worker" + "(user_id , clinic_id) VALUES" + "(?,?)";
		boolean key = false;
		Connection connection = mainConnection.dbConnection();

		int count = 0;

		try {
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT * FROM worker WHERE user_id=" + userID + " AND clinic_id=" + clinicID);
			while (resultSet.next()) {

				count++;

			}
			if (count == 0) {
				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(1, userID);
				preparedStatement.setInt(2, clinicID);

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

	public ArrayList<User> getClinicDoktorList(int clinicID) {
		ArrayList<User> list = new ArrayList<>();
		Connection connection = mainConnection.dbConnection();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(
					"SELECT u.id,u.tcno,u.password,u.name,u.type FROM worker w LEFT JOIN user u  ON w.user_id = u.id WHERE clinic_id ="
							+ clinicID);

			User obj;
			while (resultSet.next()) {
				obj = new User(resultSet.getInt("u.id"), resultSet.getString("u.name"),
						resultSet.getString("u.password"), resultSet.getString("u.tcno"),
						resultSet.getString("u.type"));

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

}
