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
	private String proxyuser;
	private String proxypassword;
	
	public RestTask(String db_name, String db_user, String db_password, Type proxy_type, String proxy_address, int proxy_port, String proxy_user, String proxy_password) {
		dbname = db_name;
		dbuser = db_user;
		dbpassword = db_password;
		proxytype = proxy_type;
		proxyaddress = proxy_address;
		proxyport = proxy_port;
		proxyuser = proxy_user;
		proxypassword = proxy_password;
	}
	
	public RestTask(String db_name, String db_user, String db_password, Type proxy_type, String proxy_address, int proxy_port) {
		dbname = db_name;
		dbuser = db_user;
		dbpassword = db_password;
		proxytype = proxy_type;
		proxyaddress = proxy_address;
		proxyport = proxy_port;
	}	
	
	public RestTask(String db_name, String db_user, String db_password) {
		dbname = db_name;
		dbuser = db_user;
		dbpassword = db_password;
	}
	
	public void run() {

		try { 

			  RestJSONObj btc_ticker = null;
			  RestJSONObj ltc_ticker = null; 
			  RestJSONObj btc_mdepth = null; 
			  RestJSONObj ltc_mdepth = null; 
			  RestJSONArray btc_thistory = null; 
			  RestJSONArray ltc_thistory = null;
			  
//	          db connection			
			  jsontodb jsontodb = new jsontodb(Inet4Address.getLocalHost().getHostAddress(),3306,dbname,dbuser,dbpassword);
			  
//            build json objects
			  if (proxytype!=null && proxyuser!=null) {
				  btc_ticker = new RestJSONObj("Ticker","BTC","https://www.okcoin.com/api/ticker.do?ok=1",proxytype,proxyaddress,proxyport,proxyuser,proxypassword);
				  ltc_ticker = new RestJSONObj("Ticker","LTC","https://www.okcoin.com/api/ticker.do?symbol=ltc_usd&ok=1",proxytype,proxyaddress,proxyport,proxyuser,proxypassword);
				  btc_mdepth = new RestJSONObj("MarketDepth","BTC","https://www.okcoin.com/api/depth.do?ok=1",proxytype,proxyaddress,proxyport,proxyuser,proxypassword);
				  ltc_mdepth = new RestJSONObj("MarketDepth","LTC","https://www.okcoin.com/api/depth.do?symbol=ltc_usd&ok=1",proxytype,proxyaddress,proxyport,proxyuser,proxypassword);
				  btc_thistory = new RestJSONArray("TradeHistory","BTC","https://www.okcoin.com/api/trades.do?since=5000&ok=1",proxytype,proxyaddress,proxyport,proxyuser,proxypassword);
				  ltc_thistory = new RestJSONArray("TradeHistory","LTC","https://www.okcoin.com/api/trades.do?symbol=ltc_usd&since=5000&ok=1",proxytype,proxyaddress,proxyport,proxyuser,proxypassword);
			  }
			  else if (proxytype!=null && proxyuser==null) {
				  btc_ticker = new RestJSONObj("Ticker","BTC","https://www.okcoin.com/api/ticker.do?ok=1",proxytype,proxyaddress,proxyport);
				  ltc_ticker = new RestJSONObj("Ticker","LTC","https://www.okcoin.com/api/ticker.do?symbol=ltc_usd&ok=1",proxytype,proxyaddress,proxyport);
				  btc_mdepth = new RestJSONObj("MarketDepth","BTC","https://www.okcoin.com/api/depth.do?ok=1",proxytype,proxyaddress,proxyport);
				  ltc_mdepth = new RestJSONObj("MarketDepth","LTC","https://www.okcoin.com/api/depth.do?symbol=ltc_usd&ok=1",proxytype,proxyaddress,proxyport);
				  btc_thistory = new RestJSONArray("TradeHistory","BTC","https://www.okcoin.com/api/trades.do?since=5000&ok=1",proxytype,proxyaddress,proxyport);
				  ltc_thistory = new RestJSONArray("TradeHistory","LTC","https://www.okcoin.com/api/trades.do?symbol=ltc_usd&since=5000&ok=1",proxytype,proxyaddress,proxyport);				  
			  }
			  else {
				  btc_ticker = new RestJSONObj("Ticker","BTC","https://www.okcoin.com/api/ticker.do?ok=1");
				  ltc_ticker = new RestJSONObj("Ticker","LTC","https://www.okcoin.com/api/ticker.do?symbol=ltc_usd&ok=1");
				  btc_mdepth = new RestJSONObj("MarketDepth","BTC","https://www.okcoin.com/api/depth.do?ok=1");
				  ltc_mdepth = new RestJSONObj("MarketDepth","LTC","https://www.okcoin.com/api/depth.do?symbol=ltc_usd&ok=1");
				  btc_thistory = new RestJSONArray("TradeHistory","BTC","https://www.okcoin.com/api/trades.do?since=5000&ok=1");
				  ltc_thistory = new RestJSONArray("TradeHistory","LTC","https://www.okcoin.com/api/trades.do?symbol=ltc_usd&since=5000&ok=1");
			  }
			  
//	          btc ticker table insert						  
			  JSONObject json_btc_ticker = btc_ticker.get_jsonobj();		  
			  jsontodb.jsonobj_insert(json_btc_ticker);			  
//	          ltc ticker table insert		  			  
			  JSONObject json_ltc_ticker = ltc_ticker.get_jsonobj();
			  jsontodb.jsonobj_insert(json_ltc_ticker);			  
//	          btc marketdepth table insert						  
			  JSONObject json_btc_mdepth = btc_mdepth.get_jsonobj();		  
			  jsontodb.jsonobj_insert(json_btc_mdepth);			  
//	          ltc marketdepth table insert						  
			  JSONObject json_ltc_mdepth = ltc_mdepth.get_jsonobj();		  
			  jsontodb.jsonobj_insert(json_ltc_mdepth);		  			  
//	          btc trade history			  
			  JSONArray json_btc_thistory = btc_thistory.get_jsonarray();
			  jsontodb.jsonarray_insert(json_btc_thistory);			  	
//	          ltc trade history			  
			  JSONArray json_ltc_thistory = ltc_thistory.get_jsonarray();
			  jsontodb.jsonarray_insert(json_ltc_thistory);			  
			} 		  
		    
		    catch (Exception e) {	
			  e.printStackTrace();		 
			  } 		
	}
	
}
