package Model;

import java.sql.*;
import java.util.ArrayList;

import Helper.DBConnection;

public class Clinic {

	private int id;
	private String name;

	DBConnection dbConnection = new DBConnection();

	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement;

	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Clinic() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Clinic> getList() {

		ArrayList<Clinic> list = new ArrayList<Clinic>();
		Connection connection = dbConnection.dbConnection();
		Clinic obj;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM clinic");

			while (resultSet.next()) {

				obj = new Clinic();
				obj.setId(resultSet.getInt("id"));
				obj.setName(resultSet.getString("name"));

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

	public boolean addClinic(String name) {
		String query = "INSERT INTO clinic" + "(name) VALUES" + "(?)";
		Connection connection = dbConnection.dbConnection();
		boolean key = false;
		try {

			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, name);

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

	public boolean updateClinic(int id, String name) {
		String query = "UPDATE clinic SET name = ? WHERE id = ? ";
		Connection connection = dbConnection.dbConnection();
		boolean key = false;
		try {

			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);

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

	public Clinic getFetch(int id) {

		Connection connection = dbConnection.dbConnection();
		Clinic clinic = new Clinic();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM clinic WHERE id = " + id);
			while (resultSet.next()) {

				clinic.setId(resultSet.getInt("id"));
				clinic.setName(resultSet.getString("name"));
				break;
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
		return clinic;
	}

	public boolean deleteClinic(int id) {
		String query = "DELETE FROM clinic WHERE id = ?";
		Connection connection = dbConnection.dbConnection();
		boolean key = false;
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
