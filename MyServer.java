import java.rmi.*;
import java.rmi.registry.*;

import java.net.InetAddress; 

public class MyServer{

    public static void main(String args[]){
        try{
            InetAddress localhost = InetAddress.getLocalHost(); 
            var ip=localhost.getHostAddress().trim();

            System.setProperty("java.rmi.server.hostname",ip);
            Distributed stub=new DistributedRemote();
            Naming.rebind("rmi://localhost:5000/run",stub);

        } catch(Exception e){
            System.out.println(e);
        }
    }

}
