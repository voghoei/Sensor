package edu.uga.scouts.uhi.db;

public abstract class DbAccessConfig
{
    /** The fully qualified name of the JDBC driver.
     */
    static final String DB_DRIVE_NAME  = "com.mysql.jdbc.Driver";
    
    /** The database name
     */
    static final String DB_NAME = "uhi";
    
    /** The database server name for the connection pool
     */
    static final String DB_SERVER_NAME = "35.185.94.88";

    /** The JDBC connection string/URL.
     */
    static final String DB_CONNECTION_URL ="jdbc:mysql://35.185.94.88:3306/uhi";


    /** The database user name.
     */
    static  String DB_CONNECTION_USERNAME = "root";

    /** The password for the database user.
     */
    static  String DB_CONNECTION_PWD = "A8dNEJwE";

}

