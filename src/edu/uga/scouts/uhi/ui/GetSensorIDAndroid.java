package edu.uga.scouts.uhi.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import edu.uga.scouts.uhi.control.DataConnection;

@WebServlet("/GetSensorIDAndroid")
public class GetSensorIDAndroid extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public GetSensorIDAndroid() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String jsonStr = request.getReader().lines().collect(Collectors.joining());
			JSONObject obj = new JSONObject(jsonStr);			

			int id = obj.getInt("id");
			DataConnection dc = new DataConnection();
			String MACAddress = dc.fetchMACAddress(id);
			response.getWriter().write("{\"MACAddress\":\"" + MACAddress + "\"}");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
