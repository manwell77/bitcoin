package bitcoin;

import java.net.Proxy;
import java.util.Timer;
import java.util.TimerTask;

public class db_fill {    
    
	public static void main(String[] args) {
    
		try { 
//          build task			
			TimerTask task = new RestTask("bitcoin","manwell77","1sjus7m3",Proxy.Type.HTTP,"192.168.201.3",3128);
//          no period -> simply run			
			if (args.length == 0) {	
				task.run(); 
			} 
//	        period specified-> schedule				
			else {     		    	 
				Timer timer = new Timer();	    	    	
				timer.schedule(task, 1000,(long) Long.parseLong((String) args[0]));
			}
		}	    	
		
	    catch (Exception e) {	
		  e.printStackTrace();		 
		 } 			  
	}
}