package bitcoin;

import java.net.Proxy;

import org.json.simple.JSONObject;

public class db_fill {    
    
	public static void main(String[] args) {
    
		try { 
//			
//		  Connection dbconn = null;           
//		  Statement stmt = null;
//		  StringBuilder builder = new StringBuilder();
//		  
//	      dbconn = DriverManager.getConnection("jdbc:mysql://10.254.0.110:3306/bitcoin?" + "user=manwell77&password=1sjus7m3"); 		
		
				  
		  ticker ticker = new ticker("BTC","https://www.okcoin.com/api/ticker.do?ok=1",Proxy.Type.HTTP,"192.168.201.3",3128);
		  JSONObject json_ticker = ticker.get_jsonobj();
		  System.out.println("timestamp: " + json_ticker.get("timestamp"));
		  System.out.println("currency: " + json_ticker.get("currency"));
		  System.out.println("buy: " + json_ticker.get("buy"));
		  System.out.println("sell: " + json_ticker.get("sell"));
		  System.out.println("low: " + json_ticker.get("low"));
		  System.out.println("high: " + json_ticker.get("high"));
		  System.out.println("last: " + json_ticker.get("last"));
		  System.out.println("volume: " + json_ticker.get("vol"));		  		  		 
		  
//		  Object obj = parser.parse(builder.toString());
//		  JSONArray jsonArray = (JSONArray) obj;
		  
//		  for(int n = 0; n < jsonArray.size(); n++)
//		  {
//		      JSONObject jsonObject = (JSONObject) jsonArray.get(n);
//		      
//		      String code = (String) jsonObject.get("code");
//		      String name = (String) jsonObject.get("name");
//		      float  rate = Float.parseFloat(new String((String) jsonObject.get("rate").toString()));
//			  //stmt = dbconn.createStatement();		
//		      //String sql = "INSERT INTO rates " + "VALUES ('" + currentTime + "','" + code + "'," + rate + ")";	
//		      //stmt.executeUpdate(sql);
//		      System.out.println(code);
//		  }
		  		
		} 		  
	    
	    catch (Exception e) {	
		  e.printStackTrace();		 
		  } 	
		  
	}
}