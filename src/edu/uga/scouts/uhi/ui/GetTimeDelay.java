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

@WebServlet("/GetTimeDelay")
public class GetTimeDelay extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public GetTimeDelay() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			DataConnection dc = new DataConnection();
			int time_delay = dc.fetchTimeDelay();
			response.getWriter().write("{\"time_delay\":" + time_delay + "}");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
