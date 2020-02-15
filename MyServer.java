import java.rmi.*;
import java.rmi.registry.*;

public class MyServer{

public static void main(String args[]){
try{

Distributed stub=new DistributedRemote();
Naming.rebind("rmi://localhost:5000/run",stub);

}catch(Exception e){System.out.println(e);}
}

}
