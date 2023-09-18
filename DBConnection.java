 import java.sql.*;
class DBConnection{
    public static Connection getDatabaseConnection(){
        Connection con=null;
        String username="root";
        String password="root";
        String url="jdbc:mysql://localhost:3306/mydatabase";

        String driver="com.mysql.jdbc.Driver";
        try
        {
            Class.forName(driver); 
            try
            {
                con=DriverManager.getConnection(url,username,password);
            }
            catch(SQLException ex)
            {

            }
        }
        catch(ClassNotFoundException ex){

        }
        return con;
    }
}