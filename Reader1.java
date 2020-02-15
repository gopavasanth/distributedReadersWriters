import java.rmi.*;

public class Reader1{

public static void main(String args[]){
try{

Distributed stub=(Distributed)Naming.lookup("rmi://localhost:5000/run");
stub.reading(1);

}catch(Exception e){System.out.println(e);}
}

}
