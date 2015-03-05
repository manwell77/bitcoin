package bitcoin;

import java.net.Inet4Address;
import java.net.Proxy.Type;
import java.util.TimerTask;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RestTask extends TimerTask {
	
	private String dbname;
	private String dbuser;
	private String dbpassword;
	private Type proxytype;
	private String proxyaddress;
	private int proxyport;
	
	public RestTask(String db_name, String db_user, String db_password, Type proxy_type, String proxy_address, int proxy_port) {
		dbname = db_name;
		dbuser = db_user;
		dbpassword = db_password;
		proxytype = proxy_type;
		proxyaddress = proxy_address;
		proxyport = proxy_port;
	}
	
	public void run() {

		try { 

//	          db connection			
			  jsontodb jsontodb = new jsontodb(Inet4Address.getLocalHost().getHostAddress(),3306,dbname,dbuser,dbpassword);
			  
//	          btc ticker table insert			
			  RestJSONObj btc_ticker = new RestJSONObj("Ticker","BTC","https://www.okcoin.com/api/ticker.do?ok=1",proxytype,proxyaddress,proxyport);
			  JSONObject json_btc_ticker = btc_ticker.get_jsonobj();		  
			  jsontodb.jsonobj_insert(json_btc_ticker);
			  
//	          ltc ticker table insert		  
			  RestJSONObj ltc_ticker = new RestJSONObj("Ticker","LTC","https://www.okcoin.com/api/ticker.do?symbol=ltc_usd&ok=1",proxytype,proxyaddress,proxyport);
			  JSONObject json_ltc_ticker = ltc_ticker.get_jsonobj();
			  jsontodb.jsonobj_insert(json_ltc_ticker);
			  
//	          btc marketdepth table insert			
			  RestJSONObj btc_mdepth = new RestJSONObj("MarketDepth","BTC","https://www.okcoin.com/api/depth.do?ok=1",proxytype,proxyaddress,proxyport);
			  JSONObject json_btc_mdepth = btc_mdepth.get_jsonobj();		  
			  jsontodb.jsonobj_insert(json_btc_mdepth);
			  
//	          ltc marketdepth table insert			
			  RestJSONObj ltc_mdepth = new RestJSONObj("MarketDepth","LTC","https://www.okcoin.com/api/depth.do?symbol=ltc_usd&ok=1",proxytype,proxyaddress,proxyport);
			  JSONObject json_ltc_mdepth = ltc_mdepth.get_jsonobj();		  
			  jsontodb.jsonobj_insert(json_ltc_mdepth);		  
			  
//	          btc trade history
			  RestJSONArray btc_thistory = new RestJSONArray("TradeHistory","BTC","https://www.okcoin.com/api/trades.do?since=5000&ok=1",proxytype,proxyaddress,proxyport);
			  JSONArray json_btc_thistory = btc_thistory.get_jsonarray();
			  jsontodb.jsonarray_insert(json_btc_thistory);
			  	
//	          ltc trade history
			  RestJSONArray ltc_thistory = new RestJSONArray("TradeHistory","LTC","https://www.okcoin.com/api/trades.do?symbol=ltc_usd&since=5000&ok=1",proxytype,proxyaddress,proxyport);
			  JSONArray json_ltc_thistory = ltc_thistory.get_jsonarray();
			  jsontodb.jsonarray_insert(json_ltc_thistory);
			  
			} 		  
		    
		    catch (Exception e) {	
			  e.printStackTrace();		 
			  } 		
	}
	
}
