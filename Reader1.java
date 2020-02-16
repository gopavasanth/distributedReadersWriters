import java.rmi.*;
import java.util.*;

public class Reader1{

public static void main(String args[]){
Scanner sc = new Scanner(System.in);
try{

Distributed stub=(Distributed)Naming.lookup("rmi://localhost:5000/run");
Thread newThread = new Thread(() -> {
try{
    stub.readingControl(1,true);
}catch(Exception e){
System.out.println(e);
}
});
newThread.start();
System.out.println("Do you want to stop reading (yes)");
String s = sc.next();
if(s.equals("yes"))
{
	stub.readingControl(1,false);
}
}catch(Exception e){System.out.println(e);}
}

}
