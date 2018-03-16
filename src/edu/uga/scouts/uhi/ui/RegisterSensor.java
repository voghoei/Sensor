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

/**
 * Servlet implementation class RegisterSensor
 */
@WebServlet("/RegisterSensor")
public class RegisterSensor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterSensor() {
        super();
        // TODO Auto-generated constructor stub
    }	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {

			String jsonStr = request.getReader().lines().collect(Collectors.joining());
			JSONObject obj = new JSONObject(jsonStr);			

			String ssid = obj.getString("ssid");
			DataConnection dc = new DataConnection();
			boolean status = dc.registerSensor(ssid);			

			response.getWriter().write("{\"status\":" + status + "}");

		} catch (SQLException |JSONException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException(e);
		} 
	}

}
