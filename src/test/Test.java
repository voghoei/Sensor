//package test;
//
//import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Locale;
//import java.util.TimeZone;
//
//import edu.uga.scouts.uhi.control.DataConnection;
//
//public class Test {
//
//	public static void main(String[] args) throws SQLException {
//		DataConnection dc=  new DataConnection();	  
//			  int id = dc.fetchID("iuiu");
//
//
//			  double latitude=10;
//			  double longitude=11.5; 
//			  double altitude=12.3; 
//			  double h_accuracy=12.44;
//			  double v_accuracy=343; 
//			  double temperature=232;
//			  double humidity=32; 
//			  double heat_index=65; 
//			  double battery_level=23;
//			  double rssi=239; 
//			  double dew_point=747; 
//			  double user_id=id; 
//
//			  java.util.Date dt = new java.util.Date();			  
//				
//			  java.sql.Timestamp sqlDate = new java.sql.Timestamp (dt.getTime());
//				
//			  dc.dataInsert(latitude,longitude,altitude,h_accuracy,v_accuracy,temperature,humidity,heat_index,battery_level,rssi,dew_point,user_id,sqlDate);
//
//			  System.out.println(id);
//		   
//
//	}
//
//}
