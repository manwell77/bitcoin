package bitcoin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class db_fill {
    
	public static void main(String[] args) {
    
		try { 
			
		  Connection dbconn = null;           
		  Statement stmt = null;
		  StringBuilder builder = new StringBuilder();
		  
	      dbconn = DriverManager.getConnection("jdbc:mysql://10.254.0.110:3306/bitcoin?" + "user=manwell77&password=1sjus7m3"); 		
			

	      Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.201.3",3128));		
				  
		  URL url = new URL("https://bitpay.com/api/rates");
		  
		  Date dt = new java.util.Date();
		  SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String currentTime = sdf.format(dt);
		  
		  HttpURLConnection httpconn = (HttpURLConnection) url.openConnection(proxy);
		  httpconn.setRequestMethod("GET");
		  httpconn.setRequestProperty("Accept", "application/json");
		
		  if (httpconn.getResponseCode() != 200) {
		    throw new RuntimeException("Failed : HTTP error code : " + httpconn.getResponseCode());
		  }
		
		  BufferedReader br = new BufferedReader(new InputStreamReader((httpconn.getInputStream())));
		
		  String output;
		  System.out.println("Output from Server .... \n");
		  
		  while ((output = br.readLine()) != null) {	
		    builder.append(output);
		    
		    	     
		  }
		  		  
		  JSONParser parser = new JSONParser();
		  Object obj = parser.parse(builder.toString());
		  JSONArray jsonArray = (JSONArray) obj;
		  
		  for(int n = 0; n < jsonArray.size(); n++)
		  {
		      JSONObject jsonObject = (JSONObject) jsonArray.get(n);
		      
		      String code = (String) jsonObject.get("code");
		      String name = (String) jsonObject.get("name");
		      float  rate = Float.parseFloat(new String((String) jsonObject.get("rate").toString()));
			  stmt = dbconn.createStatement();		
		      String sql = "INSERT INTO rates " + "VALUES ('" + currentTime + "','" + code + "'," + rate + ")";	
		      stmt.executeUpdate(sql);
		      System.out.println(code);
		  }
		  

	      
		  httpconn.disconnect();
		
		} 
		  
		catch (SQLException sqlex) {			
			System.out.println("SQLException: " + sqlex.getMessage());
	        System.out.println("SQLState: " + sqlex.getSQLState());
	        System.out.println("VendorError: " + sqlex.getErrorCode()); 
	        }	
	    
	    catch (Exception e) {	
		  e.printStackTrace();		 
		  } 	
		  
	}
}