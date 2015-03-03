package bitcoin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.json.simple.JSONObject;

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
    
    public void insert_ticker(JSONObject json_ticker) throws Exception {
    	open( );
    	Statement stmt = dbconn.createStatement();
    	String ts = (String) json_ticker.get("timestamp");
    	String curr = (String) json_ticker.get("currency");
		float buy = (float) Float.parseFloat(new String((String) json_ticker.get("buy").toString()));
		float sell = (float) Float.parseFloat(new String((String) json_ticker.get("sell").toString()));
		float low = (float) Float.parseFloat(new String((String) json_ticker.get("low").toString()));
		float high = (float) Float.parseFloat(new String((String) json_ticker.get("high").toString()));
		float last = (float) Float.parseFloat(new String((String) json_ticker.get("last").toString()));
		float vol = (float) Float.parseFloat(new String((String) json_ticker.get("vol").toString()));		 
        String sql = "INSERT INTO ticker " + "VALUES ('" + ts + "','" + curr + "'," + buy + "," + sell + "," + low + "," + high + "," + last + "," + vol + ")";			  
        stmt.executeUpdate(sql);  
        close( );
    }
}
