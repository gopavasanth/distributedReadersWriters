import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class DistributedRemote extends UnicastRemoteObject implements Distributed{
	
	int _lock = 0;
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
			read[i] = 2;
		}
		else if(val.equals("resume"))
		{
			System.out.println("*****************Reader "+i+" resumed reading*****************");
			read[i]=1;
		}
	}
	public void stopReading(int i){
		read[i]=-1;
	}
	public void lock(){
		System.out.print("*****************Writer started writing, Reader ");
		for(int i = 1 ; i <= 2 ; i++)		
		{
			if(read[i]==1 || read[i]==2)
				System.out.print(i+" ");
		}
		System.out.print("on hold*****************\n");
		_lock = 1;
	}
	public void release(){
		if(read[1]==1)
			read[1]=0;
		if(read[2]==1)
			read[2]=0;
		_lock = 0;
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
				if(_lock==0)
				{
					if(read[i]==-1)
					{
						read[i]=0;
						System.out.println("*****************Reader "+i+" reading incomplete*****************");
						break;
					}
					else if(read[i] == 0)
					{
						System.out.println("*****************Reader "+i+" started reading*****************");
						read[i]=1;
					}
					
					else if(read[i]==1)
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
