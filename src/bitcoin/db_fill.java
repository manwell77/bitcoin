package bitcoin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.Timer;
import java.util.TimerTask;

public class db_fill {    
    
	public static void main(String[] args) {
		
		String dbschema = null;
		String dbuser = null;
		String dbpassword = null;
		Type proxytype = null;
		String proxyaddress = null;
		String proxyuser = null;
		String proxypassword = null;
		int proxyport = 0;
		int period = 0;
		TimerTask task = null;
		
		try { 
//          read ini file
			BufferedReader br = new BufferedReader(new FileReader("src/bitcoin/bitcoin.cfg"));
		    String line = br.readLine();   
//          set startup configuration		    
	        while (line!=null) {
	        	switch (line.substring(0,line.indexOf("="))) {
	        		case "db_schema":
	        			dbschema = line.substring(line.lastIndexOf("=")+1);
	        			break;
	        		case "db_user":
	        			dbuser = line.substring(line.lastIndexOf("=")+1);
	        			break;
	        		case "db_password":
	        			dbpassword = line.substring(line.lastIndexOf("=")+1);
	        			break;	        			
	        		case "proxy_type":
	        			switch (line.substring(line.lastIndexOf("=")+1)) {
	        				case "http": 
	        					proxytype = Proxy.Type.HTTP; 
	        					break;
	        				case "socks": 
	        					proxytype = Proxy.Type.SOCKS; 
	        					break;
	        			}
	        			break;
	        		case "proxy_address":
	        			proxyaddress = line.substring(line.lastIndexOf("=")+1);
	        			break;	 
	        		case "proxy_port":
	        			proxyport = (int) Integer.parseInt((String) line.substring(line.lastIndexOf("=")+1));
	        			break;		
	        		case "proxy_user":
	        			proxyuser = line.substring(line.lastIndexOf("=")+1);
	        			break;	
	        		case "proxy_password":
	        			proxypassword = line.substring(line.lastIndexOf("=")+1);
	        			break;		        			
	        		case "period":
	        			period = (int) Integer.parseInt((String) line.substring(line.lastIndexOf("=")+1));
	        			break;		        			
	        	} 
	        	line = br.readLine();
	        }	
	        br.close();
//          build task			
	        if (proxytype!=null && proxyuser!=null) {
	        	task = new RestTask(dbschema,dbuser,dbpassword,proxytype,proxyaddress,proxyport,proxyuser,proxypassword);
	        }
	        else if (proxytype!=null && proxyuser==null) {
	        	task = new RestTask(dbschema,dbuser,dbpassword,proxytype,proxyaddress,proxyport);	
	        }
	        else {
	        	task = new RestTask(dbschema,dbuser,dbpassword);	
	        }
//          no period -> simply run			
			if (period == 0) {	
				task.run(); 
			} 
//	        period specified-> schedule				
			else {     		    	 
				Timer timer = new Timer();	    	    	
				timer.schedule(task, 1000,period);
			}
		}	    			
	    catch (Exception e) {	
		  e.printStackTrace();		 
		 } 			  
	}
}