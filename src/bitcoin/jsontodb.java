package bitcoin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class jsontodb {
	
	private String host = null;
    private Connection dbconn = null;           

    public jsontodb(String dbhost, int dbport, String db, String dbuser, String dbpassword) throws Exception {
    	host = "jdbc:mysql://" + dbhost + ":" + dbport + "/" + db + "?user=" + dbuser + "&password=" + dbpassword;     	 
    }
    
    private void open( ) throws Exception {
    	dbconn = DriverManager.getConnection(host);    	
    }
    
    private void close( ) throws Exception {
    	dbconn.close();    	
    }
    
    public void insert(JSONObject obj) throws Exception {
    	
    	open( );  
    	
    	Statement stmt = null;
    	String sql = null;
    	String ts = null;
    	String curr = null;
    	
    	switch ((String) obj.get("name")) {
    		
	    	case "Ticker":     	
	    		
		    	stmt = dbconn.createStatement();
		    	ts = (String) obj.get("timestamp");
		    	int date = (int) Integer.parseInt((String) obj.get("date"));
		    	curr = (String) obj.get("currency");
		    	JSONObject ticker = (JSONObject) obj.get("ticker");
				float buy = (float) Float.parseFloat(new String((String) ticker.get("buy").toString()));
				float sell = (float) Float.parseFloat(new String((String) ticker.get("sell").toString()));
				float low = (float) Float.parseFloat(new String((String) ticker.get("low").toString()));
				float high = (float) Float.parseFloat(new String((String) ticker.get("high").toString()));
				float last = (float) Float.parseFloat(new String((String) ticker.get("last").toString()));
				float vol = (float) Float.parseFloat(new String((String) ticker.get("vol").toString()));		 
		        sql = "INSERT INTO ticker " + "VALUES ('" + ts + "','" + curr + "'," + date + "," + buy + "," + sell + "," + low + "," + high + "," + last + "," + vol + ")";			  
		        stmt.executeUpdate(sql);  
		        break;
		        
	    	case "MarketDepth":    
	    		
 		    	stmt = dbconn.createStatement();
		    	ts = (String) obj.get("timestamp");
		    	curr = (String) obj.get("currency");
		    	JSONArray asks = (JSONArray) obj.get("asks");
		    	JSONArray bids = (JSONArray) obj.get("bids");
		    	
		    	for(int n = 0; n < asks.size(); n++)  {
		    		int count = n + 1;
		    		JSONArray ask = (JSONArray) asks.get(n);		    		
		    		float price = (float) Float.parseFloat(new String((String) ask.get(0).toString()));	
		    		float quantity = (float) Float.parseFloat(new String((String) ask.get(1).toString()));
			        sql = "INSERT INTO marketdepth " + "VALUES ('" + ts + "','" + curr + "','asks'," + count + "," + price + "," + quantity + ")";
			        stmt.executeUpdate(sql);		    		
		    	}		    	
		    	
		    	for(int n = 0; n < bids.size(); n++)  {		    		
		    		int count = n + 1;
		    		JSONArray bid = (JSONArray) bids.get(n);		    		
		    		float price = (float) Float.parseFloat(new String((String) bid.get(0).toString()));	
		    		float quantity = (float) Float.parseFloat(new String((String) bid.get(1).toString()));
			        sql = "INSERT INTO marketdepth " + "VALUES ('" + ts + "','" + curr + "','bids'," + count + "," + price + "," + quantity + ")";
			        stmt.executeUpdate(sql);
		    	}
 
		        break;
		        
    	}
        close( );
    }
}
