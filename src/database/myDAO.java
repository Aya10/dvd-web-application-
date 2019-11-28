package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Dvd;
import models.User;

public class myDAO {

	private Connection getConnection() {
		Connection conn = null;
		try {
			// load driver
			Class.forName("org.sqlite.JDBC");
			// url of the database location
			String url = "jdbc:sqlite:/Users/ayaaboutwerat/eclipse-workspace/dvdWebApp/dvd.sqlite";
			// initialise the conn object
			conn = DriverManager.getConnection(url);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// return conn object
		return conn;

	}

	public Dvd getStringDvd(int ID) throws SQLException {
		// creating array list

		// enstantiating a connection object. connecting to the database
		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		Dvd dvd = null;

		String sql = "SELECT * FROM collection WHERE id =" + ID + ";";
		System.out.println(sql);

		ResultSet rs = statement.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("Title");
			String genre = rs.getString("Genre");
			int year = rs.getInt("Year");

			// System.out.println(id + " " + title + " " + genre + " " + year);
			dvd = new Dvd(id, title, genre, year);

		}
		return dvd;

	}

	public ArrayList<Dvd> getDVDs() throws SQLException {
		// creating array list
		ArrayList<Dvd> allDvds = new ArrayList<>();
		// enstantiating a connection object. connecting to the database
		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "SELECT * FROM collection;";
		System.out.println(sql);

		ResultSet rs = statement.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("Title");
			String genre = rs.getString("Genre");
			int year = rs.getInt("Year");

			// System.out.println(id + " " + title + " " + genre + " " + year);
			Dvd dvd = new Dvd(id, title, genre, year);
			allDvds.add(dvd);
		}
		return allDvds;

	}

	public void insertDvd(Dvd dvd) throws SQLException {

		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "INSERT INTO collection ( Title, Genre, Year)" + " VALUES ( '" + dvd.getTitle() + "', '"
				+ dvd.getGenre() + "', '" + dvd.getYear() + "' );";

		System.out.println(sql);

		statement.executeUpdate(sql);
		System.out.println("done");
		connection.close();
	}

	public void updateDvd(Dvd dvd) throws SQLException {

		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "UPDATE collection" + " SET Title = '" + dvd.getTitle() + "', Genre = '" + dvd.getGenre()
				+ "', Year = " + dvd.getYear() + " " + " WHERE ID = " + dvd.getId() + ";";

		System.out.println(sql);

		statement.executeUpdate(sql);
		System.out.println("update done");
		connection.close();
	}

	public void deleteDvd(Dvd dvd) throws SQLException {

		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "DELETE FROM collection WHERE ID = " + dvd.getId() + ";";

		System.out.println(sql);

		statement.executeUpdate(sql);
		System.out.println("delete done");
		connection.close();
	}

	public User ValidateUserInfo(String un, String pw) throws SQLException {

		Connection connection = getConnection();
	
		//' or 1=1 LIMIT 1;--';
		String sql = "SELECT username, password, apikey, COUNT(*) as valid FROM Users WHERE username = ? AND password = ?;";
		System.out.println(sql);

		PreparedStatement statement = connection.prepareStatement(sql);
		
		// add variables to the prepared statnent 
		statement.setString(1, un);
		statement.setString(2, pw);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			int valid = rs.getInt("valid");
			if (valid > 0) {
				System.out.println("valid user");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String api = rs.getString("apikey");
				// all good
				connection.close();
				return new User(username, password, api);
			}
		}
		connection.close();
		return null;

	}

	public void insertUser(User user) throws SQLException {
		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "INSERT INTO users ( username, password, apikey)" + " VALUES ( '" + user.getUsername() + "', '"
				+ user.getPassword() + "', '" + user.getApi() + "' );";
		System.out.println(sql);

		statement.executeUpdate(sql);
		
		System.out.println("done");
		connection.close();
	}

	public boolean checkKey(String apiKey) throws SQLException {
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		String sql = "SELECT COUNT(*) as valid FROM users WHERE apiKey = '" + apiKey + "';";
		System.out.println(sql);
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			int valid = rs.getInt("valid");
			if (valid > 0) {
				// all good
				connection.close();
				return true;
			}
		}
		connection.close();
		return false;

	}
}
