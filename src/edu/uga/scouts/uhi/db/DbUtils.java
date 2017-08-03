package edu.uga.scouts.uhi.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbUtils {


    public static void disableAutoCommit( Connection conn ) 
     
    {
	  try {
		conn.setAutoCommit(false);
	  } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
    }

    public static void enableAutoCommit( Connection conn ) 
    {
	  try {
		conn.setAutoCommit(true);
	  } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
    }

    public static void commit(Connection conn) 
            
    {
        try {
            conn.commit();
        } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
    }

    
    public static void rollback(Connection conn) 
           
    {
        try {
            conn.rollback();
        } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
    }

    
    public static Connection connect() throws ClassNotFoundException, SQLException 
           
    {
	  
		Class.forName(DbAccessConfig.DB_DRIVE_NAME);

		return DriverManager.getConnection( DbAccessConfig.DB_CONNECTION_URL,
		        DbAccessConfig.DB_CONNECTION_USERNAME,
		        DbAccessConfig.DB_CONNECTION_PWD );
	  

    }

}
