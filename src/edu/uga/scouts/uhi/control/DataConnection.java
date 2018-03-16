package edu.uga.scouts.uhi.control;

import java.sql.*;

import edu.uga.scouts.uhi.db.DbUtils;

public class DataConnection {

	private Connection conn = null;
	private PreparedStatement stmt = null;

	private void connect() throws ClassNotFoundException, SQLException {
		conn = DbUtils.connect();

	}

	private void close() {
		try {
			conn.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e);
		}
	}

	// register a sensor

	@SuppressWarnings("finally")
	public boolean registerSensor(String ssid) throws SQLException {
		try {
			connect();

			String sql = "select id from id_registration where ssid='" + ssid + "'";

			// retrieve the result
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet r = stmt.executeQuery();

			if (!r.next()) { // statement returned a result

				// Insert in Database
				String insertUser = "INSERT INTO id_registration (ssid) VALUES ('" + ssid + "')";
				stmt = (PreparedStatement) conn.prepareStatement(insertUser);
				stmt.executeUpdate();

				sql = "select id from id_registration where ssid='" + ssid + "'";
				stmt.execute(sql);
				r = stmt.getResultSet();

				// we will use only the first row!
				r.next();

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		} finally {
			close();
			System.out.println("connection closed.");
			return true;
		}
		
	}

	// SELECT FROM users IF Exists, retrieve otherwise create and retrieve
	// return 0 if connection fails

	public String fetchSSID(int id) throws SQLException {
		try {
			connect();

			String sql = "select ssid from id_registration where id=" + id ;

			// retrieve the result
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet r = stmt.executeQuery();

			if (r.next()) { // statement returned a result

				// we will use only the first row!
				return r.getString(1);

			} else {				
				return "0";
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		} finally {
			close();
			System.out.println("connection closed.");
		}

	}
	
	
	public String fetchMACAddress(int id) throws SQLException {
		try {
			connect();

			String sql = "select mac_address from id_registration where id=" + id ;

			// retrieve the result
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet r = stmt.executeQuery();

			if (r.next()) { // statement returned a result

				// we will use only the first row!
				return r.getString(1);

			} else {				
				return "0";
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		} finally {
			close();
			System.out.println("connection closed.");
		}

	}
	
	
	public int fetchTimeDelay() throws SQLException {
		try {
			connect();

			String sql = "select time_delay from settings where id=1" ;

			// retrieve the result
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet r = stmt.executeQuery();

			if (r.next()) { // statement returned a result

				// we will use only the first row!
				return r.getInt(1);

			} else {				
				return 0;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			close();
			System.out.println("connection closed.");
		}

	}
	

	public boolean dataInsert(double latitude, double longitude, double altitude, double h_accuracy, double v_accuracy,
			double temperature, double humidity, double heat_index, double battery_level, double rssi, double dew_point,
			double user_id, java.sql.Timestamp time) {

		try {
			connect();

			// Insert in Database

			String insertData = "INSERT INTO loc_temp (latitude, longitude, altitude, h_accuracy, v_accuracy, temperature, humidity, heat_index, battery_level, rssi, dew_point, user_id, time) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = (PreparedStatement) conn.prepareStatement(insertData);

			stmt.setDouble(1, latitude);
			stmt.setDouble(2, longitude);
			stmt.setDouble(3, altitude);
			stmt.setDouble(4, h_accuracy);
			stmt.setDouble(5, v_accuracy);
			stmt.setDouble(6, temperature);
			stmt.setDouble(7, humidity);
			stmt.setDouble(8, heat_index);
			stmt.setDouble(9, battery_level);
			stmt.setDouble(10, rssi);
			stmt.setDouble(11, dew_point);
			stmt.setDouble(12, user_id);
			stmt.setTimestamp(13, time);

			if (stmt.executeUpdate() >= 1) {
				System.out.println("return: " + true);
				return true;
			} else {
				System.out.println("return: " + false);
				return false;

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			close();
			System.out.println("connection closed.");
		}

	}

}
