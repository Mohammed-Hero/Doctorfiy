package Model;


import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Hero
 */
public class DBconnection {
    
String Server_Name = "root" , Password = "admin" , DB_Name = "doctorfiy" ;
    String JDBC_Url = "jdbc:mysql://localhost:3306/" + DB_Name ;
    String EngineName , EngineVer; 
    public String Query;
    
    DatabaseMetaData DBMetaData;
    Connection Conn;
    Statement Stmt;
  
    public ResultSet Rs;
    
    
    public DBconnection()
    {
      try 
      {
        Class.forName("com.mysql.jdbc.Driver");
      }
      catch(ClassNotFoundException e)
      {
        out.println( e.getMessage() );
        out.println("Class Not Found Error");
      }
    }
    
    public void Make_Connection( boolean Connection_State)
    {
      if( Connection_State == true )
      {
        try 
        {  
          Conn = DriverManager.getConnection( JDBC_Url , Server_Name , Password ); 
          DBMetaData = Conn.getMetaData();
          EngineName=DBMetaData.getDatabaseProductName();
          EngineVer=DBMetaData.getDatabaseProductVersion();
        }    
        catch(SQLException e)
        {
          out.println( e.getMessage() );
          out.println( e.getSQLState() );
          out.println("Connection Cannot Be Open / Created");
        }
      }
      else if( Connection_State == false )
      {
        try 
        {
          Conn.close();
        } 
        catch (SQLException ex) 
        {
          Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
          out.println("Connection Cannot Be Closed");
        }
      }
    }
    
     public void Execute_Query(int QueryType)
     {
        try 
        { 
          Stmt = Conn.createStatement();
          switch (QueryType) 
            {
                // Select Statment
                case 1:
                    Rs = Stmt.executeQuery( Query );

                    break;
                // Update - Insert Statment
                case 2:
                    Stmt.executeUpdate(Query );
                    break;
                default:
                    out.println("Query Switch Error.");
                    break;
            }
        } 
        catch (SQLException ex) 
        { 
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            
        }
     }
     
     public void Close_Connection_Of(int Index)
     {
       try 
       {   
         if( Index == 1 )
            Conn.close();
         else if(Index == 2)
            Stmt.close();
         else if (Index == 3)
            Rs.close();
       } 
       catch (SQLException ex) 
       {
           Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
       }
     }
     
    }
