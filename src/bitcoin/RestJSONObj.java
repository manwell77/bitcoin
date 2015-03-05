package bitcoin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class RestJSONObj {
	
	private String name = null;
	private String currency = null;
	private Proxy proxy = null;
	private String login = null;
	private URL url = null;	
	private HttpURLConnection httpconn = null;
	
//  no proxy	
	public RestJSONObj(String name, String curr, String service_url) throws Exception { 
		set_name(name);
		currency = curr;
		url = new URL(service_url); 
	}
//  proxy	
    public RestJSONObj(String name, String curr, String service_url, Type proxy_type, String proxy_address, int proxy_port) throws Exception { 
    	set_name(name);
    	currency = curr;
		url = new URL(service_url);
		proxy =  new Proxy(proxy_type, new InetSocketAddress(proxy_address,proxy_port));       				    		    			   	
    }
//  authenticated proxy    
    public RestJSONObj(String name, String curr, String service_url, Type proxy_type, String proxy_address, int proxy_port, String proxy_user, String proxy_password) throws Exception {            	
    	set_name(name);
    	currency = curr;
    	url = new URL(service_url);
		proxy =  new Proxy(proxy_type, new InetSocketAddress(proxy_address,proxy_port));       		
		String credentials = proxy_user + ":" + proxy_password;
		login = new String(Base64.encode(credentials.getBytes()));    		    		   	
    }
//  connect
    private void connect( ) throws Exception {    	  
    	  if (proxy!=null) 
    	  	{ httpconn = (HttpURLConnection) url.openConnection(proxy); }
    	  else
    	  	{ httpconn = (HttpURLConnection) url.openConnection(); }
		  httpconn.setRequestMethod("GET");
		  httpconn.setRequestProperty("Accept", "application/json");	
		  if (login!=null) { httpconn.setRequestProperty("Proxy-Authorization", "Basic " + login); }
		  if (httpconn.getResponseCode() != 200) { throw new RuntimeException("Failed : HTTP error code : " + httpconn.getResponseCode()); }
    }
//  get JSON
    public JSONObject get_jsonobj( ) throws Exception {
    	String output;
    	StringBuilder builder = new StringBuilder();    	
    	connect( );
    	get_timestamp( );
    	BufferedReader br = new BufferedReader(new InputStreamReader((httpconn.getInputStream())));
    	while ((output = br.readLine()) != null) { builder.append(output); }
		JSONParser parser = new JSONParser();		
		JSONObject obj = (JSONObject) parser.parse(builder.toString());	
		obj.put("name", name );
		obj.put("currency", currency );
		obj.put("timestamp", get_timestamp( ) );
		httpconn.disconnect();
		return obj;		   	
    } 
//  set timestamp
    private String get_timestamp( ) throws Exception {
	  Date dt = new java.util.Date();
	  SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  return sdf.format(dt);    	
    }
    
//  set name
    private void set_name(String value) {
    	name = value;
    }
    
    public String get_name( ) {
    	return name;
    }    
}
