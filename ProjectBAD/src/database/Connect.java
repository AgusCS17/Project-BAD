package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	
	private final String HOST="localhost:3306";
	private final String USERNAME ="root";
	private final String PASSWORD ="";
	private final String DATABASE ="carethree_db";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
//	carethree_db

	private Connection con;
	private Statement st;
	private static Connect connect;
	public ResultSet rs;
	public ResultSetMetaData rsm;
	
	public ResultSet findAllRegisterData() {
		return executeQuery("SELECT * FROM carts");
	}
	
	private Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			con.createStatement();
			st = con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 public String getIdUsers(String email, String password)
	    {
	     String Query = "SELECT id FROM users WHERE email = ? AND password = ?";
	     String id;
	     
	     try {
	   PreparedStatement ps = con.prepareStatement(Query);
	   ps.setString(1, email);
	   ps.setString(2, password);
	   
	   System.out.println(ps);
	   
	   ResultSet rs = ps.executeQuery();
	   
	   if(rs.next())
	   {
	    id = rs.getString("id");
	    return id;
	   }
	   
	  } catch (SQLException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	     
	        return null;
	    }
	
	public static synchronized Connect getConnection() {
		if(connect == null) {
			return new Connect();
		}
		return connect;
	}
	
	// for SELECT query in database
	public ResultSet executeQuery(String query) {
		rs = null;
		try {
			rs = st.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	} 
	
	// for UPDTAE, DELETE, INSERT query in database
	public Integer executeUpdate(String query) {
		Integer res = null;
		try {
			res = st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public PreparedStatement prepare(String query) {
		PreparedStatement ps = null;
		// alt + shift + z
		try {
			ps = con.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}
	

}
