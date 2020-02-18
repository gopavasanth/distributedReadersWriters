import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class DistributedRemote extends UnicastRemoteObject implements Distributed{
	
	int lockOn=1,lockOff=0;
	int _lock = lockOff;
	int reset=0,stop=-1,reading=1,pause=2;
	int read[] = {0, 0, 0};
	DistributedRemote()throws RemoteException{
	super();
	}

	public void readingControl(int i,String val){
		if(val.equals("start"))
			reading(i);
		else if(val.equals("stop"))
		{
			stopReading(i);
		}	
		else if(val.equals("pause"))
		{
			System.out.println("*****************Reader "+i+" paused reading*****************");
			read[i] = pause;
		}
		else if(val.equals("resume"))
		{
			System.out.println("*****************Reader "+i+" resumed reading*****************");
			read[i]=reading;
		}
	}
	public void stopReading(int i){
		read[i]=stop;
	}
	public void lock(){
		System.out.print("*****************Writer started writing, Reader ");
		for(int i = 1 ; i <= 2 ; i++)		
		{
			if(read[i]==reading || read[i]==pause)
				System.out.print(i+" ");
		}
		System.out.print("on hold*****************\n");
		_lock = lockOn;
	}
	public void release(){
		if(read[1]==reading)
			read[1]=reset;
		if(read[2]==reading)
			read[2]=reset;
		_lock = lockOff;
		System.out.println("*****************Writer finished writing*****************");
	}
	public void writing(String input){
		try{    
		   FileWriter fw=new FileWriter("content",true);	    
		   fw.write(input);    
		   fw.close();    
		  }catch(Exception e){System.out.println(e);}    
		System.out.println(input+" Written successfully...");      
	}
	public void reading(int i){
		
		try {
		File file = new File("content"); 
	    	Scanner sc = new Scanner(file); 
	
	   		while (sc.hasNext()) 
			{
				if(_lock==lockOff)
				{
					if(read[i]==stop)
					{
						read[i]=reset;
						System.out.println("*****************Reader "+i+" reading incomplete*****************");
						break;
					}
					else if(read[i] == reset)
					{
						System.out.println("*****************Reader "+i+" started reading*****************");
						read[i]=reading;
					}
					
					else if(read[i]==reading)
					{
						System.out.println("Reader "+i+" reads "+sc.next());
						TimeUnit.SECONDS.sleep(2);
					}
				}
			}
		}catch(Exception e){System.out.println(e);}
		System.out.println("*****************Reader "+i+" exits*****************");
	}

}
