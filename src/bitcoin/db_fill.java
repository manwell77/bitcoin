package bitcoin;

import java.net.Inet4Address;
import java.net.Proxy;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class db_fill {    
    
	public static void main(String[] args) {
    
		try { 

//        db connection			
		  jsontodb jsontodb = new jsontodb(Inet4Address.getLocalHost().getHostAddress(),3306,"bitcoin","manwell77","1sjus7m3");
		  
//        btc ticker table insert			
		  RestJSONObj btc_ticker = new RestJSONObj("Ticker","BTC","https://www.okcoin.com/api/ticker.do?ok=1",Proxy.Type.HTTP,"192.168.201.3",3128);
		  JSONObject json_btc_ticker = btc_ticker.get_jsonobj();		  
		  jsontodb.jsonobj_insert(json_btc_ticker);
		  
//        ltc ticker table insert		  
		  RestJSONObj ltc_ticker = new RestJSONObj("Ticker","LTC","https://www.okcoin.com/api/ticker.do?symbol=ltc_usd&ok=1",Proxy.Type.HTTP,"192.168.201.3",3128);
		  JSONObject json_ltc_ticker = ltc_ticker.get_jsonobj();
		  jsontodb.jsonobj_insert(json_ltc_ticker);
		  
//        btc marketdepth table insert			
		  RestJSONObj btc_mdepth = new RestJSONObj("MarketDepth","BTC","https://www.okcoin.com/api/depth.do?ok=1",Proxy.Type.HTTP,"192.168.201.3",3128);
		  JSONObject json_btc_mdepth = btc_mdepth.get_jsonobj();		  
		  jsontodb.jsonobj_insert(json_btc_mdepth);
		  
//        ltc marketdepth table insert			
		  RestJSONObj ltc_mdepth = new RestJSONObj("MarketDepth","LTC","https://www.okcoin.com/api/depth.do?symbol=ltc_usd&ok=1",Proxy.Type.HTTP,"192.168.201.3",3128);
		  JSONObject json_ltc_mdepth = ltc_mdepth.get_jsonobj();		  
		  jsontodb.jsonobj_insert(json_ltc_mdepth);		  
		  
//        btc trade history
		  RestJSONArray btc_thistory = new RestJSONArray("TradeHistory","BTC","https://www.okcoin.com/api/trades.do?since=5000&ok=1",Proxy.Type.HTTP,"192.168.201.3",3128);
		  JSONArray json_btc_thistory = btc_thistory.get_jsonarray();
		  jsontodb.jsonarray_insert(json_btc_thistory);
		  	
//        ltc trade history
		  RestJSONArray ltc_thistory = new RestJSONArray("TradeHistory","LTC","https://www.okcoin.com/api/trades.do?symbol=ltc_usd&since=5000&ok=1",Proxy.Type.HTTP,"192.168.201.3",3128);
		  JSONArray json_ltc_thistory = ltc_thistory.get_jsonarray();
		  jsontodb.jsonarray_insert(json_ltc_thistory);
		  
		} 		  
	    
	    catch (Exception e) {	
		  e.printStackTrace();		 
		  } 	
		  
	}
}