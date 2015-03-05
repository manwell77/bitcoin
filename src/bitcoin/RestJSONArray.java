package bitcoin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class RestJSONArray {
	
	private String name = null;
	private String currency = null;
	private Proxy proxy = null;
	private String login = null;
	private URL url = null;	
	private HttpURLConnection httpconn = null;
	
//  no proxy	
	public RestJSONArray(String name, String curr, String service_url) throws Exception { 
		set_name(name);
		currency = curr;
		url = new URL(service_url); 
	}
//  proxy	
    public RestJSONArray(String name, String curr, String service_url, Type proxy_type, String proxy_address, int proxy_port) throws Exception { 
    	set_name(name);
    	currency = curr;
		url = new URL(service_url);
		proxy =  new Proxy(proxy_type, new InetSocketAddress(proxy_address,proxy_port));       				    		    			   	
    }
//  authenticated proxy    
    public RestJSONArray(String name, String curr, String service_url, Type proxy_type, String proxy_address, int proxy_port, String proxy_user, String proxy_password) throws Exception {            	
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
    public JSONArray get_jsonarray( ) throws Exception {
    	String output;
    	StringBuilder builder = new StringBuilder();    	
    	connect( );
    	BufferedReader br = new BufferedReader(new InputStreamReader((httpconn.getInputStream())));
    	while ((output = br.readLine()) != null) { builder.append(output); }
		JSONParser parser = new JSONParser();		
		JSONArray arr = (JSONArray) parser.parse(builder.toString());	
		
    	for(int n = 0; n < arr.size(); n++)  {
    		JSONObject obj = (JSONObject) arr.get(n);		 
    		obj.put("name", name );
    		obj.put("currency", currency );    		
    	}				
		httpconn.disconnect();
		return arr;		   	
    }     
//  set name
    private void set_name(String value) {
    	name = value;
    }
//  get name   
    public String get_name( ) {
    	return name;
    }    
}
