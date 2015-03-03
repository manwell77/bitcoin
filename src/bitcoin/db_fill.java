package bitcoin;

import java.net.Inet4Address;
import java.net.Proxy;
import org.json.simple.JSONObject;

public class db_fill {    
    
	public static void main(String[] args) {
    
		try { 

//        ticker db connection			
		  jsontodb jsontodb = new jsontodb(Inet4Address.getLocalHost().getHostAddress(),3306,"bitcoin","manwell77","1sjus7m3");
		  
//        btc ticker table insert			
		  ticker btc_ticker = new ticker("BTC","https://www.okcoin.com/api/ticker.do?ok=1",Proxy.Type.HTTP,"192.168.201.3",3128);
		  JSONObject json_btc_ticker = btc_ticker.get_jsonobj();		  
		  jsontodb.insert_ticker(json_btc_ticker);
		  
//        ltc ticker table insert		  
		  ticker ltc_ticker = new ticker("LTC","https://www.okcoin.com/api/ticker.do?symbol=ltc_usd&ok=1",Proxy.Type.HTTP,"192.168.201.3",3128);
		  JSONObject json_ltc_ticker = ltc_ticker.get_jsonobj();
		  jsontodb.insert_ticker(json_ltc_ticker);
		  
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