package edu.uga.scouts.uhi.ui;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import edu.uga.scouts.uhi.control.DataConnection;
import jdk.nashorn.internal.runtime.ParserException;

@WebServlet("/SensorData")
public class SensorData extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public SensorData() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		double latitude = 0.0;
		double longitude = 0.0;
		double altitude = 0.0;
		double h_accuracy = 0.0;
		double v_accuracy = 0.0;
		double temperature = 0.0;
		double humidity = 0.0;
		double heat_index = 0.0;
		double battery_level = 0.0;
		double rssi = 0.0;
		double dew_point = 0.0;
		int user_id = 0;
		String time = "";
		java.sql.Timestamp  sqlDate = null;
		Boolean status = false;

		try {
			String jsonStr = request.getReader().lines().collect(Collectors.joining());

			JSONObject obj = new JSONObject(jsonStr);

			latitude = obj.getDouble("latitude");
			longitude = obj.getDouble("longitude");
			altitude = obj.getDouble("altitude");
			h_accuracy = obj.getDouble("h_accuracy");
			v_accuracy = obj.getDouble("v_accuracy");
			temperature = obj.getDouble("temperature");
			humidity = obj.getDouble("humidity");
			heat_index = obj.getDouble("heat_index");
			battery_level = obj.getDouble("battery_level");
			rssi = obj.getDouble("rssi");
			dew_point = obj.getDouble("dew_point");
			user_id = obj.getInt("user_id");
			time = obj.getString("time");
			
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
			format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

			java.util.Date date = format.parse(time);
			System.out.println("date: " + date);
			
			sqlDate = new java.sql.Timestamp (date.getTime());
			

			System.out.println("currentTime: " + sqlDate);

		} catch (ParserException | ParseException parseEx) {
			parseEx.printStackTrace();
			throw new ServletException(parseEx);
		}

		DataConnection dc = new DataConnection();

		status = dc.dataInsert(latitude, longitude, altitude, h_accuracy, v_accuracy, temperature, humidity,
				heat_index, battery_level, rssi, dew_point, user_id, sqlDate);

		System.out.println("status: " + status);

		response.getWriter().write("{\"status\"':" + status + "}");

	}

}
