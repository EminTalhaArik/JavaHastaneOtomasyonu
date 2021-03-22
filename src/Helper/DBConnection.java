package Helper;

import java.sql.*;

public class DBConnection {
	Connection connection = null;

	public Connection dbConnection() {

		try {
			this.connection = DriverManager
					.getConnection("jdbc:mariadb://localhost:3325/hospital?user=root&password=12345");
			return connection;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return connection;

	}

}
