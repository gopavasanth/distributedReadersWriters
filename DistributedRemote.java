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
	public void lock(){
		System.out.println("*****************Writer started writing, Readers on hold*****************");
		_lock = 1;
	}
	public void release(){
		read[1]=read[2]=0;
		_lock = 0;
		System.out.println("*****************Writer finished writing, Readers continue to read if any*****************");
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
					if(read[i] == 0)
					{
						System.out.println("*****************Reader "+i+" started reading*****************");
						read[i]=1;
					}
	    				System.out.println("Reader "+i+" reads "+sc.next());
					TimeUnit.SECONDS.sleep(2);
				}
			}
		}catch(Exception e){System.out.println(e);}
		System.out.println("*****************Reader "+i+" finished reading*****************");
	}

}
