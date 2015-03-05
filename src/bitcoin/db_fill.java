package bitcoin;

import java.net.Inet4Address;
import java.net.Proxy;
import org.json.simple.JSONObject;

public class db_fill {    
    
	public static void main(String[] args) {
    
		try { 

//        db connection			
		  jsontodb jsontodb = new jsontodb(Inet4Address.getLocalHost().getHostAddress(),3306,"bitcoin","manwell77","1sjus7m3");
		  
//        btc ticker table insert			
		  RestJSONObj btc_ticker = new RestJSONObj("Ticker","BTC","https://www.okcoin.com/api/ticker.do?ok=1",Proxy.Type.HTTP,"192.168.201.3",3128);
		  JSONObject json_btc_ticker = btc_ticker.get_jsonobj();		  
		  jsontodb.insert(json_btc_ticker);
		  
//        ltc ticker table insert		  
		  RestJSONObj ltc_ticker = new RestJSONObj("Ticker","LTC","https://www.okcoin.com/api/ticker.do?symbol=ltc_usd&ok=1",Proxy.Type.HTTP,"192.168.201.3",3128);
		  JSONObject json_ltc_ticker = ltc_ticker.get_jsonobj();
		  jsontodb.insert(json_ltc_ticker);
		  
//        btc marketdepth table insert			
		  RestJSONObj btc_mdepth = new RestJSONObj("MarketDepth","BTC","https://www.okcoin.com/api/depth.do?ok=1",Proxy.Type.HTTP,"192.168.201.3",3128);
		  JSONObject json_btc_mdepth = btc_mdepth.get_jsonobj();		  
		  jsontodb.insert(json_btc_mdepth);
		  
//        ltc marketdepth table insert			
		  RestJSONObj ltc_mdepth = new RestJSONObj("MarketDepth","LTC","https://www.okcoin.com/api/depth.do?symbol=ltc_usd&ok=1",Proxy.Type.HTTP,"192.168.201.3",3128);
		  JSONObject json_ltc_mdepth = ltc_mdepth.get_jsonobj();		  
		  jsontodb.insert(json_ltc_mdepth);		  
		  
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