import java.rmi.*;

public class Reader2{

public static void main(String args[]){
try{

Distributed stub=(Distributed)Naming.lookup("rmi://localhost:5000/run");
stub.reading(2);

}catch(Exception e){System.out.println(e);}
}

}
